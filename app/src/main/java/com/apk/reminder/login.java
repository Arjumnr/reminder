package com.apk.reminder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.reminder.DBuser.DBuser;
import com.apk.reminder.DBuser.FieldUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {

    Button btnsignin;
    TextView btndaftar;
    EditText Username, Password;
    DBuser dbuser;
    Boolean valid = false;
    String Susername, Spassword;
    ProgressDialog progressDialog;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        dbuser = new DBuser( login.this );

        btnsignin = findViewById(R.id.btn_signin);
        Username = findViewById(R.id.id_username);
        Password = findViewById(R.id.id_password);
        progressDialog = new ProgressDialog(this);

        findViewById(R.id.btn_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Susername = Username.getText().toString();
                Spassword = Password.getText().toString();

                if (TextUtils.isEmpty(Susername) || TextUtils.isEmpty(Spassword)) {
                    Toast.makeText(getApplicationContext(), "CANNOT NO EMPTY",Toast.LENGTH_SHORT).show();

                    valid = false;
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://anr.my.id/APIR/cek-user", response -> {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean status = jsonObject.getBoolean("error");
                            if (!status) {
                                dbuser.SqlExecuteQuery( "delete from "+ FieldUser.NAMA_TABLE);
                                JSONArray data = jsonObject.getJSONArray( "data" );
                                for ( int i=0; i<data.length(); i++ ){
                                    JSONObject jsonObject_beda = data.getJSONObject( i );
                                    dbuser.SqlExecuteQuery( "insert into "+FieldUser.NAMA_TABLE+ " values( ' "
                                                                            + jsonObject_beda.getString( FieldUser.ID )+" ') " );

                                    level = jsonObject_beda.getString( "profesi" );
                                }

                                if (level.equals( "dosen" )){
                                    startActivity(new Intent( login.this, MainActivityDosen.class  ));
                                    finish();
                                }else if (level.equals ( "mahasiswa" )){
                                    startActivity(new Intent( login.this, MainActivity.class  ));
                                    finish();
                                }

                                Toast.makeText(getApplicationContext(), "SIGN IN Success",Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(login.this, "NO DATA", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }, error -> {

                        Toast.makeText(login.this, error.toString(), Toast.LENGTH_SHORT).show();
                    })  {
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("name", Susername);
                            params.put("password", Spassword);

                            return params;
                        }
                    };
                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);

                }




            }
        });
        btndaftar = findViewById(R.id.btn_daftar);
        findViewById(R.id.btn_daftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),regis.class));
                finish();
            }

        });
    }
}
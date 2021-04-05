package com.apk.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.reminder.DBuser.DBuser;
import com.apk.reminder.DBuser.FieldUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class edit_profil extends AppCompatActivity {
    EditText etnama,etemail,ettlp;
    String[] datalist;
    String id;
    DBuser dBuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        dBuser = new DBuser(edit_profil.this);
        datalist = dBuser.getData(FieldUser.NAMA_TABLE);
        id = datalist[0];

        etnama = findViewById(R.id.Enama);
        etemail = findViewById(R.id.Eemail);
        ettlp = findViewById(R.id.EnoPonsel);


        findViewById(R.id.btn_save).setOnClickListener( v -> {

            if(TextUtils.isEmpty(etnama.getText().toString()) || TextUtils.isEmpty(etemail.getText().toString()) || TextUtils.isEmpty(ettlp.getText().toString())){
                Toast.makeText(getApplicationContext(), "CANNOT NO EMPTY",Toast.LENGTH_SHORT).show();
            }else {
                simpanJadwal();
            }
        } );



    }

    private void simpanJadwal(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://anr.my.id/APIR/edit-profil", response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean status = jsonObject.getBoolean("error");
                if (!status) {
                    Toast.makeText(getApplicationContext(), "EDIT BERHASIL ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "EDIT GAGAL ", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(""+e,"gagal catch");
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }, error -> {
            Log.e("","gagal ");
            Toast.makeText(edit_profil.this, error.toString(), Toast.LENGTH_SHORT).show();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", etnama.getText().toString());
                params.put("email", etemail.getText().toString());
                params.put("no_hp", ettlp.getText().toString());


                return params;
            }

        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);
    }


}



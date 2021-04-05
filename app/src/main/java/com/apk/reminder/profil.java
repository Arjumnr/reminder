package com.apk.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class profil extends AppCompatActivity {

    TextView profesi, nama, email, nohp;
    String level, id;
    DBuser dbuser;
    String[] datalist;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        dbuser = new DBuser( profil.this );
        datalist = dbuser.getData( FieldUser.NAMA_TABLE );
        id = datalist[0];

        profesi = findViewById(R.id.id_pprofesi);
        nama = findViewById(R.id.id_Pnama);
        email = findViewById(R.id.id_Pemail);
        nohp = findViewById(R.id.id_Pphone);

        getData();



        findViewById(R.id.btn_edit_profil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),edit_profil.class));

            }


        });

        findViewById(R.id.btn_loguot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        });

    }


    public void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://anr.my.id/APIR/tampil-profil", response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean status = jsonObject.getBoolean("error");
                if (!status) {

                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject_beda = data.getJSONObject(i);

                        profesi.setText(jsonObject_beda.get("profesi").toString());
                        nama.setText(jsonObject_beda.get("name").toString());
                        email.setText(jsonObject_beda.get("email").toString());
                        nohp.setText(jsonObject_beda.get("no_hp").toString());

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }, error -> {
            Toast.makeText(profil.this, error.toString(), Toast.LENGTH_SHORT).show();
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);


                return params;
            }

        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);
    }

}
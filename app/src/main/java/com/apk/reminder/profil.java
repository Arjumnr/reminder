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
    String Susername, Spassword;
    String level, id;
    DBuser dbuser;
    String[] datalist;
    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        dbuser = new DBuser( profil.this );
        datalist = dbuser.getData( FieldUser.NAMA_TABLE );
        id = datalist[0];

        profesi = findViewById(R.id.id_pprofesi);
        nama = findViewById(R.id.id_Pnama);
        email = findViewById(R.id.id_Pemail);
        nohp = findViewById(R.id.id_Pphone);


                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://anr.my.id/APIR/tampil-profil", response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean status = jsonObject.getBoolean("error");
                        if (!status) {
                            dbuser.SqlExecuteQuery( "select from "+ FieldUser.NAMA_TABLE);
                            JSONArray data = jsonObject.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject_beda = data.getJSONObject(i);

                                profesi.setText("profesi" + id);
                                nama.setText("name" + id);
                                email.setText("email" + id);
                                nohp.setText("no_hp" + id)

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






//        findViewById(R.id.btn_edit_profil).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                simpanProfil();
//
//            }
//            public void simpanProfil(){
//                String url = "http://anr.my.id/APIR/cek-user";
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
//
//
//                        if (response!=null) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                boolean status = jsonObject.getBoolean("error");
//                                if (!status) {
//                                    Toast.makeText(getApplicationContext(), "BERHASIL", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(getApplicationContext(), "GAGAL", Toast.LENGTH_SHORT).show();
//
//                                }
//                            }catch (JSONException e){
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }, error -> {
//
//                    Toast.makeText(profil.this, error.toString(), Toast.LENGTH_SHORT).show();
//                })  {
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<>();
//                        params.put("name", Susername);
//                        params.put("password", Spassword);
//
//                        return params;
//                    }
//                };
//                Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);
//            }
//
//
//
//        });

        findViewById(R.id.btn_loguot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

    }




}
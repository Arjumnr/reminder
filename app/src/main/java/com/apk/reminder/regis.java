package com.apk.reminder;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class regis extends AppCompatActivity {
    Button btnsignup;
    ImageView icback;
    EditText name, email, nohp, pass;
    RadioGroup radioGroup;
    String Sradio, Snama, Snohp, Semail, Spass, status;

    Boolean STATUS;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        nohp = findViewById(R.id.noHp);
        pass = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        radioGroup=findViewById(R.id.rg_profesi);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_dosen :
                            Sradio="dosen";
                        break;
                    case R.id.rb_mahasiswa :
                            Sradio = "mahasiswa";
                        break;

                }
            }


        });


        btnsignup = findViewById(R.id.btn_signup);

        findViewById(R.id.btn_signup).setOnClickListener(v -> {


            Snama = name.getText().toString();
            Semail = email.getText().toString();
            Snohp = nohp.getText().toString();
            Spass = pass.getText().toString();


            if (TextUtils.isEmpty(Sradio) || TextUtils.isEmpty(Snama) || TextUtils.isEmpty(Semail) || TextUtils.isEmpty(Snohp) || TextUtils.isEmpty(Spass)) {
                Toast.makeText(getApplicationContext(), "CANNOT NO EMPTY",Toast.LENGTH_SHORT).show();
                STATUS = false;
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://anr.my.id/APIR/simpan-user", response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean status = jsonObject.getBoolean("error");
                        if (!status) {
                            Toast.makeText(getApplicationContext(), "SIGN UP Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), login.class));
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }, error -> {
                    progressDialog.hide();
                    Toast.makeText(regis.this, error.toString(), Toast.LENGTH_SHORT).show();
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("profesi", Sradio);
                        params.put("name", name.getText().toString());
                        params.put("email", email.getText().toString());
                        params.put("no_hp", nohp.getText().toString());
                        params.put("password", pass.getText().toString());



                        return params;
                    }

                };
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);

            }


        });
                icback = findViewById(R.id.ic_back);
                findViewById(R.id.ic_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),login.class));
                    }
                });

    }

}
package com.apk.reminder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.apk.reminder.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class edit_profil extends AppCompatActivity {
    EditText etnama,etemail,ettlp,etpass;
    RadioGroup eprofesi;
    RadioButton edosen, emahasiswa;
    User user = SharedPrefManager.getInstance(this).getUser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        etnama = findViewById(R.id.Enama);
        etemail = findViewById(R.id.Eemail);
        ettlp = findViewById(R.id.EnoPonsel);
        etpass = findViewById(R.id.Epassword);

        etnama.setText(user.getName());
        ettlp.setText(user.getNo_hp());
        etemail.setText(user.getEmail());
        etpass.setText(user.getPassword());

        findViewById(R.id.btn_edit_profil).setOnClickListener(v -> {
            ProgressDialog pd = new ProgressDialog(edit_profil.this);
            pd.setTitle("Mengubah Data");
            pd.setMessage("Loading...");
            pd.show();

            StringRequest stringRequest = new StringRequest(Request.Method.PUT, "http://api.anr.my.id/APIR/data/cek-user"+user.getId(), response -> {
                pd.dismiss();
//                    recreate();
//                ViewGroup vg = findViewById(R.id.)
                ;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("status")){
//                        SwipeRefreshLayout.OnRefreshListener;
//                        startActivity(new Intent(getApplicationContext(),BottomNavigationActivity.class));
//                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
//                        builder1.setTitle("Data Anda Berhasil DiUpdate");
//                        builder1.setMessage("Silahkan Login Kembali");
//                        builder1.setCancelable(true);
//
//                        builder1.setPositiveButton(
//                                "OK",
//                                (dialog, id) -> {
//                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                                    finish();
//
//                                });

//                        builder1.setNegativeButton(
//                                "No",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });

//                        AlertDialog alert11 = builder1.create();
//                        alert11.show();
//                        finish();
                        new AlertDialog.Builder(edit_profil.this)
                                .setTitle("Data Anda Berhasil DiUpdate")
                                .setMessage("Silahkan Login Kembali")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        SharedPrefManager.getInstance(edit_profil.this).logout();
                                        finish();
                                    }
                                }).show();
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }, error -> {
//                    progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", etnama.getText().toString());
                    params.put("email", etemail.getText().toString());
                    params.put("noHp", ettlp.getText().toString());
                    params.put("password", etpass.getText().toString());
                    return params;
                }
            };
            RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        });
    }


}



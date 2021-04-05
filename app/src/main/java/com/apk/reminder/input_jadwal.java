package com.apk.reminder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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


public class input_jadwal extends AppCompatActivity {

    ImageView icback;
    String[] dataDosen;
    String tes;
    String id;
    Spinner SPnamaDosen;
    TextView date, time;
    DBuser dBuser;
    String[] datalist;
    String tanggal, waktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jadwal);

        dBuser = new DBuser(input_jadwal.this);
        datalist = dBuser.getData(FieldUser.NAMA_TABLE);
        id = datalist[0];

        date = findViewById(R.id.id_date);
        time = findViewById(R.id.id_time);


        getNamaDosen();

        SPnamaDosen = findViewById(R.id.id_naDosen_dropdown);

        SPnamaDosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tes = dataDosen[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        icback = findViewById(R.id.ic_back);
        findViewById(R.id.ic_back).setOnClickListener( v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } );

        date.setOnClickListener( v -> panggilTANGGAL() );

        time.setOnClickListener( v -> panggilJAM() );

        findViewById(R.id.id_btn_save).setOnClickListener( v -> simpanJadwal() );
    }

    void getNamaDosen() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://anr.my.id/APIR/tampil-nama-dosen", response -> {
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean status = jsonObject.getBoolean("error");
                    if (!status) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        dataDosen = new String[data.length()];
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject object = data.getJSONObject(i);
                            dataDosen[i] = object.getString("name");
                        }
                        SPnamaDosen.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dataDosen));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    finish();
                }

            }

        }, error -> {

        } );
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);
    }


    void panggilTANGGAL() {
        AlertDialog.Builder builder = new AlertDialog.Builder(input_jadwal.this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") final View dialogView = inflater.inflate(R.layout.calender, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        final DatePicker tm = dialogView.findViewById(R.id.setTgl);
        Button ok = dialogView.findViewById(R.id.id_atur_tgl);
        final Button batal = dialogView.findViewById(R.id.id_batal_tanggal);
        final AlertDialog dialog = builder.create();

        ok.setOnClickListener(v -> {
            StringBuilder dx = new StringBuilder();
            int day = tm.getDayOfMonth();
            int month = tm.getMonth();
            int year = tm.getYear();
            String filterMounth, filterDay;
            filterMounth = String.valueOf(month + 1);
            filterDay = String.valueOf(day);

            if (filterDay.length() < 2)
                filterDay = "0" + filterDay;
            if (filterMounth.length() < 2)
                filterMounth = "0" + filterMounth;
            dx.append(filterDay).append("-").append(filterMounth).append("-").append(year);
            date.setText(dx);
            tanggal = dx.toString();
            dialog.cancel();
        });

        batal.setOnClickListener(v -> dialog.dismiss() );
        dialog.show();
    }

    private void panggilJAM() {
        AlertDialog.Builder builder = new AlertDialog.Builder(input_jadwal.this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") final View dialogView = inflater.inflate(R.layout.time_layout, null);
        builder.setCancelable(false);
        builder.setView(dialogView);
        final TimePicker tm = dialogView.findViewById(R.id.setjam);
        tm.setIs24HourView(true);
        Button oke = dialogView.findViewById(R.id.id_atur_waktu);
        final Button batal = dialogView.findViewById(R.id.id_batal_waktu);
        final AlertDialog dialog = builder.create();

        oke.setOnClickListener( v -> {
            // Oke
            StringBuilder dx = new StringBuilder();
            if (android.os.Build.VERSION.SDK_INT <= 23) {
                // only for gingerbread and newer versions
                dx.append(tm.getCurrentHour()).append(":").append(tm.getCurrentMinute()).append(":00");
            } else {
                dx.append(tm.getHour()).append(":").append(tm.getMinute()).append(":00");
            }
            time.setText(dx);
            waktu = dx.toString();

            dialog.cancel();
        } );

        batal.setOnClickListener( v -> {
            //batal
            dialog.dismiss();
        } );
        dialog.show();
    }

    private void simpanJadwal() {
        if (SPnamaDosen.getSelectedItem().toString().equals("")
                || (date.getText().toString().equals("Tanggal Pertemuan"))
                || (time.getText().toString().equals("Waktu Pertemuan"))
        ) {
            Toast.makeText(getApplicationContext(), "Silahkan Lengkapi Data Anda...!!!", Toast.LENGTH_LONG).show();
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://anr.my.id/APIR/simpan-jadwal-mahasiswa", response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean status = jsonObject.getBoolean("error");
                    if (!status) {
                        Toast.makeText(getApplicationContext(), "JADWAL TERKIRIM ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(""+e,"gagal catch");
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }, error -> {
                Log.e("","gagal ");
                Toast.makeText(input_jadwal.this, error.toString(), Toast.LENGTH_SHORT).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", id);
                    params.put("dosen", tes);
                    params.put("tgl_pertemuan", tanggal);
                    params.put("waktu_pertemuan", waktu);


                    return params;
                }

            };
            Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);

        }

    }
}
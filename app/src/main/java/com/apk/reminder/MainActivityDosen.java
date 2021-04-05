package com.apk.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityDosen extends AppCompatActivity {

    ImageView btnKonfirJadwal;
    ImageView btninfojadwal;
    ImageView btnprofil;
    ImageView btnhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dosen);

        btnprofil = findViewById(R.id.btn_profil);
        findViewById(R.id.btn_profil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),profil.class));

                

            }
        });

        btnKonfirJadwal = findViewById(R.id.btn_konfirmasi_jadwal);
        findViewById(R.id.btn_konfirmasi_jadwal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),recycleDosen.class));
            }
        });

        btninfojadwal = findViewById(R.id.btn_info_jadwal);
        findViewById(R.id.btn_info_jadwal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),recycle_info_jadwal_dosen.class));
            }
        });

        btnhistory = findViewById(R.id.btn_history);
        findViewById(R.id.btn_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),recycle_history_dosen.class));
            }
        });

    }
}
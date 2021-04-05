package com.apk.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView btnjadwal;
    ImageView btninfojadwal;
    ImageView btnprofil;
    ImageView btnhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnjadwal = findViewById(R.id.btn_jadwal);
        findViewById(R.id.btn_jadwal).setOnClickListener( v -> startActivity(new Intent(getApplicationContext(),input_jadwal.class)) );

        btninfojadwal = findViewById(R.id.btn_info_jadwal);
        findViewById(R.id.btn_info_jadwal).setOnClickListener( v -> startActivity(new Intent(getApplicationContext(),recycle.class)) );

        btnprofil = findViewById(R.id.btn_profil);
        findViewById(R.id.btn_profil).setOnClickListener( v -> startActivity(new Intent(getApplicationContext(),profil.class)) );

        btnhistory = findViewById(R.id.btn_history);
        findViewById(R.id.btn_history).setOnClickListener( v -> startActivity(new Intent(getApplicationContext(),recycle_history_mhs.class)) );
    }

    public void startAlarmManager(View view){

    }
}
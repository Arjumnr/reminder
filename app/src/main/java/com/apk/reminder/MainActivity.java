package com.apk.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        findViewById(R.id.btn_jadwal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),input_jadwal.class));
            }
        });

        btninfojadwal = findViewById(R.id.btn_info_jadwal);
        findViewById(R.id.btn_info_jadwal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),recycle.class));
            }
        });

        btnprofil = findViewById(R.id.btn_profil);
        findViewById(R.id.btn_profil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),profil.class));
            }
        });

        btnhistory = findViewById(R.id.btn_history);
        findViewById(R.id.btn_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),recycle.class));
            }
        });
    }
}
package com.apk.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.apk.reminder.DBuser.DBuser;
import com.apk.reminder.DBuser.FieldUser;

public class splashScreen extends AppCompatActivity {

    DBuser dbuser;
    String[] datalist;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        dbuser = new DBuser( splashScreen.this );
        datalist = dbuser.getData( FieldUser.NAMA_TABLE );
        id = datalist[0];


        new Handler(  ).postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity( new Intent( getApplicationContext(),MainActivity.class ) );
                finish();
            }
        },2000);
    }
}
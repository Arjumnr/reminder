package com.apk.reminder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.reminder.Adapter.adapterInfoJadwalDosen;
import com.apk.reminder.DBuser.DBuser;
import com.apk.reminder.DBuser.FieldUser;
import com.apk.reminder.Model.dosenModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class recycle_info_jadwal_dosen extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DBuser dBuser;
    adapterInfoJadwalDosen adapterInfoJadwalDosen;
    String[] datalist;
    String id, name;
    List<dosenModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycle_info_jadwal_dosen );

        recyclerView = findViewById( R.id.id_recycle_dosen_info );
        linearLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );

        dBuser = new DBuser( recycle_info_jadwal_dosen.this );
        datalist = dBuser.getData( FieldUser.NAMA_TABLE );
        id = datalist[0];
        name = datalist[1];

        tampilInfoJadwalDosen( getApplicationContext() );



    }

    void tampilInfoJadwalDosen(Context context) {
        adapterInfoJadwalDosen = new adapterInfoJadwalDosen( list, context );
        recyclerView.setAdapter( adapterInfoJadwalDosen );

        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://anr.my.id/APIR/tampil-info-jadwal-dosen", response -> {
            try {
                JSONObject jsonObject = new JSONObject( response );
                boolean status = jsonObject.getBoolean( "error" );
                if (!status) {
                    JSONArray jsonArray = jsonObject.getJSONArray( "data" );
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                        dosenModel dosenModel = new dosenModel();

                        String w = " " + jsonObject1.getString( "dosen" ) + " ";
                        Log.e( name, "tes" );
                        Log.e( w, "tes2" );
                        System.out.println( name + "tes" );
                        System.out.println( w + "tes2" );

                        if(w.equals( name )) {
                            dosenModel.setIdMahasiswa( jsonObject1.getString( "mahasiswa" ) );
                            dosenModel.setNamamhs( jsonObject1.getString( "name" ) );
                            dosenModel.setNamaDosen( jsonObject1.getString( "dosen" ) );
                            dosenModel.setTglPertemuan( jsonObject1.getString( "tgl_pertemuan" ) );
                            dosenModel.setWaktuPertemuan( jsonObject1.getString( "waktu_pertemuan" ) );
                            dosenModel.setTglPenginputan( jsonObject1.getString( "created_at" ) );

                            list.add( dosenModel );
                            adapterInfoJadwalDosen = new adapterInfoJadwalDosen( list, context );
                            recyclerView.setAdapter( adapterInfoJadwalDosen );
                        }


                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        } ) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put( "id", id );
                hashMap.put( "name", name );
                return hashMap;

            }
        };
        Volley.newRequestQueue( getApplicationContext() ).add( stringRequest ).setShouldCache( false );
    }
}
package com.apk.reminder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.reminder.Adapter.adapterHistoryDosen;
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

public class recycle_history_dosen extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DBuser dBuser;
    adapterHistoryDosen adapterHistoryDosen;
    String[] datalist;
    String id, name;
    List<dosenModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycle_history_dosen );

        recyclerView = findViewById( R.id.id_recycle_history_dosen );
        linearLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );

        dBuser = new DBuser( recycle_history_dosen.this );
        datalist = dBuser.getData( FieldUser.NAMA_TABLE );
        id = datalist[0];
        name = datalist[1];

        getHistoryDosen(getApplicationContext());
    }

    void getHistoryDosen(Context context) {
        adapterHistoryDosen = new adapterHistoryDosen( list, context );
        recyclerView.setAdapter( adapterHistoryDosen );

        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://anr.my.id/APIR/tampil-history-dosen", response -> {
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
                            adapterHistoryDosen = new adapterHistoryDosen( list, context );
                            recyclerView.setAdapter( adapterHistoryDosen );
                        }

                        else{
                            Toast.makeText( context,"xxxxxx",Toast.LENGTH_SHORT ).show();

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
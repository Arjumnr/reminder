package com.apk.reminder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.reminder.Adapter.adapterHistoryMhs;
import com.apk.reminder.DBuser.DBuser;
import com.apk.reminder.DBuser.FieldUser;
import com.apk.reminder.Model.jadwal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class recycle_history_mhs extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterHistoryMhs adapterHistoryMhs;
    LinearLayoutManager linearLayoutManager;
    List<jadwal> list=new ArrayList<>();
    DBuser dBuser;
    String[] datalist;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycle_history_mhs );

        dBuser = new DBuser( recycle_history_mhs.this );
        datalist = dBuser.getData( FieldUser.NAMA_TABLE );
        id = datalist[0];

        recyclerView = findViewById(R.id.id_recycle_history_mhs);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getHistoryMhs( getApplicationContext() );

    }

    void getHistoryMhs(Context context){
        recyclerView.setAdapter(adapterHistoryMhs);


        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://anr.my.id/APIR/tampil-history-mhs", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean status = jsonObject.getBoolean("error");
                    if (!status){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            jadwal jadwal = new jadwal();

                            if( id.equals(jsonObject1.getString("mahasiswa"))  ){
                                jadwal.setNamaDosen(jsonObject1.getString("dosen"));
                                jadwal.setTglPertemuan(jsonObject1.getString("tgl_pertemuan"));
                                jadwal.setWaktuPertemuan(jsonObject1.getString("waktu_pertemuan"));
                                jadwal.setStatus(jsonObject1.getString("status_jadwal"));
                                Log.e(jadwal.getStatus(), "gagal");
                                jadwal.setTglPenginputan(jsonObject1.getString("created_at"));

                                list.add(jadwal);
                                adapterHistoryMhs = new adapterHistoryMhs(list, context);
                                recyclerView.setAdapter(adapterHistoryMhs);
                            }




                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap=new HashMap<>();
//                hashMap.put("id", id);
                return hashMap;

            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);
    }
}
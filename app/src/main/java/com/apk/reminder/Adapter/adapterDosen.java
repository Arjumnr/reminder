package com.apk.reminder.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.reminder.Model.dosenModel;
import com.apk.reminder.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adapterDosen extends RecyclerView.Adapter<adapterDosen.holderKonfirmasiDosen> {
    List<dosenModel> list;
    Context context;

    public adapterDosen(List<dosenModel> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterDosen.holderKonfirmasiDosen onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_info_jadwal_dosen,parent, false);
        return new holderKonfirmasiDosen( view );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adapterDosen.holderKonfirmasiDosen holder, int position) {
        dosenModel dosenModel = list.get(position);
        holder.namaMahasiswa.setText("Nama : "+dosenModel.getNamamhs());
        holder.tglPertemuan.setText("Tanggal : "+dosenModel.getTglPertemuan());
        holder.waktuPertemuan.setText("Pukul : "+dosenModel.getWaktuPertemuan());
        holder.tglPenginputan.setText(dosenModel.getTglPenginputan());


        holder.terima.setOnClickListener( v -> {
            StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://anr.my.id/APIR/edit-status-jadwal", response -> {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean status=jsonObject.getBoolean( "error" );
                    if(!status){
                        Toast.makeText( v.getContext(),"Berhasil Diterima",Toast.LENGTH_SHORT ).show();
                        System.out.println(dosenModel.getNamaDosen());
                        System.out.println(dosenModel.getIdMahasiswa());
                         ((Activity)v.getContext()).finish();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }, error -> {

            } ){
                @Override
                protected Map<String, String> getParams() {
                    HashMap<String,String> map=new HashMap<>();
                    map.put( "id", dosenModel.getIdMahasiswa() );
                    map.put( "dosen", dosenModel.getNamaDosen() );
                    map.put( "status_jadwal", "diterima");
                    return map;
                }
            };
            Volley.newRequestQueue(  v.getContext()).add( stringRequest ).setShouldCache( false );
        } );

        holder.tolak.setOnClickListener( v -> {
            StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://anr.my.id/APIR/edit-status-jadwal", response -> {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean status=jsonObject.getBoolean( "error" );
                    if(!status){
                        Toast.makeText( v.getContext(),"Berhasil Ditolak",Toast.LENGTH_SHORT ).show();
                        System.out.println(dosenModel.getNamaDosen());
                        System.out.println(dosenModel.getIdMahasiswa());
                       ((Activity)v.getContext()).finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }, error -> {

            } ){
                @Override
                protected Map<String, String> getParams() {
                    HashMap<String,String> map=new HashMap<>();
                    map.put( "id", dosenModel.getIdMahasiswa() );
                    map.put( "dosen", dosenModel.getNamaDosen() );
                    map.put( "status_jadwal", "ditolak");
                    return map;
                }
            };
            Volley.newRequestQueue(  v.getContext()).add( stringRequest ).setShouldCache( false );
        } );
    }





    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class holderKonfirmasiDosen extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView namaMahasiswa;
        TextView tglPertemuan;
        TextView waktuPertemuan;
        TextView tglPenginputan;
        Button terima;
        Button tolak;

        public holderKonfirmasiDosen(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.id_img);
            namaMahasiswa = itemView.findViewById(R.id.id_nama_mhs);
            tglPertemuan = itemView.findViewById(R.id.id_tgl_pertemuan_dsn);
            waktuPertemuan = itemView.findViewById(R.id.id_waktu_pertemuan_dsn);
            tglPenginputan = itemView.findViewById(R.id.id_tgl_penginputan_dsn);
            terima = itemView.findViewById(R.id.id_terima);
            tolak = itemView.findViewById(R.id.id_tolak);

        }
    }
}

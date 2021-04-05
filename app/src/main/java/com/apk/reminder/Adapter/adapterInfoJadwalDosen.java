package com.apk.reminder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.reminder.Model.dosenModel;
import com.apk.reminder.R;

import java.util.List;

public class adapterInfoJadwalDosen extends RecyclerView.Adapter<adapterInfoJadwalDosen.holderInfoJadwalDosen> {
    List<dosenModel> list;
    Context context;

    public adapterInfoJadwalDosen(List<dosenModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterInfoJadwalDosen.holderInfoJadwalDosen onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_jadwal_dosen, parent, false );
        return new holderInfoJadwalDosen( view );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adapterInfoJadwalDosen.holderInfoJadwalDosen holder, int position) {

        dosenModel dosenModel = list.get( position );
        holder.namaMahasiswa.setText( "Nama : " + dosenModel.getNamamhs() );
        holder.tglPertemuan.setText( "Tanggal : " + dosenModel.getTglPertemuan() );
        holder.waktuPertemuan.setText( "Pukul : " + dosenModel.getWaktuPertemuan() );
        holder.tglPenginputan.setText( dosenModel.getTglPenginputan() );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class holderInfoJadwalDosen extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView namaMahasiswa;
        TextView tglPertemuan;
        TextView waktuPertemuan;
        TextView tglPenginputan;

        public holderInfoJadwalDosen(View view) {
            super( view );
            imageView = view.findViewById( R.id.id_img );
            namaMahasiswa = view.findViewById( R.id.id_nama_mhs_info );
            tglPertemuan = view.findViewById( R.id.id_tgl_pertemuan_dsn_info );
            waktuPertemuan = view.findViewById( R.id.id_waktu_pertemuan_dsn_info );
            tglPenginputan = view.findViewById( R.id.id_tgl_penginputan_dsn_info );
        }


    }
}

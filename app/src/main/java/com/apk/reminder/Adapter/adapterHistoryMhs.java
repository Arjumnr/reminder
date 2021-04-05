package com.apk.reminder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apk.reminder.Model.jadwal;
import com.apk.reminder.R;

import java.util.List;

public class adapterHistoryMhs extends RecyclerView.Adapter<adapterHistoryMhs.holderHistoryMhs> {
    List<jadwal> list;
    Context context;

    public adapterHistoryMhs(List<jadwal> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterHistoryMhs.holderHistoryMhs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.card_history_mhs,parent, false);
        return new holderHistoryMhs( view );
    }

    @Override
    public void onBindViewHolder(@NonNull adapterHistoryMhs.holderHistoryMhs holder, int position) {
        jadwal jadwal = list.get(position);
        holder.namaDosen.setText("Nama Dosen : "+jadwal.getNamaDosen());
        holder.tglPertemuan.setText("Tanggal : "+jadwal.getTglPertemuan());
        holder.waktuPertemuan.setText("Pukul : "+jadwal.getWaktuPertemuan());
        holder.status.setText(jadwal.getStatus());
        holder.tglPenginputan.setText(jadwal.getTglPenginputan());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class holderHistoryMhs extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView namaDosen;
        TextView tglPertemuan;
        TextView waktuPertemuan;
        TextView status;
        TextView tglPenginputan;

        public holderHistoryMhs(@NonNull View itemView) {
            super( itemView );
            imageView = itemView.findViewById(R.id.id_img);
            namaDosen = itemView.findViewById(R.id.id_nama_dosen);
            tglPertemuan = itemView.findViewById(R.id.id_tgl_pertemuan);
            waktuPertemuan = itemView.findViewById(R.id.id_waktu_pertemuan);
            status = itemView.findViewById(R.id.id_card_status);
            tglPenginputan = itemView.findViewById(R.id.id_tgl_penginputan);
        }
    }
}

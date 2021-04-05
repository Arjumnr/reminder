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

import com.apk.reminder.Model.jadwal;
import com.apk.reminder.R;

import java.util.List;

public class adapterMahasiswa extends RecyclerView.Adapter<adapterMahasiswa.holderJadwalMahasiswa> {
    // deklarasi
    List<jadwal> list;
    Context context;

    //konstruktor
    public adapterMahasiswa(List<jadwal> list, Context context){
        this.list = list;
        this.context = context;
    }

    //method
    @NonNull
    @Override
    public holderJadwalMahasiswa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_info_jadwal_mahasiswa,parent, false);
        return new holderJadwalMahasiswa( view );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull holderJadwalMahasiswa holder, int position) {
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

    public static class holderJadwalMahasiswa extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView namaDosen;
        TextView tglPertemuan;
        TextView waktuPertemuan;
        TextView status;
        TextView tglPenginputan;
//        CardView cardMahasiswa;

        public holderJadwalMahasiswa(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.id_img);
            namaDosen = itemView.findViewById(R.id.id_nama_dosen);
            tglPertemuan = itemView.findViewById(R.id.id_tgl_pertemuan);
            waktuPertemuan = itemView.findViewById(R.id.id_waktu_pertemuan);
            status = itemView.findViewById(R.id.id_card_status);
            tglPenginputan = itemView.findViewById(R.id.id_tgl_penginputan);
//          cardMahasiswa = itemView.findViewById(R.id.id_card_jadwal_mhs);

        }
    }

}

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

public class adapterHistoryDosen extends RecyclerView.Adapter<adapterHistoryDosen.holderHistoryDosen> {
    List<dosenModel> list;
    Context context;

    public adapterHistoryDosen(List<dosenModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterHistoryDosen.holderHistoryDosen onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_history_dosen, parent, false );
        return new holderHistoryDosen( view );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adapterHistoryDosen.holderHistoryDosen holder, int position) {
        dosenModel dosenModel = list.get( position );
        holder.namaMahasiswa.setText( "Nama : " + dosenModel.getNamamhs() );
        holder.tglPertemuan.setText( "Tanggal : " + dosenModel.getTglPertemuan() );
        holder.waktuPertemuan.setText( "Pukul : " + dosenModel.getWaktuPertemuan() );
//        holder.status.setText( dosenModel.getStatus() );
        holder.tglPenginputan.setText( dosenModel.getTglPenginputan() );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class holderHistoryDosen extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView namaMahasiswa;
        TextView tglPertemuan;
        TextView waktuPertemuan;
        TextView tglPenginputan;
//        TextView status;

        public holderHistoryDosen( View view) {
            super(view);
            imageView = view.findViewById(R.id.id_img);
            namaMahasiswa = view.findViewById(R.id.id_nama_mhs_his);
            tglPertemuan = view.findViewById(R.id.id_tgl_pertemuan_dsn_his);
            waktuPertemuan = view.findViewById(R.id.id_waktu_pertemuan_dsn_his);
//            status = itemView.findViewById(R.id.id_card_status);
            tglPenginputan = view.findViewById(R.id.id_tgl_penginputan_dsn_his);
        }
    }
}

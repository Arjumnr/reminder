package com.apk.reminder.Model;

public class jadwal {
    private String id, tgl_pertemuan, waktu_pertemuan, status, id_mahasiswa, id_dosen;

    public String getId(){return id;}
    public  String getTgl_pertemuan(){return tgl_pertemuan;}
    public String getWaktu_pertemuan(){return  waktu_pertemuan;}
    public String getStatus(){return status;}
    public String getId_mahasiswa(){return id_mahasiswa;}
    public String getId_dosen(){return id_dosen;}


    public jadwal(String Id, String tgl_pertemuan, String waktu_pertemuan, String status, String id_mahasiswa, String id_dosen){
        this.id = id;
        this.tgl_pertemuan = tgl_pertemuan;
        this.waktu_pertemuan = waktu_pertemuan;
        this.status = status;
        this.id_mahasiswa = id_mahasiswa;
        this.id_dosen = id_dosen;
    }
}

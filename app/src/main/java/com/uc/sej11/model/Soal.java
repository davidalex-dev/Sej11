package com.uc.sej11.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Soal implements Parcelable {

    private List<Data> soal;

    protected Soal(Parcel in) {
    }

    public static final Creator<Soal> CREATOR = new Creator<Soal>() {
        @Override
        public Soal createFromParcel(Parcel in) {
            return new Soal(in);
        }

        @Override
        public Soal[] newArray(int size) {
            return new Soal[size];
        }
    };

    public static Soal objectFromData(String str) {

        return new Gson().fromJson(str, Soal.class);
    }

    public List<Data> getSoal() {
        return soal;
    }

    public void setSoal(List<Data> soal) {
        this.soal = soal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Data {
        private int id;
        private int sej11__level_id;
        private int sej11_waktu_id;
        private Object gambar_materi_id;
        private String soal;
        private int jenis_soal;
        private String potongan_gambar;

        public static Data objectFromData(String str) {

            return new Gson().fromJson(str, Data.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSej11__level_id() {
            return sej11__level_id;
        }

        public void setSej11__level_id(int sej11__level_id) {
            this.sej11__level_id = sej11__level_id;
        }

        public int getSej11_waktu_id() {
            return sej11_waktu_id;
        }

        public void setSej11_waktu_id(int sej11_waktu_id) {
            this.sej11_waktu_id = sej11_waktu_id;
        }

        public Object getGambar_materi_id() {
            return gambar_materi_id;
        }

        public void setGambar_materi_id(Object gambar_materi_id) {
            this.gambar_materi_id = gambar_materi_id;
        }

        public String getSoal() {
            return soal;
        }

        public void setSoal(String soal) {
            this.soal = soal;
        }

        public int getJenis_soal() {
            return jenis_soal;
        }

        public void setJenis_soal(int jenis_soal) {
            this.jenis_soal = jenis_soal;
        }

        public String getPotongan_gambar() {
            return potongan_gambar;
        }

        public void setPotongan_gambar(String potongan_gambar) {
            this.potongan_gambar = potongan_gambar;
        }
    }
}

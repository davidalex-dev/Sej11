package com.uc.sej11.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Materi implements Parcelable {
    private List<Data> data;

    protected Materi(Parcel in) {
    }

    public static final Creator<Materi> CREATOR = new Creator<Materi>() {
        @Override
        public Materi createFromParcel(Parcel in) {
            return new Materi(in);
        }

        @Override
        public Materi[] newArray(int size) {
            return new Materi[size];
        }
    };

    public static Materi objectFromData(String str) {

        return new Gson().fromJson(str, Materi.class);
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
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
        private String gambar_utuh;
        private String judul_sub_bab;
        private String materi;

        public static Data objectFromData(String str) {

            return new Gson().fromJson(str, Data.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGambar_utuh() {
            return gambar_utuh;
        }

        public void setGambar_utuh(String gambar_utuh) {
            this.gambar_utuh = gambar_utuh;
        }

        public String getJudul_sub_bab() {
            return judul_sub_bab;
        }

        public void setJudul_sub_bab(String judul_sub_bab) {
            this.judul_sub_bab = judul_sub_bab;
        }

        public String getMateri() {
            return materi;
        }

        public void setMateri(String materi) {
            this.materi = materi;
        }
    }
}

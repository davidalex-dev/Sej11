package com.uc.sej11.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Waktu implements Parcelable {


    private List<Sej11Waktu> sej11_waktu;

    protected Waktu(Parcel in) {
    }

    public static final Creator<Waktu> CREATOR = new Creator<Waktu>() {
        @Override
        public Waktu createFromParcel(Parcel in) {
            return new Waktu(in);
        }

        @Override
        public Waktu[] newArray(int size) {
            return new Waktu[size];
        }
    };

    public static Waktu objectFromData(String str) {

        return new Gson().fromJson(str, Waktu.class);
    }

    public List<Sej11Waktu> getSej11_waktu() {
        return sej11_waktu;
    }

    public void setSej11_waktu(List<Sej11Waktu> sej11_waktu) {
        this.sej11_waktu = sej11_waktu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Sej11Waktu {
        private int id;
        private int waktu;

        public static Sej11Waktu objectFromData(String str) {

            return new Gson().fromJson(str, Sej11Waktu.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWaktu() {
            return waktu;
        }

        public void setWaktu(int waktu) {
            this.waktu = waktu;
        }
    }
}

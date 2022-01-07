package com.uc.sej11.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Waktu implements Parcelable {


    private List<Data> data;

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

    public List<Data> getdataWaktu() {
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
        private int waktu;

        public static Data objectFromData(String str) {

            return new Gson().fromJson(str, Data.class);
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

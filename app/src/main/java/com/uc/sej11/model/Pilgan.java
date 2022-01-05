package com.uc.sej11.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Pilgan implements Parcelable {


    private List<Sej11OpsiPilgan> sej11_opsi_pilgan;

    protected Pilgan(Parcel in) {
    }

    public static final Creator<Pilgan> CREATOR = new Creator<Pilgan>() {
        @Override
        public Pilgan createFromParcel(Parcel in) {
            return new Pilgan(in);
        }

        @Override
        public Pilgan[] newArray(int size) {
            return new Pilgan[size];
        }
    };

    public static Pilgan objectFromData(String str) {

        return new Gson().fromJson(str, Pilgan.class);
    }

    public List<Sej11OpsiPilgan> getSej11_opsi_pilgan() {
        return sej11_opsi_pilgan;
    }

    public void setSej11_opsi_pilgan(List<Sej11OpsiPilgan> sej11_opsi_pilgan) {
        this.sej11_opsi_pilgan = sej11_opsi_pilgan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Sej11OpsiPilgan {
        private int id;
        private int sej11_soal_id;
        private String opsi_pg;
        private int status_benar;

        public static Sej11OpsiPilgan objectFromData(String str) {

            return new Gson().fromJson(str, Sej11OpsiPilgan.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSej11_soal_id() {
            return sej11_soal_id;
        }

        public void setSej11_soal_id(int sej11_soal_id) {
            this.sej11_soal_id = sej11_soal_id;
        }

        public String getOpsi_pg() {
            return opsi_pg;
        }

        public void setOpsi_pg(String opsi_pg) {
            this.opsi_pg = opsi_pg;
        }

        public int getStatus_benar() {
            return status_benar;
        }

        public void setStatus_benar(int status_benar) {
            this.status_benar = status_benar;
        }
    }
}

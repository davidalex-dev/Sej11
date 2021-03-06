package com.uc.sej11.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

public class RegisterResponse implements Parcelable {
    private String name;
    private String email;
    private String username;
    private String password;
    private String school;
    private String city;
    private String birthyear;

    protected RegisterResponse(Parcel in) {
        name = in.readString();
        email = in.readString();
        username = in.readString();
        password = in.readString();
        school = in.readString();
        city = in.readString();
        birthyear = in.readString();
    }

    public static final Creator<RegisterResponse> CREATOR = new Creator<RegisterResponse>() {
        @Override
        public RegisterResponse createFromParcel(Parcel in) {
            return new RegisterResponse(in);
        }

        @Override
        public RegisterResponse[] newArray(int size) {
            return new RegisterResponse[size];
        }
    };

    public static RegisterResponse objectFromData(String str) {

        return new Gson().fromJson(str, RegisterResponse.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() { return username; }

    public void setUsername(){ this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(school);
        parcel.writeString(city);
        parcel.writeString(birthyear);
    }
}

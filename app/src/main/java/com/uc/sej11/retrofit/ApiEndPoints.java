package com.uc.sej11.retrofit;

import com.google.gson.JsonObject;
import com.uc.sej11.model.Materi;
import com.uc.sej11.model.Pilgan;
import com.uc.sej11.model.RegisterResponse;
import com.uc.sej11.model.Soal;
import com.uc.sej11.model.TokenResponse;
import com.uc.sej11.model.Waktu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("username") String username,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String password_confirmation,
                                    @Field("school") String school,
                                    @Field("city") String city,
                                    @Field("birthyear") String birthyear);

    @POST("logout")
    Call<JsonObject> logout();

    @GET("sej11_levels")
    Call<Materi> getData();

    @GET("sej11_soal/{id}")
    Call<Soal> getSej11_soal(
            @Path("id") String id
    );

    @GET("sej11_opsi_pilgan/{id}")
    Call<Pilgan> getSej11_opsi_pilgan(
            @Path("id") String id
    );

    @GET("sej11_waktu/{id}")
    Call<Waktu> getSej11_waktu(
            @Path("id") String id
    );

//    @GET("sej11_soal/{sej11_soal}")
//    Call<List<Soal>> getSej11_soal(
//            @Path("sej11_soal") String id
//    );

}

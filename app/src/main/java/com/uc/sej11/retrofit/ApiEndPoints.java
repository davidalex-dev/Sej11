package com.uc.sej11.retrofit;

import com.google.gson.JsonObject;
import com.uc.sej11.model.RegisterResponse;
import com.uc.sej11.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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


}

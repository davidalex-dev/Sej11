package com.uc.sej11.retrofit;

import com.google.gson.JsonObject;
import com.uc.sej11.helper.Const;
import com.uc.sej11.model.Materi;
import com.uc.sej11.model.Pilgan;
import com.uc.sej11.model.RegisterResponse;
import com.uc.sej11.model.Soal;
import com.uc.sej11.model.TokenResponse;
import com.uc.sej11.model.Waktu;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final ApiEndPoints api;
    private static RetrofitService service;
    private static final String TAG = "RetrofitService";

    public RetrofitService(String token){
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (token.equals("")){
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(request);
            });
        }else {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(request);
            });
        }

        api = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build().create(ApiEndPoints.class);
    }

    public static RetrofitService getInstance(String token){
        if(service == null){
            service = new RetrofitService(token);
        }else if(!token.equals("")){
            service = new RetrofitService(token);
        }
        return service;
    }

    public Call<TokenResponse> login(String email, String password){
        return api.login(email, password);
    }

    public Call<RegisterResponse> register(String name, String email, String username,
                                           String password, String password_confirmation,
                                           String school, String city, String birthyear){
        return api.register(name, email, username, password, password_confirmation, school, city,
                birthyear);
    }

    public Call<Materi> getData(){
        return api.getData();
    }

    public Call<Soal> getSej11_soal(String id){
        return api.getSej11_soal(id);
    }

    public Call<Waktu> getSej11_waktu(String id){
        return api.getSej11_waktu(id);
    }

    public Call<Pilgan> getSej11_opsi_pilgan(String id){
        return api.getSej11_opsi_pilgan(id);
    }

    public Call<JsonObject> logout(){return api.logout();}

}

package com.diet.model.retrofits;

import android.content.Context;

import com.diet.model.services.UserApiRetrofitService;
import com.diet.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApiRetrofit {

    private static UserApiRetrofit instance = null;

    public static UserApiRetrofit getInstance(Context context) {
        if (instance == null) {
            synchronized (UserApiRetrofit.class) {
                if (instance == null) {
                    instance = new UserApiRetrofit(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private final Context context;
    private Gson gson;
    private Retrofit retrofit;
    private final UserApiRetrofitService service;

    private UserApiRetrofit(Context context) {
        this.context = context;

        gson = new GsonBuilder()
                .setLenient()
                .create();

        System.out.println("step ******************************************************** retro - 001");


        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.getCurrentURL(this.context)+"/user/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        System.out.println("step ******************************************************** retro - 002");

        service = retrofit.create(UserApiRetrofitService.class);

        System.out.println("step ******************************************************** retro - 003");

    }

    public UserApiRetrofitService getService() { return service; }
}

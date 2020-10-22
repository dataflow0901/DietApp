package com.diet.model.retrofits;

import android.content.Context;

import com.diet.model.services.OrderApiRetrofitService;
import com.diet.model.services.SearchApiRetrofitService;
import com.diet.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchApiRetrofit {

    private static SearchApiRetrofit instance = null;

    public static SearchApiRetrofit getInstance(Context context) {
        if (instance == null) {
            synchronized (SearchApiRetrofit.class) {
                if (instance == null) {
                    instance = new SearchApiRetrofit(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private final Context context;
    private Gson gson;
    private Retrofit retrofit;
    private final SearchApiRetrofitService service;

    private SearchApiRetrofit(Context context) {
        this.context = context;

        gson = new GsonBuilder()
                .setLenient()
                .create();

        System.out.println("step ******************************************************** retro - 001");


        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.getCurrentURL(this.context)+"/product/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        System.out.println("step ******************************************************** retro - 002");

        service = retrofit.create(SearchApiRetrofitService.class);

        System.out.println("step ******************************************************** retro - 003");

    }

    public SearchApiRetrofitService getService() { return service; }
}

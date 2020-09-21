package com.diet.model.retrofits;

import android.content.Context;

import com.diet.model.services.ProductApiRetrofitService;
import com.diet.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductApiRetrofit {

    private static ProductApiRetrofit instance = null;

    public static ProductApiRetrofit getInstance(Context context) {
        if (instance == null) {
            synchronized (ProductApiRetrofit.class) {
                if (instance == null) {
                    instance = new ProductApiRetrofit(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private final Context context;
    private Gson gson;
    private Retrofit retrofit;
    private final ProductApiRetrofitService service;

    private ProductApiRetrofit(Context context) {
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

        service = retrofit.create(ProductApiRetrofitService.class);

        System.out.println("step ******************************************************** retro - 003");

    }

    public ProductApiRetrofitService getService() { return service; }

}



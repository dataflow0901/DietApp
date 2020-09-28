package com.diet.model.retrofits;

import android.content.Context;

import com.diet.model.services.ProductApiRetrofitService;
import com.diet.model.services.OrderApiRetrofitService;
import com.diet.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderApiRetrofit {

    private static OrderApiRetrofit instance = null;

    public static OrderApiRetrofit getInstance(Context context) {
        if (instance == null) {
            synchronized (OrderApiRetrofit.class) {
                if (instance == null) {
                    instance = new OrderApiRetrofit(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private final Context context;
    private Gson gson;
    private Retrofit retrofit;
    private final OrderApiRetrofitService service;

    private OrderApiRetrofit(Context context) {
        this.context = context;

        gson = new GsonBuilder()
                .setLenient()
                .create();

        System.out.println("step ******************************************************** retro - 001");


        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.getCurrentURL(this.context)+"/order/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        System.out.println("step ******************************************************** retro - 002");

        service = retrofit.create(OrderApiRetrofitService.class);

        System.out.println("step ******************************************************** retro - 003");

    }

    public OrderApiRetrofitService getService() { return service; }

}



package com.diet.model.services;


import com.diet.model.ProductDTO;
import com.diet.model.OrderDTO;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OrderApiRetrofitService {

    //    @Headers("Content-Type: application/json")
//    @POST("register")
//    Call<JsonObject> register(@Body UserAccountDTO user);
//
//    @Headers("Content-Type: application/json")
//    @POST("checkId")
//    Call<JsonObject> checkId(@Body UserAccountDTO user);
//
//    @Headers("Content-Type: application/json")
//    @POST("login")
//    Call<JsonObject> login(@Body UserAccountDTO user);
//
//

    @Headers("Content-Type: application/json")
    @POST("addOrder")
    Call<JsonObject> addOrder(@Body OrderDTO order);


    @Headers("Content-Type: application/json")
    @POST("addCart")
    Call<JsonObject> addCart(@Body OrderDTO order);

    @Headers("Content-Type: application/json")
    @POST("addZzim")
    Call<JsonObject> addZzim(@Body OrderDTO order);

    @Headers("Content-Type: application/json")
    @POST("getOrderInfoByUserID")
    Call<JsonObject> getOrderInfoByUserID(@Body OrderDTO order);


}
 
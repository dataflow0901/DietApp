package com.diet.model.services;


import com.diet.model.ProductDTO;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ProductApiRetrofitService {

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
    @POST("getProductList")
    Call<JsonObject> getProductList(@Body ProductDTO product);


    @Headers("Content-Type: application/json")
    @POST("getProduct")
    Call<JsonObject> getProduct(@Body ProductDTO product);

    @Headers("Content-Type: application/json")
    @POST("getCategoryList")
    Call<JsonObject> getCategoryList(@Body ProductDTO product);

    @Headers("Content-Type: application/json")
    @POST("getCategoryListDetail")
    Call<JsonObject> getCategoryListDetail(@Body ProductDTO product);

}
 
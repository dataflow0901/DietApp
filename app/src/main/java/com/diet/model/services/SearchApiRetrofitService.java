package com.diet.model.services;


import com.diet.model.SearchDTO;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SearchApiRetrofitService {
    @Headers("Content-Type: application/json")
    @POST("productSerach")
    Call<JsonObject> addOrder(@Body SearchDTO serch);
}

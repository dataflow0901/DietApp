package com.diet.model.services;


import com.diet.model.SearchDTO;
import com.diet.model.UserDTO;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApiRetrofitService {
    @Headers("Content-Type: application/json")
    @POST("register")
    Call<JsonObject> register(@Body UserDTO user);
}

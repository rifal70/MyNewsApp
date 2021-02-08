package com.application.rifal.myapplication.apiHelper;

import com.application.rifal.myapplication.model.ModuleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseApiService {
    @GET("sources")
    Call<ModuleResponse> showNews(@Query("apiKey") String key,
                                  @Query("category") String cat);
    @GET("sources")
    Call<ModuleResponse> showNewsGeneral(@Query("apiKey") String key);
}
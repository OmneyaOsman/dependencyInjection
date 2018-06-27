package com.omni.dependencyinjection.interfaces;


import com.omni.dependencyinjection.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsServiceApi {

    @GET("everything")
    Call<NewsResponse> getRandomUsers(@Query("q") String newsAbout , @Query("sortBy") String sortBy , @Query("apiKey") String apiKey );
}
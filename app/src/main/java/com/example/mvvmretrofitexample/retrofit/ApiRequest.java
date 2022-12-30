package com.example.mvvmretrofitexample.retrofit;

import static com.example.mvvmretrofitexample.constants.AppConstant.API_KEY;


import com.example.mvvmretrofitexample.response.ArticleResponse;
import com.google.gson.internal.bind.util.ISO8601Utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("top-headlines")
    Call<ArticleResponse> getTopHeadLines(
//            @Path("us") String country,
//            @Path("business") String category
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
}

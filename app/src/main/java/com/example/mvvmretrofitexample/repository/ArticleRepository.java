package com.example.mvvmretrofitexample.repository;

import static com.example.mvvmretrofitexample.constants.AppConstant.API_KEY;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmretrofitexample.model.Article;
import com.example.mvvmretrofitexample.response.ArticleResponse;
import com.example.mvvmretrofitexample.retrofit.ApiRequest;
import com.example.mvvmretrofitexample.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private static final String TAG = ArticleRepository.class.getSimpleName();
    private final ApiRequest apiRequest;

    public ArticleRepository() {
        this.apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ArticleResponse> getDashBoardNews(String country, String category){
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();

        apiRequest.getTopHeadLines(country, category, API_KEY)

                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        System.out.println(response.body());
                        if(response.body() != null) {
                            data.setValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }
}

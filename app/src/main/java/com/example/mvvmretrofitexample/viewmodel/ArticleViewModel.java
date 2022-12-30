package com.example.mvvmretrofitexample.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmretrofitexample.repository.ArticleRepository;
import com.example.mvvmretrofitexample.response.ArticleResponse;

public class ArticleViewModel extends AndroidViewModel {

        private ArticleRepository articleRepository;
        private LiveData<ArticleResponse> articleResponseLiveData;
        public static String country = "br", category;

    public static void setCountry(String country) {
        ArticleViewModel.country = country;
    }

    public static void setCategory(String category) {
        ArticleViewModel.category = category;
    }

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        this.articleRepository = new ArticleRepository();
    }

    public LiveData<ArticleResponse> getBashBoardNewsResponseLiveData(){
        this.articleResponseLiveData = articleRepository.getDashBoardNews(country, category);
        System.out.println(country);
        return articleResponseLiveData;
    }
}

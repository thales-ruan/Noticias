package com.example.mvvmretrofitexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.mvvmretrofitexample.R;
import com.example.mvvmretrofitexample.adapter.ArticleAdapter;
import com.example.mvvmretrofitexample.databinding.ActivityMainBinding;
import com.example.mvvmretrofitexample.model.Article;
import com.example.mvvmretrofitexample.viewmodel.ArticleViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = MainActivity.class.getSimpleName();
    private LinearLayoutManager layoutManager;
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    ArticleViewModel articleViewModel;
    private ArticleAdapter adapter;
    static final List<String> country = new ArrayList<>();
    static final List<String> categories = new ArrayList<>();
    public static String sigla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        getArticles();
        country.add(0,"Brasil");
        country.add(1,"México");
        country.add(2,"Austrália");
        country.add(3,"EUA");

        categories.add(0, "business");
        categories.add(1, "entertainment");
        categories.add(2, "general");
        categories.add(3, "health");
        categories.add(4, "science");
        categories.add(5, "sports");
        categories.add(6, "technology");
    }

    private void getArticles() {
        articleViewModel.getBashBoardNewsResponseLiveData().observe(this,articleResponse -> {
            if(articleResponse != null && articleResponse.getArticles()!= null
            && !articleResponse.getArticles().isEmpty()){

                binding.progressBar.setVisibility(View.GONE);
                List<Article> articleList = articleResponse.getArticles();
                articleArrayList.addAll(articleList);
                adapter.notifyDataSetChanged();

            }
        });
    }

    private void init() {
        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        adapter = new ArticleAdapter(MainActivity.this, articleArrayList);
        binding.recyclerView.setAdapter(adapter);


        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        ArrayAdapter<String> adapterCountries = new ArrayAdapter<>(this, R.layout.dropdown_item, country);
        binding.autoCompleteCountry.setAdapter(adapterCountries);

        ArrayAdapter<String> adapterCategories = new ArrayAdapter<>(this, R.layout.dropdown_item, categories);
        binding.autoCompleteCategory.setAdapter(adapterCategories);

        binding.autoCompleteCountry.setOnItemClickListener((adapterView, view, i, l) -> {
            String countrySelected = country.get(i);
            Toast.makeText(this, countrySelected, Toast.LENGTH_SHORT).show();

            if(countrySelected.equals("Brasil")){
                ArticleViewModel.setCountry("br");
                articleArrayList.clear();
                getArticles();
            }else if(countrySelected.equals("EUA")){
                ArticleViewModel.setCountry("us");
                articleArrayList.clear();
                getArticles();
            }else if(countrySelected.equals("México")){
                ArticleViewModel.setCountry("mx");
                articleArrayList.clear();
                getArticles();
            }else if(countrySelected.equals("Austrália")){
                ArticleViewModel.setCountry("au");
                articleArrayList.clear();
                getArticles();
            }

        });

        binding.autoCompleteCategory.setOnItemClickListener((adapterView, view, i, l) -> {
            String categorySelected = categories.get(i);
            Toast.makeText(this, categorySelected, Toast.LENGTH_SHORT).show();

            if(categorySelected.equals("business")){
                ArticleViewModel.setCategory("business");
                articleArrayList.clear();
                getArticles();
            }else if(categorySelected.equals("entertainment")){
                ArticleViewModel.setCategory("entertainment");
                articleArrayList.clear();
                getArticles();
            }else if(categorySelected.equals("general")){
                ArticleViewModel.setCategory("general");
                articleArrayList.clear();
                getArticles();
            }else if(categorySelected.equals("health")){
                ArticleViewModel.setCategory("health");
                articleArrayList.clear();
                getArticles();
            }else if(categorySelected.equals("science")){
                ArticleViewModel.setCategory("science");
                articleArrayList.clear();
                getArticles();
            }else if(categorySelected.equals("sports")){
                ArticleViewModel.setCategory("sports");
                articleArrayList.clear();
                getArticles();
            }else if(categorySelected.equals("technology")){
                ArticleViewModel.setCategory("technology");
                articleArrayList.clear();
                getArticles();
            }

        });

    }

}
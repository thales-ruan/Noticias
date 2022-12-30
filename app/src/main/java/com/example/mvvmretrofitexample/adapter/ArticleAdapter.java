package com.example.mvvmretrofitexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvmretrofitexample.databinding.NewsItemBinding;
import com.example.mvvmretrofitexample.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private final Context context;
    ArrayList<Article> articleArrayList;

    public ArticleAdapter(Context context, ArrayList<Article> articleArrayList) {
        this.context = context;
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(NewsItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Article article = articleArrayList.get(position);
        holder.binding.tvTitle.setText(article.getTitle());
        Picasso.get()
                .load(article.getUrlToImage()) // Equivalent of what ends up in onBitmapLoaded
                .into (holder.binding.imgViewCover);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private NewsItemBinding binding;

        public MyViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

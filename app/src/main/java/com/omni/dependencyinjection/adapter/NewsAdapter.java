package com.omni.dependencyinjection.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omni.dependencyinjection.MainActivity;
import com.omni.dependencyinjection.R;
import com.omni.dependencyinjection.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RandomUserViewHolder> {

    private final Picasso picasso;
    private final MainActivity mainActivity;
    private List<Article> articleList = new ArrayList<>();


   public NewsAdapter(MainActivity mainActivity, Picasso picasso) {
        this.mainActivity = mainActivity;
        this.picasso = picasso;
    }



    @NonNull
    @Override
    public RandomUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news,
                parent, false);
        return new RandomUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomUserViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.textView.setText( article.getTitle());
        Picasso.get()
                .load(article.getUrlToImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setItems(List<Article> articles) {
        articleList = articles;
        notifyDataSetChanged();
    }

    class RandomUserViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        RandomUserViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}

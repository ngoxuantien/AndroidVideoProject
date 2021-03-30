package com.example.appvideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appvideo.Activity.MovieDetails;
import com.example.appvideo.R;
import com.example.appvideo.model.CategoryItem;

import java.util.List;

public class MovieCastJoinAdapter extends RecyclerView.Adapter<MovieCastJoinAdapter.MainViewHolder> {
    Context context;
    List<CategoryItem> categoryItemList;

    public MovieCastJoinAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_cast_join_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Glide.with(context).load(categoryItemList.get(position).getImageUrl()).into(holder.photojoin);
        holder.namemoviejoin.setText(categoryItemList.get(position).getMovieName());

        holder.photojoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("MovienameID",categoryItemList.get(position).getId());
                intent.putExtra("Moviename",categoryItemList.get(position).getMovieName());
                intent.putExtra("MovieImageUrl",categoryItemList.get(position).getImageUrl());
                intent.putExtra("Backdrop",categoryItemList.get(position).getBackdrop());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        ImageView photojoin;
        TextView namemoviejoin;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            photojoin= itemView.findViewById(R.id.photomoviejoin);
            namemoviejoin= itemView.findViewById(R.id.namemoviejoin);
        }
    }
}

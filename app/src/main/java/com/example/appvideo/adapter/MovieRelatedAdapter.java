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
import com.example.appvideo.Activity.TvShowDetail;
import com.example.appvideo.R;
import com.example.appvideo.model.CategoryItem;

import java.util.List;

public class MovieRelatedAdapter extends RecyclerView.Adapter<MovieRelatedAdapter.MainViewHolder> {
    Context context;
    List<CategoryItem> categoryItemList;

    public MovieRelatedAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.related_movie_itiem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRelatedAdapter.MainViewHolder holder, int position) {
        Glide.with(context).load(categoryItemList.get(position).getImageUrl()).into(holder.photo);
        holder.namemovie.setText(categoryItemList.get(position).getMovieName());

        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /// điều kiện giá trị của biến tvshow để lọc tv và movie
                Intent intent = null ;
                if(categoryItemList.get(position).getTvshow()!=null){
                    intent= new Intent(context, TvShowDetail.class);
                    intent.putExtra("tvshow",categoryItemList.get(position).getTvshow());
                }else{ intent = new Intent(context, MovieDetails.class); }
                /// điều kiện giá trị của biến tvshow để lọc tv và movie


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
        ImageView photo;
        TextView namemovie;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            photo= itemView.findViewById(R.id.photomovierelate);
            namemovie= itemView.findViewById(R.id.namemovierelate);
        }
    }
}

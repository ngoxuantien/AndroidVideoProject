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

public class MovieListHorizontalAdapter extends RecyclerView.Adapter<MovieListHorizontalAdapter.MainViewHoldel> {
    Context context;
    List<CategoryItem> movieListitem;

    public MovieListHorizontalAdapter(Context context, List<CategoryItem> movieListitem) {
        this.context = context;
        this.movieListitem = movieListitem;
    }

    @NonNull
    @Override
    public MainViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHoldel(LayoutInflater.from(context).inflate(R.layout.movie_horizontal_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHoldel holder, int position) {
        Glide.with(context).load(movieListitem.get(position).getImageUrl()).into(holder.photo);
        holder.namemovie.setText(movieListitem.get(position).getMovieName());

        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /// điều kiện giá trị của biến tvshow để lọc tv và movie
                Intent intent = null ;
                if(movieListitem.get(position).getTvshow()!=null){
                    intent= new Intent(context, TvShowDetail.class);
                    intent.putExtra("tvshow",movieListitem.get(position).getTvshow());
                }else{ intent = new Intent(context, MovieDetails.class); }
                /// điều kiện giá trị của biến tvshow để lọc tv và movie


                intent.putExtra("MovienameID",movieListitem.get(position).getId());
                intent.putExtra("Moviename",movieListitem.get(position).getMovieName());
                intent.putExtra("MovieImageUrl",movieListitem.get(position).getImageUrl());
                intent.putExtra("Backdrop",movieListitem.get(position).getBackdrop());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieListitem.size();
    }

    public class MainViewHoldel extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView namemovie;
        public MainViewHoldel(@NonNull View itemView) {
            super(itemView);
            photo= itemView.findViewById(R.id.photomovie);
            namemovie= itemView.findViewById(R.id.namemovie);
        }
    }
}

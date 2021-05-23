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

public class ViewPage2Adapter extends RecyclerView.Adapter<ViewPage2Adapter.ViewHolder> {
    private Context context;

    public ViewPage2Adapter(Context context, List<CategoryItem> categoryItems) {
        this.context = context;
        this.categoryItems = categoryItems;
    }

    private List<CategoryItem> categoryItems;

    @NonNull
    @Override
    public ViewPage2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.banner_movie_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPage2Adapter.ViewHolder holder, int position) {

        holder.namemovietv.setText(categoryItems.get(position).getMovieName());
        holder.reviewtv.setText(categoryItems.get(position).getSummary());
        Glide.with(context).load(categoryItems.get(position).getBackdrop()).into(holder.bannerImage);


        holder.bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// điều kiện giá trị của biến tvshow để lọc tv và movie
                Intent intent = null;
                if (categoryItems.get(position).getTvshow() != null) {
                    intent = new Intent(context, TvShowDetail.class);
                    intent.putExtra("tvshow", categoryItems.get(position).getTvshow());
                } else {
                    intent = new Intent(context, MovieDetails.class);
                }

                /// điều kiện giá trị của biến tvshow để lọc tv và movie
                intent.putExtra("MovienameID", categoryItems.get(position).getId());
                intent.putExtra("Moviename", categoryItems.get(position).getMovieName());
                intent.putExtra("MovieImageUrl", categoryItems.get(position).getImageUrl());
                intent.putExtra("Backdrop", categoryItems.get(position).getBackdrop());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bannerImage;
        TextView namemovietv;
        TextView reviewtv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.Banner_image);
            namemovietv = itemView.findViewById(R.id.namemovietv);
            reviewtv = itemView.findViewById(R.id.reviewtv);
        }
    }
}

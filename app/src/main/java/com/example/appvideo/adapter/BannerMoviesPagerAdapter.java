package com.example.appvideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.appvideo.Activity.MovieDetails;
import com.example.appvideo.Activity.TvShowDetail;
import com.example.appvideo.R;
import com.example.appvideo.model.CategoryItem;

import java.util.List;

public class BannerMoviesPagerAdapter extends PagerAdapter {

    Context context;
    List<CategoryItem> categoryItems;

    public BannerMoviesPagerAdapter(Context context, List<CategoryItem> categoryItems) {
        this.context = context;
        this.categoryItems = categoryItems;
    }

    @Override
    public int getCount() {
        return categoryItems.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View )object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
     View view = LayoutInflater.from(context).inflate(R.layout.banner_movie_layout,null);
        ImageView bannerImage= view.findViewById(R.id.Banner_image);
        TextView namemovietv= view.findViewById(R.id.namemovietv);
        TextView reviewtv= view.findViewById(R.id.reviewtv);
        namemovietv.setText(categoryItems.get(position).getMovieName());
        reviewtv.setText(categoryItems.get(position).getSummary());
        Glide.with(context).load(categoryItems.get(position).getBackdrop()).into(bannerImage);
        container.addView(view);

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// điều kiện giá trị của biến tvshow để lọc tv và movie
                Intent intent = null ;
                if(categoryItems.get(position).getTvshow()!=null){
                    intent= new Intent(context, TvShowDetail.class);
                    intent.putExtra("tvshow",categoryItems.get(position).getTvshow());
                }else{ intent = new Intent(context, MovieDetails.class); }

                /// điều kiện giá trị của biến tvshow để lọc tv và movie
                intent.putExtra("MovienameID", categoryItems.get(position).getId());
                intent.putExtra("Moviename", categoryItems.get(position).getMovieName());
                intent.putExtra("MovieImageUrl", categoryItems.get(position).getImageUrl());
                intent.putExtra("Backdrop",categoryItems.get(position).getBackdrop());
                context.startActivity(intent);

            }
        });




        return view;
    }
}

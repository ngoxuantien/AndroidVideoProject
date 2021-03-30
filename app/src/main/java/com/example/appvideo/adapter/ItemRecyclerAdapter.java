package com.example.appvideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {


    Context context;
    List<CategoryItem> categoryItems;

    public Context getContext() {
        return context;
    }

    public ItemRecyclerAdapter(Context context, List<CategoryItem> categoryItems) {
        this.context = context;
        this.categoryItems = categoryItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_recycler_row_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Glide.with(context).load(categoryItems.get(position).getImageUrl()).into(holder.itemImage);
        holder.textView.setText(categoryItems.get(position).getMovieName());
//



   holder.itemImage.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           /// điều kiện giá trị của biến tvshow để lọc tv và movie
           Intent intent = null ;
           if(categoryItems.get(position).getTvshow()!=null){
               intent= new Intent(context, TvShowDetail.class);
               intent.putExtra("tvshow",categoryItems.get(position).getTvshow());
           }else{ intent = new Intent(context, MovieDetails.class); }

           /// điều kiện giá trị của biến tvshow để lọc tv và movie

           intent.putExtra("MovienameID",categoryItems.get(position).getId());
           intent.putExtra("Moviename",categoryItems.get(position).getMovieName());
           intent.putExtra("MovieImageUrl",categoryItems.get(position).getImageUrl());
           intent.putExtra("Backdrop",categoryItems.get(position).getBackdrop());
           context.startActivity(intent);
       }
   });
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }


    public static final class ItemViewHolder extends RecyclerView.ViewHolder{
ImageView itemImage;
TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);


            itemImage= itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.movienametv);



        }
    }
}

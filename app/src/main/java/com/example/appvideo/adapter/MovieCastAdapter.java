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
import com.example.appvideo.Activity.ActivityInformationCast;
import com.example.appvideo.R;
import com.example.appvideo.model.Cast;

import java.util.ArrayList;
import java.util.List;

public class MovieCastAdapter extends RecyclerView.Adapter<MovieCastAdapter.MainViewHolder> {

    List<Cast> castList = new ArrayList<>();
    Context context;

    public Context getContext() {
        return context;
    }

    public MovieCastAdapter(Context context, List<Cast> castList) {
        this.castList = castList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_cast_itime, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCastAdapter.MainViewHolder holder, int position) {
        Glide.with(context).load(castList.get(position).getImagecast()).into(holder.photocast);
        holder.namecast.setText(castList.get(position).getName());
        holder.photocast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityInformationCast.class);
                intent.putExtra("IDCast",castList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public final static class MainViewHolder extends RecyclerView.ViewHolder {
        ImageView photocast;
        TextView namecast;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            photocast = itemView.findViewById(R.id.castphotoitem);
            namecast = itemView.findViewById(R.id.namecastitem);
        }
    }
}

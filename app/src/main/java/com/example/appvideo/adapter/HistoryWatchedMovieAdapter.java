package com.example.appvideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appvideo.R;
import com.example.appvideo.model.HistoryMovieWatched;

import java.util.List;

public class HistoryWatchedMovieAdapter extends RecyclerView.Adapter<HistoryWatchedMovieAdapter.ViewHolder> {
    private Context context;
    private List<HistoryMovieWatched> watchedList;

    public HistoryWatchedMovieAdapter(Context context, List<HistoryMovieWatched> watchedList) {
        this.context = context;
        this.watchedList = watchedList;
    }

    @NonNull
    @Override
    public HistoryWatchedMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie_watched,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryWatchedMovieAdapter.ViewHolder holder, int position) {
        holder.name.setText(watchedList.get(position).getName().toString());
        holder.review.setText(watchedList.get(position).getReview().toString());
        Glide.with(context).load(watchedList.get(position).getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return watchedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, review;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imagewatched);
            name= itemView.findViewById(R.id.namewatched);
            review= itemView.findViewById(R.id.reviewWatched);

        }
    }
}

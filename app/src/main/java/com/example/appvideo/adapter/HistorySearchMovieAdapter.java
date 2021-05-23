package com.example.appvideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvideo.R;
import com.example.appvideo.model.HistorySearch;

import org.w3c.dom.Text;

import java.util.ConcurrentModificationException;
import java.util.List;

public class HistorySearchMovieAdapter extends RecyclerView.Adapter<HistorySearchMovieAdapter.ViewHolder> {
    private Context context;
    private List<HistorySearch> list;

    public HistorySearchMovieAdapter(Context context, List<HistorySearch> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HistorySearchMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistorySearchMovieAdapter.ViewHolder holder, int position) {
        holder.tile.setText(list.get(position).getTitle());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tile;
        ImageView delete;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tile = itemView.findViewById(R.id.titleSearch);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}

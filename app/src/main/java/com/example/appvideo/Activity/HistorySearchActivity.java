package com.example.appvideo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appvideo.R;
import com.example.appvideo.adapter.HistorySearchMovieAdapter;
import com.example.appvideo.adapter.HistoryWatchedMovieAdapter;
import com.example.appvideo.model.HistoryMovieWatched;
import com.example.appvideo.model.HistorySearch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistorySearchActivity extends AppCompatActivity {
    private List<HistorySearch> list;
    private RecyclerView recyclerView;
    private HistorySearchMovieAdapter historySearchMovieAdapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_search);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("historySearch");
        list = new ArrayList<>();


        setHistorySearch();
    }

    private void setrecyclerviewAdapter(List<HistorySearch> list) {
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistorySearchActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        historySearchMovieAdapter = new HistorySearchMovieAdapter(this, list);
        recyclerView.setAdapter(historySearchMovieAdapter);
    }

    private void setHistorySearch() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    list.add(dataSnapshot.getValue(HistorySearch.class));

                }
                setrecyclerviewAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
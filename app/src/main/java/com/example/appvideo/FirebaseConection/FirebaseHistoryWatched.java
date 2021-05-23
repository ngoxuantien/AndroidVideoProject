package com.example.appvideo.FirebaseConection;

import com.example.appvideo.model.HistoryMovieWatched;
import com.example.appvideo.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHistoryWatched {
    private DatabaseReference databaseReference;
    public FirebaseHistoryWatched() {

    }


    public void createUserObject() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("historyWatched");
    }

    public void addUserObject(HistoryMovieWatched historyMovieWatched) {
        databaseReference = FirebaseDatabase.getInstance().getReference("historyWatched");

        databaseReference.child(historyMovieWatched.getId() + "").setValue(historyMovieWatched);


    }
}

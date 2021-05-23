package com.example.appvideo.FirebaseConection;

import com.example.appvideo.model.HistorySearch;
import com.example.appvideo.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHistorySearch {
    private DatabaseReference databaseReference;

    public FirebaseHistorySearch() {

    }

    /// hàm tạo 1 lớp đại diện là users chứa các list các tài khoản///
    public void createUserObject() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("historySearch");
    }

    //// hàm thêm tài khoản//
    public void addUserObject(HistorySearch historySearch) {
        databaseReference = FirebaseDatabase.getInstance().getReference("historySearch");

        databaseReference.child(historySearch.getId() + "").setValue(historySearch);


    }

}

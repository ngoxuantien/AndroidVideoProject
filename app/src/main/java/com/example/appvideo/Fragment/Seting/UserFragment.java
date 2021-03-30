package com.example.appvideo.Fragment.Seting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appvideo.Fragment.ChangePFragment;
import com.example.appvideo.R;

public class UserFragment extends Fragment {
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_userinfor,container,false);
      button =  view.findViewById(R.id.repairP);
      setButton(view);

      return view;
    }
    private  void setButton(View view){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutuser,new ChangePFragment()).commit();
            }
        });
    }
}

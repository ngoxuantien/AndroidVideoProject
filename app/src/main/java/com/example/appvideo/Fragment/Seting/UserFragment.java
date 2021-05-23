package com.example.appvideo.Fragment.Seting;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appvideo.Activity.MainActivity;
import com.example.appvideo.Fragment.ChangePFragment;
import com.example.appvideo.R;
import com.example.appvideo.Session.Session;
import com.example.appvideo.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserFragment extends Fragment implements Serializable {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser userfb = auth.getCurrentUser();
    private User user = new User();

    private Button updateUser;
    private RelativeLayout button;
    private TextView textView1, textView2, textView3, textView4;
    private Spinner textView5;
    private EditText editText1, editText2, editText3, editText4, editText5;
    private ImageView edit1, edit2, edit3, edit4, edit5;
    private ViewSwitcher viewSwitcher1, viewSwitcher2, viewSwitcher3, viewSwitcher4, viewSwitcher5;
    private DatabaseReference databaseReference;
    private Bundle bundle = new Bundle();
    private com.example.appvideo.FirebaseConection.FirebaseUser firebaseUser;
    private Session session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfor, container, false);
        firebaseUser = new com.example.appvideo.FirebaseConection.FirebaseUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        button = view.findViewById(R.id.repairP);
        updateUser = view.findViewById(R.id.updateUser);
        session = new Session(view.getContext());

        textView1 = view.findViewById(R.id.text1);
        textView2 = view.findViewById(R.id.text2);
        textView3 = view.findViewById(R.id.text3);
        textView4 = view.findViewById(R.id.text4);
        textView5 = view.findViewById(R.id.text5);

        editText1 = view.findViewById(R.id.editext1);
        editText2 = view.findViewById(R.id.editext2);
        editText3 = view.findViewById(R.id.editext3);
        editText4 = view.findViewById(R.id.editext4);


        edit1 = view.findViewById(R.id.edit1);
        edit2 = view.findViewById(R.id.edit2);
        edit3 = view.findViewById(R.id.edit3);
        edit4 = view.findViewById(R.id.edit4);
        edit5 = view.findViewById(R.id.edit5);

        String[] arayy = getResources().getStringArray(R.array.gioitinh);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, arayy);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textView5.setAdapter(arrayAdapter);
        if (session.getTypeUser() == 1) {
            //   user = (User) getArguments().getSerializable("User");//// lấy dữ liệu từ bundle
            user = session.getSession();
            //    bundle.putSerializable("user",user);
            Toast.makeText(view.getContext(), "Lỗihhhhhh" + user.getNameuser(), Toast.LENGTH_SHORT).show();
            editText1.setText(user.getNameuser().toString());
            editText2.setText(user.getEmailuser().toString());
            editText3.setText(user.getDateofbirthuser().toString());
            editText4.setText(user.getPlaceuser().toString());

            textView1.setText(user.getNameuser().toString());
            textView2.setText(user.getEmailuser().toString());
            textView3.setText(user.getDateofbirthuser().toString());
            textView4.setText(user.getPlaceuser().toString());
            if (user.getGenderuser().toString().equals("Nam")) {
                textView5.setSelection(0);
            } else {
                textView5.setSelection(1);
            }


        } else {
            ///// lấy dữ liệu từ facebook/////
            editText1.setText(userfb.getDisplayName());
            editText1.setText(userfb.getEmail());
        }


        setButton(view);
        setEdit1(view);
        setEdit2(view);
        setEdit3(view);
        setEdit4(view);

        setClickBTupdate(view);
        return view;
    }

    private void setClickBTupdate(View view) {


        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog(view, "Thông Báo", "Bạn đã lưu thành công");


                firebaseUser.updateUserObject(new User(user.getId(), textView1.getText().toString(), textView2.getText().toString(), textView3.getText().toString(), textView4.getText().toString(), textView5.getSelectedItem().toString(), user.getPassword()));


                User useredit = new User(user.getId(), textView1.getText().toString(), textView2.getText().toString(), textView3.getText().toString(), textView4.getText().toString(), textView5.getSelectedItem().toString(), user.getPassword());
                session.clearSession();
                session.createSession(useredit);


            }


        });

    }

    private void ShowDialog(View view, String title1, String tv21) {
        ImageView imageDialog;
        Button ok;
        TextView title, tv2;
        Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialog_notification_error);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        title = dialog.findViewById(R.id.titleDialog);
        tv2 = dialog.findViewById(R.id.error_text);
        ok = dialog.findViewById(R.id.button_dialog);
        imageDialog = dialog.findViewById(R.id.imagedialog);
        title.setText(title1.toString());
        tv2.setText(tv21);
        imageDialog.setImageResource(R.drawable.susscess);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);// tìm hiểu thêm
        dialog.show();


    }

    private void setEdit1(View view) {
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView1.setText(editText1.getText().toString());
                viewSwitcher1 = view.findViewById(R.id.viewSwitcher1);
                viewSwitcher1.showNext();
            }
        });


    }

    private void setEdit2(View view) {
        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView2.setText(editText2.getText().toString());
                viewSwitcher2 = view.findViewById(R.id.viewSwitcher2);
                viewSwitcher2.showNext();
            }
        });


    }

    private void setEdit3(View view) {
        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView3.setText(editText3.getText().toString());
                viewSwitcher3 = view.findViewById(R.id.viewSwitcher3);
                viewSwitcher3.showNext();
            }
        });


    }

    private void setEdit4(View view) {
        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView4.setText(editText4.getText().toString());
                viewSwitcher4 = view.findViewById(R.id.viewSwitcher4);
                viewSwitcher4.showNext();
            }
        });


    }

    private void setEdit5(View view) {
//        edit5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                viewSwitcher5 = view.findViewById(R.id.viewSwitcher5);
//                viewSwitcher5.showNext();
//            }
//        });


    }

    private void setButton(View view) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = null;

                fragment2 = new ChangePFragment();
                //   fragment2.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutuser, fragment2).addToBackStack(null).commit();
            }
        });
    }
}

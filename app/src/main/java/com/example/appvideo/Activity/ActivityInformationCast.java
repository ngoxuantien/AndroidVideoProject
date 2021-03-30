package com.example.appvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.appvideo.Json.Json;
import com.example.appvideo.R;
import com.example.appvideo.adapter.MovieCastJoinAdapter;
import com.example.appvideo.model.AllCategory;
import com.example.appvideo.model.Cast;
import com.example.appvideo.model.CategoryItem;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ActivityInformationCast extends Activity {
    ImageView searchImage;
    MovieCastJoinAdapter movieCastJoinAdapter;
    RecyclerView recyclerViewjoin;
    List<CategoryItem> categoryItemListjoin;
    List<Cast> castList;
    TextView namecast, sexcast, strorycast, dateofbirthcast, placeCast;
    ImageView nyminh;


    int ID;
    Json json = new Json();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_information_cast);

        namecast = findViewById(R.id.namecast);
        dateofbirthcast = findViewById(R.id.date_of_birthcast);
        placeCast = findViewById(R.id.place_cast);
        sexcast = findViewById(R.id.sexcast);
        strorycast = findViewById(R.id.storycast);
        nyminh = findViewById(R.id.nyminh);
        ID = getIntent().getExtras().getInt("IDCast");
        this.jsonRelated();
        // namecast.setText("hoahoahaoa");
        this.jsoninformation();

    }

    private void jsoninformation() {
        RequestQueue requestQueue = Volley.newRequestQueue(ActivityInformationCast.this);


        castList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/person/" + ID + "?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=en-US&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            castList = json.JSONcastDetail(response);
                            namecast.setText(castList.get(0).getName());
                            dateofbirthcast.setText(castList.get(0).getDate());
                            placeCast.setText(castList.get(0).getPlace());
                            sexcast.setText(castList.get(0).getSex());
                            strorycast.setText(castList.get(0).getStory());
                            Glide.with(ActivityInformationCast.this).load(castList.get(0).getImagecast()).into(nyminh);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityInformationCast.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void jsonRelated() {
        RequestQueue requestQueue = Volley.newRequestQueue(ActivityInformationCast.this);


        categoryItemListjoin = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/person/" + ID + "/movie_credits?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-Vi",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            categoryItemListjoin = json.JSONmovieJoin(response);
                            setJoinRecyclerview(categoryItemListjoin);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityInformationCast.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }


    private void setJoinRecyclerview(List<CategoryItem> categoryItemList) {
        recyclerViewjoin = findViewById(R.id.infomationCast);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerViewjoin.setLayoutManager(layoutManager);
        movieCastJoinAdapter = new MovieCastJoinAdapter(this, categoryItemList);
        recyclerViewjoin.setAdapter(movieCastJoinAdapter);
    }


}
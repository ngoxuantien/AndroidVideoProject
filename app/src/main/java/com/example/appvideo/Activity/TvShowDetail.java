package com.example.appvideo.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import com.example.appvideo.adapter.MovieCastAdapter;
import com.example.appvideo.adapter.MovieRelatedAdapter;
import com.example.appvideo.model.Cast;
import com.example.appvideo.model.CategoryItem;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TvShowDetail extends Activity {
    TextView moviename;

    ImageView movieImage;
    private String keymovie;
    Button playButton;
    RecyclerView mainRecyclerview;
    MovieCastAdapter movieCastAdapter;
    RecyclerView relatedRecyclerview;
    MovieRelatedAdapter movieRelatedAdapter;
    List<CategoryItem> categoryItemList;
    List<Cast> castList;
    CategoryItem movieDetailinfomation;
    TextView movieTime, movieVote, movieCategory, movieSummary, movieDate, movieTitle;
    int mID;
    Json json = new Json();
    String k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tv_show_detail);

        moviename = findViewById(R.id.tvshow_name);
        movieImage = findViewById(R.id.tvshow_image_detail);
        playButton = findViewById(R.id.play_button);
//////
        movieTime = findViewById(R.id.timetvshow);
        movieVote = findViewById(R.id.tvshowVote);
        movieCategory = findViewById(R.id.tvshowCategory);
        movieSummary = findViewById(R.id.tvshowSummary);
        movieDate = findViewById(R.id.tvshowDate);
        movieTitle = findViewById(R.id.tvshowTitle);


        // get data from Intent
        mID = getIntent().getExtras().getInt("MovienameID");
        String mname = getIntent().getStringExtra("Moviename");
        String mImage = getIntent().getStringExtra("MovieImageUrl");
        String mFileUrl = getIntent().getStringExtra("Backdrop");
        Glide.with(this).load(mFileUrl).into(movieImage);

        this.getmoviekey(mID);


        moviename.setText(mname);


        this.jsonmovieDetail();
        this.testhhh();
        this.jsonRelated();


        this.setPlayButton();

    }

    private void setPlayButton() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TvShowDetail.this, PlaymovieActivity.class);
                i.putExtra("url", keymovie);
                startActivity(i);
            }
        });
    }

    private void getmoviekey(int kh) {

        Toast.makeText(TvShowDetail.this, kh + "", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(TvShowDetail.this);



        keymovie = "rong";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/tv/" + kh + "/videos?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-Vi",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            keymovie = json.JSONmovieTrailer(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TvShowDetail.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }

    private void testhhh() {
        RequestQueue requestQueue = Volley.newRequestQueue(TvShowDetail.this);

        castList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/tv/" + mID + "/credits?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-Vi&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            castList = json.JSONcastlist(response);
                            setMainRecyclerview(castList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TvShowDetail.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void jsonmovieDetail() {
        RequestQueue requestQueue = Volley.newRequestQueue(TvShowDetail.this);
        movieDetailinfomation = new CategoryItem();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, " https://api.themoviedb.org/3/tv/" + mID + "?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=en-US",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            movieDetailinfomation = json.JSONTvDetail(response);
                            movieTime.setText(movieDetailinfomation.getTime());
                            movieCategory.setText(movieDetailinfomation.getGenres());
                            movieSummary.setText(movieDetailinfomation.getSummary());
                            movieDate.setText(movieDetailinfomation.getYearOfRelease());
                            movieTitle.setText(movieDetailinfomation.getTitle());
                            movieVote.setText(movieDetailinfomation.getVote());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TvShowDetail.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }

    private void jsonRelated() {
        RequestQueue requestQueue = Volley.newRequestQueue(TvShowDetail.this);



        categoryItemList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/tv/" + mID + "/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            categoryItemList = json.JSONTvshowlist(response);
                            setRelatedRecyclerview(categoryItemList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TvShowDetail.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void setMainRecyclerview(List<Cast> castList) {
        mainRecyclerview = findViewById(R.id.castrecyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mainRecyclerview.setLayoutManager(layoutManager);
        movieCastAdapter = new MovieCastAdapter(this, castList);
        mainRecyclerview.setAdapter(movieCastAdapter);

    }

    private void setRelatedRecyclerview(List<CategoryItem> castList) {
        relatedRecyclerview = findViewById(R.id.relatedlistmovie);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        relatedRecyclerview.setLayoutManager(layoutManager);
        movieRelatedAdapter = new MovieRelatedAdapter(this, castList);
        relatedRecyclerview.setAdapter(movieRelatedAdapter);

    }

}
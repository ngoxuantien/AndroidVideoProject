package com.example.appvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appvideo.Json.Json;
import com.example.appvideo.R;
import com.example.appvideo.adapter.MovieListHorizontalAdapter;
import com.example.appvideo.adapter.MovieRelatedAdapter;
import com.example.appvideo.model.CategoryItem;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    RecyclerView recyclerViewsearch1, recyclerViewsearch2;
    MovieRelatedAdapter movieRelatedAdapter1, movieRelatedAdapter2;
    Json json = new Json();
    EditText editTextSearch;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);


        editTextSearch = findViewById(R.id.editextSearch);
        button = findViewById(R.id.btsearch);

        this.setOnClickbtSearch();

    }
    private  void setOnClickbtSearch(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = editTextSearch.getText().toString();
               searchMovie(t);searchTvShow(t);


            }
        });
    }

    private void searchMovie(String keyValue) {
        RequestQueue requestQueue = Volley.newRequestQueue(SearchActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/search/movie?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&query=" + keyValue + "",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<CategoryItem> movieList = new ArrayList<>();
                            movieList = json.JSONmovielist(response);
                            setMovieListRecyclerview(movieList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void searchTvShow(String keyValue) {
        RequestQueue requestQueue = Volley.newRequestQueue(SearchActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/search/tv?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&query=" + keyValue + "",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<CategoryItem> TvShowlList = new ArrayList<>();
                            TvShowlList = json.JSONTvshowlist(response);

                            setTvShowRecyclerview(TvShowlList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void setMovieListRecyclerview(List<CategoryItem> movieList) {
        recyclerViewsearch1 = findViewById(R.id.recyclersearch1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this, RecyclerView.HORIZONTAL, false);
        recyclerViewsearch1.setLayoutManager(layoutManager);
        movieRelatedAdapter1 = new MovieRelatedAdapter(SearchActivity.this, movieList);
        recyclerViewsearch1.setAdapter(movieRelatedAdapter1);

    }

    private void setTvShowRecyclerview(List<CategoryItem> TvShowList) {
        recyclerViewsearch2 = findViewById(R.id.recyclersearch2);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(SearchActivity.this, RecyclerView.HORIZONTAL, false);
        recyclerViewsearch2.setLayoutManager(layoutManager1);
        movieRelatedAdapter2 = new MovieRelatedAdapter(SearchActivity.this, TvShowList);
        recyclerViewsearch2.setAdapter(movieRelatedAdapter2);

    }


}
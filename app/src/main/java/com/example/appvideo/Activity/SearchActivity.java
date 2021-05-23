package com.example.appvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appvideo.FirebaseConection.FirebaseHistorySearch;
import com.example.appvideo.Json.Json;
import com.example.appvideo.R;
import com.example.appvideo.adapter.MovieListHorizontalAdapter;
import com.example.appvideo.adapter.MovieRelatedAdapter;
import com.example.appvideo.model.CategoryItem;
import com.example.appvideo.model.HistorySearch;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchActivity extends Activity {

    private RecyclerView recyclerViewsearch1, recyclerViewsearch2;
    private MovieRelatedAdapter movieRelatedAdapter1, movieRelatedAdapter2;
    private Json json = new Json();
    private EditText editTextSearch;
    private Button button;
    private TextView textView1, textView2;
    private FirebaseHistorySearch firebaseHistorySearch;
private  Handler mainThreadHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);


        editTextSearch = findViewById(R.id.editextSearch);
        button = findViewById(R.id.btsearch);
        textView1 = findViewById(R.id.movieshearch);
        textView2 = findViewById(R.id.movieshearch2);

       this.setOnClickbtSearch();

    }
  
    private void setOnClickbtSearch() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) SearchActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                //Find the currently focused view, so we can grab the correct window token from it.
                View view = SearchActivity.this.getCurrentFocus();
                //If no view currently has focus, create a new one, just so we can grab a window token from it
                if (view == null) {
                    view = new View(SearchActivity.this);
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                String t = editTextSearch.getText().toString();
           //     postTaskInsideBackgroundTask();

                searchTvShow(t);

                searchMovie(t);


                firebaseHistorySearch = new FirebaseHistorySearch();
                firebaseHistorySearch.createUserObject();
                Random random = new Random();
                int d = random.nextInt(99999);
                firebaseHistorySearch.addUserObject(new HistorySearch(d, t));

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
        textView1.setText("Phim lẻ được tìm thấy");


    }

    private void setTvShowRecyclerview(List<CategoryItem> TvShowList) {
        recyclerViewsearch2 = findViewById(R.id.recyclersearch2);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(SearchActivity.this, RecyclerView.HORIZONTAL, false);
        recyclerViewsearch2.setLayoutManager(layoutManager1);
        movieRelatedAdapter2 = new MovieRelatedAdapter(SearchActivity.this, TvShowList);
        recyclerViewsearch2.setAdapter(movieRelatedAdapter2);
        textView2.setText("Phim bộ được tìm thấy");

    }


}
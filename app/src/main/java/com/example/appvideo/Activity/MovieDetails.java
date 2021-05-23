package com.example.appvideo.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.appvideo.FirebaseConection.FirebaseHistoryWatched;
import com.example.appvideo.Json.Json;
import com.example.appvideo.R;
import com.example.appvideo.adapter.MovieCastAdapter;
import com.example.appvideo.adapter.MovieRelatedAdapter;
import com.example.appvideo.model.Cast;
import com.example.appvideo.model.CategoryItem;
import com.example.appvideo.model.HistoryMovieWatched;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MovieDetails extends Activity {

    private TextView moviename;
    private ImageView movieImage, likemovie;
    private String keymovie;
    private Button playButton;
    private RecyclerView mainRecyclerview;
    private MovieCastAdapter movieCastAdapter;
    private RecyclerView relatedRecyclerview;
    private MovieRelatedAdapter movieRelatedAdapter;
    private List<CategoryItem> categoryItemList;
    private List<Cast> castList;
    private CategoryItem movieDetailinfomation;
    private TextView movieTime, movieVote, movieCategory, movieSummary, movieDate, movieTitle;
    int tvshow;
    int mID;
    private Json json = new Json();
    private String k;
    private FirebaseHistoryWatched firebaseHistoryWatched;
    private String namemovie;
    private int anhlike = 1;
    private ImageView ratingim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_movie_details);

        moviename = findViewById(R.id.movie_name);
        movieImage = findViewById(R.id.movie_image_detail);
        playButton = findViewById(R.id.play_button);
        likemovie = findViewById(R.id.likemovie);
        ratingim = findViewById(R.id.ratingim);

//////
        movieTime = findViewById(R.id.timemovie);
        movieVote = findViewById(R.id.movievote);
        movieCategory = findViewById(R.id.movieCategory);
        movieSummary = findViewById(R.id.movieSummary);
        movieDate = findViewById(R.id.movieDate);
        movieTitle = findViewById(R.id.movieTitle);


        firebaseHistoryWatched = new FirebaseHistoryWatched();
        firebaseHistoryWatched.createUserObject();

        // get data from Intent
        mID = getIntent().getExtras().getInt("MovienameID");
        String mname = getIntent().getStringExtra("Moviename");
        String mImage = getIntent().getStringExtra("MovieImageUrl");
        String mFileUrl = getIntent().getStringExtra("Backdrop");
        tvshow = getIntent().getExtras().getInt("tvshow");
        namemovie = mname;
        this.getmoviekey(mID);

        Glide.with(this).load(mFileUrl).into(movieImage);
        moviename.setText(mname);


        this.jsonmovieDetail();
        this.testhhh();
        this.jsonRelated();

        setClicklikemovie();

        this.setPlayButton();
        setClickRatingitem();

    }

    private void setClickRatingitem() {
        ratingim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog(v);
            }
        });

    }

    private void ShowDialog(View view) {

        Button ratingbt,deleterating;
        RatingBar ratingBar;
        Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialog_choose_rating);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        deleterating= dialog.findViewById(R.id.deleterating);
        ratingbt = dialog.findViewById(R.id.ratingbt);
        ratingBar = dialog.findViewById(R.id.ratingitembar);
        ratingbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetails.this);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String url = "https://api.themoviedb.org/3/movie/" + mID + "/rating?api_key=9ed4a1f097a3e78ed51133843d2156ea&session_id=59489d368f113315f024f7a5e390730070cecf6c";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("value", rating + "");
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });
        deleterating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.themoviedb.org/3/movie/" + mID + "/rating?api_key=9ed4a1f097a3e78ed51133843d2156ea&session_id=59489d368f113315f024f7a5e390730070cecf6c";
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    }
                }) {

                };
                requestQueue.add(stringRequest);

                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);// tìm hiểu thêm
        dialog.show();


    }

    private void setClicklikemovie() {
        likemovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(MovieDetails.this);
                if (anhlike == 1) {
                    likemovie.setImageResource(R.drawable.like);
                    anhlike = 2;
                } else {
                    likemovie.setImageResource(R.drawable.like1);
                    anhlike = 1;
                }
                JSONObject object2 = new JSONObject();
                try {

                    object2.put("media_type", "movie");
                    object2.put("media_id", mID);
                    if (anhlike == 1) {
                        object2.put("favorite", false);
                    } else {
                        object2.put("favorite", true);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = "https://api.themoviedb.org/3/account/10144263/favorite?api_key=9ed4a1f097a3e78ed51133843d2156ea&session_id=59489d368f113315f024f7a5e390730070cecf6c";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object2,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ) {
                    @Override       //Send Header
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("api_call_header", "header_value");

                        return params;
                    }
                };

                requestQueue.add(request);

            }
        });
    }

    private void setPlayButton() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetails.this, PlaymovieActivity.class);
                i.putExtra("url", keymovie);
                startActivity(i);
            }
        });
    }

    private void getmoviekey(int kh) {

        Toast.makeText(MovieDetails.this, kh + "", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetails.this);
        String catagory = "movie";
        if (tvshow == 1) {
            catagory = "tv";
        }

        keymovie = "rong";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/" + catagory + "/" + kh + "/videos?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN",
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
                Toast.makeText(MovieDetails.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }

    private void testhhh() {
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetails.this);

        String catagory = "movie";
        if (tvshow == 1) {
            catagory = "tv";
        }

        castList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/" + catagory + "/" + mID + "/credits?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-Vi&page=1",
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
                Toast.makeText(MovieDetails.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void jsonmovieDetail() {
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetails.this);
        movieDetailinfomation = new CategoryItem();
        String catagory = "movie";
        if (tvshow == 1) {
            catagory = "tv";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, " https://api.themoviedb.org/3/" + catagory + "/" + mID + "?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            movieDetailinfomation = json.JSONmovieDetail(response);
                            movieTime.setText(movieDetailinfomation.getTime());
                            movieCategory.setText(movieDetailinfomation.getGenres());
                            movieSummary.setText(movieDetailinfomation.getSummary());
                            movieDate.setText(movieDetailinfomation.getYearOfRelease());
                            movieVote.setText(movieDetailinfomation.getVote());
                            movieTitle.setText(movieDetailinfomation.getTitle());

                            /// lấy dữ liệu đẩy lên firebase
                            firebaseHistoryWatched.addUserObject(new HistoryMovieWatched(mID, namemovie, movieDetailinfomation.getImageUrl(), movieDetailinfomation.getSummary()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MovieDetails.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }

    private void jsonRelated() {
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetails.this);

        String catagory = "movie";
        if (tvshow == 1) {
            catagory = "tv";
        }

        categoryItemList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/" + catagory + "/" + mID + "/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            categoryItemList = json.JSONmovielist(response);
                            setRelatedRecyclerview(categoryItemList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MovieDetails.this, "Lỗi", Toast.LENGTH_SHORT).show();
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
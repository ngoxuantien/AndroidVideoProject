package com.example.appvideo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appvideo.Activity.MainActivity;
import com.example.appvideo.Activity.SearchActivity;
import com.example.appvideo.Json.Json;
import com.example.appvideo.R;
import com.example.appvideo.adapter.BannerMoviesPagerAdapter;
import com.example.appvideo.adapter.MainRecyclerAdapter;
import com.example.appvideo.adapter.MovieListHorizontalAdapter;
import com.example.appvideo.model.AllCategory;
import com.example.appvideo.model.CategoryItem;
import com.example.appvideo.model.CategoryItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    TabLayout tabIndicator, categoryTab;
    ViewPager bannerMoviesViewPager;
    private Handler handler;


    RecyclerView mainRecyclerview, horizontalRecyclerview1, horizontalRecyclerview2, horizontalRecyclerview3, horizontalRecyclerview4;
    MainRecyclerAdapter mainRecyclerAdapter;
    MovieListHorizontalAdapter movieListHorizontalAdapter1, movieListHorizontalAdapter2, movieListHorizontalAdapter3, movieListHorizontalAdapter4;

    List<AllCategory> allCategoryList;
    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;
    ImageView searchImage;
    Button purcharBtn;
    SwipeRefreshLayout swipeRefreshLayout;
    Json json = new Json();
    TextView textView;
    String t;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        ////////////////////////////////////////////////
        this.testhhh();

        tabIndicator = view.findViewById(R.id.tab_indicator);
        bannerMoviesViewPager = view.findViewById(R.id.banner_viewPager);
        nestedScrollView = view.findViewById(R.id.nestedScroll);
        appBarLayout = view.findViewById(R.id.appbar);
        purcharBtn = view.findViewById(R.id.purchase);
        searchImage = view.findViewById(R.id.search_image);


        //////

        this.ClickSearch();

        this.setrefresh();
        for (int i = 0; i < 2; i++) {
            jsonmenumovie(i);
            jsonmenutv(i);
        }


        jsonmenumovie(2);
        //on tab change select data


        tabIndicator.setupWithViewPager(bannerMoviesViewPager);

        Timer sliderTimer = new Timer();
        //  sliderTimer.scheduleAtFixedRate(view.AutoSlider(), 2000, 6000);


        ////////////////////////

     //   handler = new Handler();
//        Runnable runnable = new Runnable() {
//            public void run() {
//                if (bannerMoviesViewPager.getCurrentItem()<5) {
//                    bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
//                } else
//                    bannerMoviesViewPager.setCurrentItem(0);
//
//            }
//
//
//        };
        tabIndicator.setupWithViewPager(bannerMoviesViewPager, true);
        //     handler.postDelayed(runnable, 20);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void ClickSearch() {
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                startActivity(intent);

            }
        });

    }

    private void testhhh() {

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

        allCategoryList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/now_playing?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<CategoryItem> movieDetailList = new ArrayList<>();
                            movieDetailList = json.JSONmovielist(response);

                            allCategoryList.add(new AllCategory("Đang chiếu rạp", 1, movieDetailList));


                            setMainRecyclerview(allCategoryList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void jsonmenumovie(int hk) {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        int a[] = {791373, 630004, 458576};


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/" + a[hk] + "/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<CategoryItem> movieDetailList = new ArrayList<>();
                            movieDetailList = json.JSONmovielist(response);

                            if (hk == 0) {
                                setMovieListHorizontalRecyclerview1(movieDetailList);
                            } else {
                                if (hk == 2) {
                                    setBannerMoviesPagerAdapter(movieDetailList);
                                } else setMovieListHorizontalRecyclerview2(movieDetailList);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void jsonmenutv(int hk) {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        int a[] = {1554, 65930};


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/tv/" + a[hk] + "/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<CategoryItem> movieDetailList = new ArrayList<>();
                            movieDetailList = json.JSONTvshowlist(response);

                            if (hk == 0) {
                                setMovieListHorizontalRecyclerview3(movieDetailList);
                            } else setMovieListHorizontalRecyclerview4(movieDetailList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }

    private void setBannerMoviesPagerAdapter(List<CategoryItem> bannerMoviesList) {


        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(view.getContext(), bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);

    }


    private void setMovieListHorizontalRecyclerview1(List<CategoryItem> movieListHorizontal) {
        horizontalRecyclerview1 = view.findViewById(R.id.recyclerview1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        horizontalRecyclerview1.setLayoutManager(layoutManager);
        movieListHorizontalAdapter1 = new MovieListHorizontalAdapter(view.getContext(), movieListHorizontal);
        horizontalRecyclerview1.setAdapter(movieListHorizontalAdapter1);

    }

    private void setMovieListHorizontalRecyclerview2(List<CategoryItem> movieListHorizontal) {
        horizontalRecyclerview2 = view.findViewById(R.id.recyclerview2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        horizontalRecyclerview2.setLayoutManager(layoutManager);
        movieListHorizontalAdapter2 = new MovieListHorizontalAdapter(view.getContext(), movieListHorizontal);
        horizontalRecyclerview2.setAdapter(movieListHorizontalAdapter2);

    }

    private void setMovieListHorizontalRecyclerview3(List<CategoryItem> movieListHorizontal) {
        horizontalRecyclerview3 = view.findViewById(R.id.recyclerview3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        horizontalRecyclerview3.setLayoutManager(layoutManager);
        movieListHorizontalAdapter3 = new MovieListHorizontalAdapter(view.getContext(), movieListHorizontal);
        horizontalRecyclerview3.setAdapter(movieListHorizontalAdapter3);

    }

    private void setMovieListHorizontalRecyclerview4(List<CategoryItem> movieListHorizontal) {
        horizontalRecyclerview4 = view.findViewById(R.id.recyclerview4);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        horizontalRecyclerview4.setLayoutManager(layoutManager);
        movieListHorizontalAdapter4 = new MovieListHorizontalAdapter(view.getContext(), movieListHorizontal);
        horizontalRecyclerview4.setAdapter(movieListHorizontalAdapter4);

    }

    private void setMainRecyclerview(List<AllCategory> allCategoryList) {
        mainRecyclerview = view.findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        mainRecyclerview.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(view.getContext(), allCategoryList);
        mainRecyclerview.setAdapter(mainRecyclerAdapter);

    }

    private void setScollDefaultState() {
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0, 0);
        appBarLayout.setExpanded(true);
    }

    private void setrefresh() {
        swipeRefreshLayout = view.findViewById(R.id.swipe_fefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                testhhh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


//    class AutoSlider extends TimerTask {
//
//        @Override
//        public void run() {
//            MainActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (bannerMoviesViewPager.getCurrentItem() < HomeList.size() - 1) {
//                        bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
//                    } else
//                        bannerMoviesViewPager.setCurrentItem(0);
//                }
//
//
//            });
//        }
//    }
}

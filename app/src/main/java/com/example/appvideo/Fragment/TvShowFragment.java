package com.example.appvideo.Fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.appvideo.Activity.TvShowDetail;
import com.example.appvideo.Json.Json;
import com.example.appvideo.R;
import com.example.appvideo.adapter.BannerMoviesPagerAdapter;
import com.example.appvideo.adapter.MainRecyclerAdapter;
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

public class TvShowFragment extends Fragment {
    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    TabLayout tabIndicator, categoryTab;
    ViewPager bannerMoviesViewPager;

    RecyclerView mainRecyclerview;
    MainRecyclerAdapter mainRecyclerAdapter;
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

        view = inflater.inflate(R.layout.fragment_tvshow,container,false);

        ////////////////////////////////////////////////
        this.testhhh();

        tabIndicator = view.findViewById(R.id.tab_indicator);
        categoryTab = view.findViewById(R.id.tablayout);
        nestedScrollView = view.findViewById(R.id.nestedScroll);
        appBarLayout = view.findViewById(R.id.appbar);
        purcharBtn = view.findViewById(R.id.purchase);
//        textView = findViewById(R.id.testtt);
        searchImage = view.findViewById(R.id.search_image);



        //////

        this.ClickSearch();

        this.setrefresh();

        this.jsonmenuHome();



        //on tab change select data
        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                   jsonmenuHome();
                }else{
                    jsonmenu(tab.getPosition());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabIndicator.setupWithViewPager(bannerMoviesViewPager);

        Timer sliderTimer = new Timer();
        //    sliderTimer.scheduleAtFixedRate(new getContext().AutoSlider(), 2000, 6000);
        tabIndicator.setupWithViewPager(bannerMoviesViewPager, true);

        ////////////////////////
        return view;
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
        String [] nametitle={"Phát trong ngày","Tốp hàng đầu","Trở nên phổ biến","Trực tiếp tv"};
        String [] nameapi={"airing_today","top_rated","popular","on_the_air"};
        allCategoryList = new ArrayList<>();
        for(int i=0;i<4;i++) {

            int finalI = i;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/tv/"+nameapi[finalI]+"?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                Toast.makeText(view.getContext(), allCategoryList.size()+"sdfjasfka", Toast.LENGTH_SHORT).show();
                                List<CategoryItem> movieDetailList = new ArrayList<>();
                                movieDetailList = json.JSONTvshowlist(response);

                                allCategoryList.add(new AllCategory(nametitle[finalI], 1, movieDetailList));
                                if(allCategoryList.size()==3){
                                    setBannerMoviesPagerAdapter(movieDetailList);
                                }
                                if(allCategoryList.size()==4){
                                    setMainRecyclerview(allCategoryList);
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



    }

    private void jsonmenu( int hk) {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
         int a[]={95057,2224,456};


         StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/tv/" +a[hk-1] +"/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                 new Response.Listener<String>() {

                     @Override
                     public void onResponse(String response) {
                         try {
                             List<CategoryItem> movieDetailList = new ArrayList<>();
                             movieDetailList = json.JSONTvshowlist(response);

                                     setScollDefaultState();
                                     setBannerMoviesPagerAdapter(movieDetailList);

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
    private void jsonmenuHome() {

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        allCategoryList = new ArrayList<>();


            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/tv/popular?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {

                                List<CategoryItem> movieHomelList = new ArrayList<>();
                                movieHomelList = json.JSONTvshowlist(response);

                                    setBannerMoviesPagerAdapter(movieHomelList);

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


        bannerMoviesViewPager = view.findViewById(R.id.banner_viewPager);
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(view.getContext(), bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);

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
    private  void setrefresh(){
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

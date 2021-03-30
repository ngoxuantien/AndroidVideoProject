package com.example.appvideo.Json;

import com.example.appvideo.model.Cast;
import com.example.appvideo.model.CategoryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Json {


    public Json() {
    }


    public List<CategoryItem> JSONTvshowlist(String jsonMovie) throws JSONException {
        List<CategoryItem> listmovie = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonMovie);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);
            listmovie.add(new CategoryItem(obj1.getInt("id"), obj1.getString("name"), "https://image.tmdb.org/t/p/w500" + obj1.getString("poster_path"), "https://image.tmdb.org/t/p/w500" + obj1.getString("backdrop_path"), 1, obj1.getString("overview")));
        }
        return listmovie;
    }

    public CategoryItem JSONTvDetail(String jsontvshow) throws JSONException {
        CategoryItem categoryItem;
        JSONObject jsonObject = new JSONObject(jsontvshow);
        String genres = "";
        JSONArray jsonArray = jsonObject.getJSONArray("genres");

        String vote = jsonObject.getString("vote_average") + "/" + jsonObject.getString("vote_count");
        //// lấy thể loại phim///
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject job = jsonArray.getJSONObject(i);
            if (i == jsonArray.length() - 1) {
                genres = genres + job.getString("name");
            } else {
                genres = genres + job.getString("name") + ",";
            }
        }
        categoryItem = new CategoryItem(jsonObject.getString("episode_run_time"), jsonObject.getString("first_air_date"), jsonObject.getString("overview"), genres, vote, jsonObject.getString("name"));


        return categoryItem;
    }

    public String JSONmovieTrailer(String jsonString) throws JSONException {
        String k = "rong";
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONObject obj = jsonArray.getJSONObject(0);
        k = obj.getString("key");
        return k;

    }

    public CategoryItem JSONmovieDetail(String JsonString) throws Exception {
        CategoryItem categoryItem;
        JSONObject jsonObject = new JSONObject(JsonString);
        String genres = "";
        JSONArray jsonArray = jsonObject.getJSONArray("genres");

        String vote = jsonObject.getString("vote_average") + "/" + jsonObject.getString("vote_count");
        //// lấy thể loại phim///
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject job = jsonArray.getJSONObject(i);

            if (i == jsonArray.length() - 1) {
                genres = genres + job.getString("name");
            } else {
                genres = genres + job.getString("name") + ",";
            }
        }
        categoryItem = new CategoryItem(jsonObject.getString("runtime"), jsonObject.getString("release_date"), jsonObject.getString("overview"), genres, vote, jsonObject.getString("original_title"));


        return categoryItem;
    }

    public List<CategoryItem> JSONmovielist(String jsonMovie) throws JSONException {
        List<CategoryItem> listmovie = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonMovie);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);
            listmovie.add(new CategoryItem(obj1.getInt("id"), obj1.getString("title"), "https://image.tmdb.org/t/p/w500" + obj1.getString("poster_path"), "https://image.tmdb.org/t/p/w500" + obj1.getString("backdrop_path"), obj1.getString("overview")));
        }
        return listmovie;
    }

    public List<CategoryItem> JSONmovielistsearch(String jsonMovie) throws JSONException {
        List<CategoryItem> listmovie = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonMovie);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);
            listmovie.add(new CategoryItem(obj1.getInt("id"), obj1.getString("original_title"), "https://image.tmdb.org/t/p/w500" + obj1.getString("poster_path"), "https://image.tmdb.org/t/p/w500" + obj1.getString("backdrop_path")));
        }
        return listmovie;
    }


    public List<Cast> JSONcastlist(String jsonCast) throws JSONException {
        List<Cast> castArrayList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonCast);
        JSONArray jsonArray = jsonObject.getJSONArray("cast");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);
            castArrayList.add(new Cast(obj1.getInt("id"), obj1.getString("name"), "https://image.tmdb.org/t/p/w500" + obj1.getString("profile_path")));
        }
        return castArrayList;
    }

    public List<CategoryItem> JSONmovieJoin(String movieJoin) throws Exception {
        List<CategoryItem> categoryItemList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(movieJoin);
        JSONArray jsonArray = jsonObject.getJSONArray("cast");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);
            categoryItemList.add(new CategoryItem(obj1.getInt("id"), obj1.getString("original_title"), "https://image.tmdb.org/t/p/w500" + obj1.getString("poster_path"), "https://image.tmdb.org/t/p/w500" + obj1.getString("backdrop_path")));

        }
        return categoryItemList;
    }

    public List<Cast> JSONcastDetail(String jsonDetail) throws JSONException {
        List<Cast> castList = new ArrayList<>();
        String h = "khong co gioi tinh";
        JSONObject jsonObject = new JSONObject(jsonDetail);
        if (jsonObject.getString("gender").equals("1")) {
            h = "women";
        } else {
            h = "man";
        }
        castList.add(new Cast(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("birthday"), jsonObject.getString("place_of_birth"), h, "https://image.tmdb.org/t/p/w500/" + jsonObject.getString("profile_path"), jsonObject.getString("biography")));

        return castList;
    }


}

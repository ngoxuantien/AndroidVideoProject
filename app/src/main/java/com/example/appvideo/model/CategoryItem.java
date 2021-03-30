package com.example.appvideo.model;

public class CategoryItem {
    Integer id, tvshow;
    String movieName;
    String imageUrl;
    String backdrop;
    String time, yearOfRelease, summary, genres, title, movieKEY,vote;


    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public CategoryItem(Integer id, String movieName, String imageUrl, String backdrop, Integer tvshow, String summary) {
        this.id = id;
        this.tvshow = tvshow;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.backdrop = backdrop;
        this.summary=summary;
    }

    public CategoryItem(String movieKEY) {
        this.movieKEY = movieKEY;
    }

    public String getMovieKEY() {
        return movieKEY;
    }

    public void setMovieKEY(String movieKEY) {
        this.movieKEY = movieKEY;
    }

    //// chi tiết movie///
    public CategoryItem(String time, String yearOfRelease, String summary, String genres,String vote,String title) {
        this.time = time;
        this.yearOfRelease = yearOfRelease;
        this.summary = summary;
        this.genres = genres;
        this.vote=vote;
        this.title=title;

    }


    public CategoryItem() {
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryItem(Integer id, String movieName, String imageUrl, String backdrop) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.backdrop = backdrop;
    }

    public CategoryItem(Integer id, String movieName, String imageUrl, String backdrop, String summary) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.backdrop = backdrop;
        this.summary = summary;
    }

    public Integer getTvshow() {
        return tvshow;
    }

    public void setTvshow(Integer tvshow) {
        this.tvshow = tvshow;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public CategoryItem(Integer id, String movieName, String imageUrl) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}

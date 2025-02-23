package com.example.movieapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarked_movies")
public class BookmarkedMovie {
    @PrimaryKey
    private int id;
    private String title;
    private String overview;
    private String posterPath;

    public BookmarkedMovie(int id, String title, String overview, String posterPath) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) { this.overview = overview; }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }
}

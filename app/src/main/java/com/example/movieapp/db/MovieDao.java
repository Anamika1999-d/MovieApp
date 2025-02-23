package com.example.movieapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookmarkedMovie movie);

    @Query("SELECT * FROM bookmarked_movies")
    List<BookmarkedMovie> getAllBookmarkedMovies();

    @Query("DELETE FROM bookmarked_movies WHERE id = :id")
    void deleteById(int id);
}

package com.example.movieapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BookmarkedMovie.class}, version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}

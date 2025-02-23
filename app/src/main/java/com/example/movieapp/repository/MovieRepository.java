package com.example.movieapp.repository;

import android.util.Log;

import com.example.movieapp.api.TmdbService;
import com.example.movieapp.data.MovieResponse;
import com.example.movieapp.db.MovieDao;
import com.example.movieapp.db.BookmarkedMovie;
import java.util.List;

import io.reactivex.Single;

public class MovieRepository {

    private final TmdbService tmdbService;
    private final MovieDao movieDao;
    private final String apiKey;

    public MovieRepository(TmdbService tmdbService, MovieDao movieDao, String apiKey) {
        this.tmdbService = tmdbService;
        this.movieDao = movieDao;
        this.apiKey = apiKey;
    }

    public Single<MovieResponse> getTrendingMovies() {
        Log.i("anamika",apiKey);
        return tmdbService.getTrendingMovies(apiKey);
    }

    public Single<MovieResponse> getNowPlayingMovies() {
        return tmdbService.getNowPlayingMovies(apiKey);
    }

    public Single<MovieResponse> searchMovies(String query) {
        return tmdbService.searchMovies(apiKey, query);
    }

    public void bookmarkMovie(BookmarkedMovie movie) {
        // For simplicity, using a new thread. Consider using RxJava or Executors.
        new Thread(() -> movieDao.insert(movie)).start();
    }

    public Single<List<BookmarkedMovie>> getBookmarkedMovies() {
        return Single.fromCallable(() -> movieDao.getAllBookmarkedMovies());
    }
}

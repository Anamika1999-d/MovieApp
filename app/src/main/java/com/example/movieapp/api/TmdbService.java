package com.example.movieapp.api;


import com.example.movieapp.data.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbService {

    @GET("trending/movie/day")
    Single<MovieResponse> getTrendingMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Single<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Single<MovieResponse> searchMovies(@Query("api_key") String apiKey, @Query("query") String query);
}

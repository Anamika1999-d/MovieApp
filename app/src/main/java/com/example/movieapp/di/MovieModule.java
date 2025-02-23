package com.example.movieapp.di;

import android.content.Context;

import androidx.room.Room;

import com.example.movieapp.api.TmdbService;
import com.example.movieapp.db.MovieDao;
import com.example.movieapp.db.MovieDatabase;
import com.example.movieapp.repository.MovieRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieModule {

    private final Context context;

    public MovieModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        String BASE_URL = "https://api.themoviedb.org/3/";
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    TmdbService provideTmdbService(Retrofit retrofit) {
        return retrofit.create(TmdbService.class);
    }

    @Provides
    @Singleton
    MovieDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, MovieDatabase.class, "movies_db").build();
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(MovieDatabase database) {
        return database.movieDao();
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(TmdbService tmdbService, MovieDao movieDao) {
        String apiKey = "5349c29ac9849fd0b14d77113f00849f";
        return new MovieRepository(tmdbService, movieDao, apiKey);
    }
}

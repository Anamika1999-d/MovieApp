package com.example.movieapp;

import android.app.Application;

import com.example.movieapp.di.AppComponent;
import com.example.movieapp.di.DaggerAppComponent;
import com.example.movieapp.di.MovieModule;

public class MovieApplication extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .movieModule(new MovieModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}

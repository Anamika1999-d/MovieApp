package com.example.movieapp.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.movieapp.MovieApplication;
import com.example.movieapp.data.Result;
import com.example.movieapp.databinding.ActivityListBinding;
import com.example.movieapp.ui.detail.MovieDetailsActivity;

import java.util.List;


public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding;
    private ListViewModel viewModel;
    private MovieListAdapter trendingAdapter;
    private MovieListAdapter nowPlayingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MovieApplication.getAppComponent().inject(this);

        // Setup adapters
        trendingAdapter = new MovieListAdapter();
        nowPlayingAdapter = new MovieListAdapter();

        // Set click listeners for each adapter
        trendingAdapter.setOnItemClickListener(this::openMovieDetails);
        nowPlayingAdapter.setOnItemClickListener(this::openMovieDetails);

        binding.recyclerTrending.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerTrending.setAdapter(trendingAdapter);

        binding.recyclerNowPlaying.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerNowPlaying.setAdapter(nowPlayingAdapter);

        viewModel = new ViewModelProvider(this, new ListViewModel.Factory())
                .get(ListViewModel.class);

        addObservers();


    }

    private void addObservers(){
        // Observe trending movies LiveData and update the adapter
        viewModel.getTrendingMovies().observe(this, moviesResponse -> {
            Log.i("anamika",moviesResponse.getResults().toString());
            List<Result> movies = moviesResponse.getResults();
            trendingAdapter.setMovies(movies);
        });

        // Observe now playing movies LiveData and update the adapter
        viewModel.getNowPlayingMovies().observe(this, moviesResponse -> {
            List<Result> movies = moviesResponse.getResults();
            nowPlayingAdapter.setMovies(movies);
        });
    }

    private void openMovieDetails(Result movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}

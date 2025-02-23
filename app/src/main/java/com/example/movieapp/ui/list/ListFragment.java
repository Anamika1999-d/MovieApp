package com.example.movieapp.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.data.Result;
import com.example.movieapp.databinding.ActivityListBinding;
import com.example.movieapp.ui.detail.MovieDetailsActivity;

import java.util.List;

public class ListFragment extends Fragment {
    private final String TAG = "ListFragment";
    private ActivityListBinding binding;
    private ListViewModel viewModel;
    private MovieListAdapter trendingAdapter;
    private MovieListAdapter nowPlayingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ActivityListBinding.inflate(getLayoutInflater());
        trendingAdapter = new MovieListAdapter();
        nowPlayingAdapter = new MovieListAdapter();
        trendingAdapter.setOnItemClickListener(this::openMovieDetails);
        nowPlayingAdapter.setOnItemClickListener(this::openMovieDetails);

        binding.recyclerTrending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerTrending.setAdapter(trendingAdapter);

        binding.recyclerNowPlaying.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerNowPlaying.setAdapter(nowPlayingAdapter);

        viewModel = new ViewModelProvider(requireActivity(), new ListViewModel.Factory())
                .get(ListViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addObservers();
    }

    private void addObservers() {
        viewModel.getTrendingMovies().observe(getViewLifecycleOwner(), moviesResponse -> {
            Log.i(TAG, moviesResponse.getResults().toString());
            List<Result> movies = moviesResponse.getResults();
            trendingAdapter.setMovies(movies);
        });

        viewModel.getNowPlayingMovies().observe(getViewLifecycleOwner(), moviesResponse -> {
            List<Result> movies = moviesResponse.getResults();
            nowPlayingAdapter.setMovies(movies);
        });
    }

    private void openMovieDetails(Result movie) {
        Intent intent = new Intent(requireContext(), MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}

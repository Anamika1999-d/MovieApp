package com.example.movieapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movieapp.data.Result;
import com.example.movieapp.databinding.ActivitySearchBinding;
import com.example.movieapp.ui.detail.MovieDetailsActivity;

import java.util.List;

public class SearchFragment extends Fragment {

    private ActivitySearchBinding binding;
    private SearchViewModel viewModel;
    private SearchResultsAdapter adapter;
    private static final long DEBOUNCE_DELAY = 500;
    private long lastEditTime = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivitySearchBinding.inflate(getLayoutInflater());

        binding.recyclerSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SearchResultsAdapter();
        binding.recyclerSearchResults.setAdapter(adapter);

        adapter.setOnItemClickListener(this::openMovieDetails);

        viewModel = new ViewModelProvider(this, new SearchViewModel.Factory())
                .get(SearchViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getSearchResults().observe(getViewLifecycleOwner(), moviesResponse -> {
            if (moviesResponse != null) {
                List<Result> movies = moviesResponse.getResults();
                adapter.setMovies(movies);
            }
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastEditTime = System.currentTimeMillis();
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.etSearch.postDelayed(() -> {
                    if (System.currentTimeMillis() - lastEditTime >= DEBOUNCE_DELAY) {
                        String query = s.toString().trim();
                        if (!query.isEmpty()) {
                            viewModel.searchMovies(query);
                        } else {
                            adapter.setMovies(null); // Optionally clear results if query is empty.
                        }
                    }
                }, DEBOUNCE_DELAY);
            }
        });
    }

    private void openMovieDetails(Result movie) {
        Intent intent = new Intent(requireContext(), MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}

package com.example.movieapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.movieapp.data.Result;
import com.example.movieapp.databinding.ActivitySearchBinding;
import com.example.movieapp.ui.detail.MovieDetailsActivity;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private SearchViewModel viewModel;
    private SearchResultsAdapter adapter;
    private static final long DEBOUNCE_DELAY = 500;
    private long lastEditTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerSearchResults.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchResultsAdapter();
        binding.recyclerSearchResults.setAdapter(adapter);

        adapter.setOnItemClickListener(this::openMovieDetails);

        viewModel = new ViewModelProvider(this, new SearchViewModel.Factory())
                .get(SearchViewModel.class);

        viewModel.getSearchResults().observe(this, moviesResponse -> {
            if (moviesResponse != null) {
                List<Result> movies = moviesResponse.getResults();
                adapter.setMovies(movies);
            }
        });

        // Set up search input text change listener with debounce.
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
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}

package com.example.movieapp.ui.bookmark;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.movieapp.databinding.ActivityBookmarkBinding;

public class BookmarksActivity extends AppCompatActivity {

    private BookmarkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.movieapp.databinding.ActivityBookmarkBinding binding = ActivityBookmarkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerBookmarks.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BookmarkAdapter();
        binding.recyclerBookmarks.setAdapter(adapter);

        BookmarkViewModel viewModel = new ViewModelProvider(this, new BookmarkViewModel.Factory())
                .get(BookmarkViewModel.class);

        viewModel.getBookmarkedMovies().observe(this, bookmarkedMovies -> {
            adapter.setBookmarkedMovies(bookmarkedMovies);
        });
    }
}

package com.example.movieapp.ui.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movieapp.databinding.ActivityBookmarkBinding;

public class BookmarksFragment extends Fragment {

    ActivityBookmarkBinding binding;
    private BookmarkAdapter adapter;
    BookmarkViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ActivityBookmarkBinding.inflate(getLayoutInflater());
        binding.recyclerBookmarks.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BookmarkAdapter();
        binding.recyclerBookmarks.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, new BookmarkViewModel.Factory())
                .get(BookmarkViewModel.class);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addObservers();
    }

    private void addObservers() {
        viewModel.getBookmarkedMovies().observe(getViewLifecycleOwner(), bookmarkedMovies -> {
            adapter.setBookmarkedMovies(bookmarkedMovies);
        });
    }

}

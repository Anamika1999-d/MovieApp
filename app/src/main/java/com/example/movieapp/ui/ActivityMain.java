package com.example.movieapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.ui.bookmark.BookmarksFragment;
import com.example.movieapp.ui.list.ListFragment;
import com.example.movieapp.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMain extends AppCompatActivity {

    private final String TAG = "ActivityMain";

    private ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            loadListFragment();
        }

        BottomNavigationView bottomNavigation = binding.bottomNavigation;

        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                loadListFragment();
            } else if (item.getItemId() == R.id.navigation_search) {
                loadSearchFragment();
            } else if (item.getItemId() == R.id.navigation_bookmarks) {
                loadBookmarkFragment();
            } else {
                loadListFragment();
            }
            return false;
        });
    }

    public void loadListFragment() {
        Log.i(TAG, "loadListFragment");
        ListFragment fragment = new ListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, fragment)
                .commit();
    }

    public void loadBookmarkFragment() {
        Log.i(TAG, "loadBookmarkFragment");
        BookmarksFragment fragment = new BookmarksFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, fragment)
                .commit();
    }

    public void loadSearchFragment() {
        Log.i(TAG, "loadSearchFragment");
        SearchFragment fragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, fragment)
                .commit();
    }


}

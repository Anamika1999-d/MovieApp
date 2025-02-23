package com.example.movieapp.di;

import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.ui.ActivityMain;
import com.example.movieapp.ui.bookmark.BookmarksActivity;
import com.example.movieapp.ui.detail.MovieDetailsActivity;
import com.example.movieapp.ui.list.ListActivity;
import com.example.movieapp.ui.search.SearchActivity;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {MovieModule.class})
public interface AppComponent {
    void inject(ActivityMain activityMain);
    void inject(ListActivity homeActivity);
    void inject(MovieDetailsActivity movieDetailsActivity);
    void inject(SearchActivity searchActivity);
    void inject(BookmarksActivity bookmarksActivity);
    MovieRepository provideMovieRepository();
}

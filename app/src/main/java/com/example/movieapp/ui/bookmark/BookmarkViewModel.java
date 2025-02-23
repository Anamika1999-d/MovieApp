package com.example.movieapp.ui.bookmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.movieapp.MovieApplication;
import com.example.movieapp.db.BookmarkedMovie;
import com.example.movieapp.repository.MovieRepository;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BookmarkViewModel extends ViewModel {

    private final MovieRepository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<List<BookmarkedMovie>> bookmarkedMovies = new MutableLiveData<>();

    public BookmarkViewModel(MovieRepository repository) {
        this.repository = repository;
        loadBookmarkedMovies();
    }

    public LiveData<List<BookmarkedMovie>> getBookmarkedMovies() {
        return bookmarkedMovies;
    }

    private void loadBookmarkedMovies() {
        disposable.add(
                repository.getBookmarkedMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(bookmarkedMovies::setValue, Throwable::printStackTrace)
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public static class Factory implements ViewModelProvider.Factory {
        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            MovieRepository repository = MovieApplication.getAppComponent().provideMovieRepository();
            return (T) new BookmarkViewModel(repository);
        }
    }
}

package com.example.movieapp.ui.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.movieapp.MovieApplication;
import com.example.movieapp.data.MovieResponse;
import com.example.movieapp.repository.MovieRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    private final MovieRepository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<MovieResponse> searchResults = new MutableLiveData<>();

    public SearchViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public LiveData<MovieResponse> getSearchResults() {
        return searchResults;
    }

    public void searchMovies(String query) {
        disposable.add(
                repository.searchMovies(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(searchResults::setValue, Throwable::printStackTrace)
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public static class Factory implements ViewModelProvider.Factory {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            // Replace with your Dagger accessor for MovieRepository.
            MovieRepository repository = MovieApplication.getAppComponent().provideMovieRepository();
            return (T) new SearchViewModel(repository);
        }
    }
}

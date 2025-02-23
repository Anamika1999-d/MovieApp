package com.example.movieapp.ui.list;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.movieapp.MovieApplication;
import com.example.movieapp.data.MovieResponse;
import com.example.movieapp.repository.MovieRepository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private final MovieRepository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private final MutableLiveData<MovieResponse> trendingMovies = new MutableLiveData<>();
    private final MutableLiveData<MovieResponse> nowPlayingMovies = new MutableLiveData<>();

    public ListViewModel(MovieRepository repository) {
        this.repository = repository;
        loadMovies();
    }

    public LiveData<MovieResponse> getTrendingMovies() {
        return trendingMovies;
    }

    public LiveData<MovieResponse> getNowPlayingMovies() {
        return nowPlayingMovies;
    }

    private void loadMovies() {
        Log.i("anamika","loadMovies");
        disposable.add(repository.getTrendingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesResponse -> {
                    Log.i("anamika", "Response: " + moviesResponse.getResults().toString());
                    trendingMovies.setValue(moviesResponse);
                }, error ->{
                    Log.i("anamika","error : "+error);
                }));


        disposable.add(repository.getNowPlayingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(nowPlayingMovies::setValue, Throwable::printStackTrace));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    // Factory for instantiation with DI
    public static class Factory implements ViewModelProvider.Factory {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            MovieRepository repository = MovieApplication.getAppComponent().provideMovieRepository();
            return (T) new ListViewModel(repository);
        }
    }
}

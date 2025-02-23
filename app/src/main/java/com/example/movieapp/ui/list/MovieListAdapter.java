package com.example.movieapp.ui.list;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.movieapp.data.Result;
import com.example.movieapp.databinding.MovieItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.*;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<Result> movies = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Result movie);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Result movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovies(List<Result> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private final MovieItemBinding binding;

        public MovieViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Result movie) {
            binding.tvMovieTitle.setText(movie.getTitle());
            // Assuming you have a full URL for the poster image
            String posterUrl = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();
            Log.d("ANAMIKA", posterUrl);
            Picasso.get().load(posterUrl).into(binding.ivMoviePoster);

            binding.getRoot().setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(movie);
                }
            });
        }
    }
}

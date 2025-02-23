package com.example.movieapp.ui.search;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.data.Result;
import com.example.movieapp.databinding.MovieItemBinding;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchViewHolder> {

    private List<Result> movies = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(Result movie);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovies(List<Result> movies) {
        this.movies = movies != null ? movies : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Result movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        private final MovieItemBinding binding;

        public SearchViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Result movie) {
            binding.tvMovieTitle.setText(movie.getTitle());

            String posterUrl = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();
            Picasso.get()
                    .load(posterUrl)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.stat_notify_error)
                    .into(binding.ivMoviePoster);

            binding.getRoot().setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(movie);
                }
            });
        }
    }
}

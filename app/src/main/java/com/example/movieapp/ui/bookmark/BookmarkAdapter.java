package com.example.movieapp.ui.bookmark;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.databinding.BookmarkItemBinding;
import com.example.movieapp.db.BookmarkedMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private List<BookmarkedMovie> bookmarkedMovies = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setBookmarkedMovies(List<BookmarkedMovie> movies) {
        this.bookmarkedMovies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookmarkItemBinding binding = BookmarkItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new BookmarkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        BookmarkedMovie movie = bookmarkedMovies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return bookmarkedMovies.size();
    }

    static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        private final BookmarkItemBinding binding;

        public BookmarkViewHolder(BookmarkItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(BookmarkedMovie movie) {
            binding.tvBookmarkTitle.setText(movie.getTitle());
            binding.tvBookmarkOverview.setText(movie.getOverview());

            // If your item layout includes an ImageView for the poster,
            // you can load it with Picasso (or another library) as shown below.
            String posterUrl = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
            // Uncomment and ensure Picasso dependency is added:
             Picasso.get().load(posterUrl).into(binding.ivBookmarkPoster);
        }
    }
}

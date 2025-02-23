package com.example.movieapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.MovieApplication;
import com.example.movieapp.data.Result;
import com.example.movieapp.databinding.ActivityMovieDetailBinding;
import com.example.movieapp.db.BookmarkedMovie;
import com.example.movieapp.repository.MovieRepository;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;
    private MovieRepository repository;
    private Result movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MovieApplication.getAppComponent().provideMovieRepository();

        if (getIntent() != null && getIntent().hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            if (movie != null) {
                displayMovieDetails();
            }
        }

        binding.btnBookmark.setOnClickListener(v -> {
            Log.i("MovieDetails", "Bookmarked");
            if (movie != null) {
                // Create a BookmarkedMovie using the parsed Result object
                BookmarkedMovie bookmarkedMovie = new BookmarkedMovie(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getOverview(),
                        movie.getPoster_path()
                );
                // Save the bookmarked movie using the repository
                repository.bookmarkMovie(bookmarkedMovie);
                Toast.makeText(MovieDetailsActivity.this, "Movie Bookmarked!", Toast.LENGTH_SHORT).show();
            }
        });


        // Share button click listener
        binding.btnShare.setOnClickListener(v -> {
            if (movie != null) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareText = "Check out this movie: movieapp://movie/" + movie.getId();
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }

    private void displayMovieDetails() {
        // Set the title and overview from the movie object.
        binding.tvMovieTitle.setText(movie.getTitle());
        binding.tvMovieOverview.setText(movie.getOverview());

        // Build the full URL for the movie poster.
        String posterUrl = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();

        // Use Picasso to load the image into the ImageView.
        Picasso.get()
                .load(posterUrl)
                .into(binding.ivMoviePoster);
    }
}

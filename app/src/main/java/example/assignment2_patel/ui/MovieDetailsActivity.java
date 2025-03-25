package example.assignment2_patel.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import example.assignment2_patel.databinding.ActivityMovieDetailsBinding;
import example.assignment2_patel.model.MovieDetails;
import example.assignment2_patel.network.OmdbApi;
import example.assignment2_patel.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    private final String apiKey = "136ccaa3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String imdbID = getIntent().getStringExtra("imdbID");
        if (imdbID != null) {
            fetchMovieDetails(imdbID);
        } else {
            Toast.makeText(this, "No movie ID found", Toast.LENGTH_SHORT).show();
            finish();
        }

        binding.buttonBack.setOnClickListener(v -> finish());
    }

    private void fetchMovieDetails(String imdbID) {
        OmdbApi api = RetrofitClient.getInstance().create(OmdbApi.class);
        api.getMovieDetails(apiKey, imdbID).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieDetails movie = response.body();
                    binding.textTitle.setText(movie.getTitle());
                    binding.textYear.setText("Year: " + movie.getYear());
                    binding.textGenre.setText("Genre: " + movie.getGenre());
                    binding.textDirector.setText("Director: " + movie.getDirector());
                    binding.textPlot.setText("Plot:\n" + movie.getPlot());

                    Glide.with(MovieDetailsActivity.this)
                            .load(movie.getPoster())
                            .into(binding.imagePoster);
                } else {
                    Toast.makeText(MovieDetailsActivity.this, "Failed to load movie details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Toast.makeText(MovieDetailsActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

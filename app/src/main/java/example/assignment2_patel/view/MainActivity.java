package example.assignment2_patel.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import example.assignment2_patel.databinding.ActivityMainBinding;
import example.assignment2_patel.viewmodel.MoviesViewModel;
import example.assignment2_patel.ui.MovieDetailsActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MoviesViewModel viewModel;
    private AdapterMovie adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DEBUG", "Before binding");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("DEBUG", "After binding");

        Toast.makeText(this, "MainActivity loaded", Toast.LENGTH_SHORT).show();

        Log.d("DEBUG", "Before ViewModel init");
        viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        Log.d("DEBUG", "After ViewModel init");

        Log.d("DEBUG", "Before adapter init");
        adapter = new AdapterMovie(new ArrayList<>(), movie -> {
            Log.d("DEBUG", "Item clicked: " + movie.getTitle());
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            intent.putExtra("imdbID", movie.getImdbID());
            startActivity(intent);
        });
        Log.d("DEBUG", "After adapter init");

        binding.recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMovies.setAdapter(adapter);

        Log.d("DEBUG", "Before search button");

        binding.buttonSearch.setOnClickListener(v -> {
            String query = binding.editTextSearch.getText().toString().trim();
            if (!query.isEmpty()) {
                Log.d("DEBUG", "Searching: " + query);
                viewModel.searchMovies(query);
            } else {
                Toast.makeText(this, "Please enter a movie title", Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("DEBUG", "Before LiveData observe");

        viewModel.getMovies().observe(this, movies -> {
            if (movies != null) {
                Log.d("DEBUG", "Movies found: " + movies.size());
                adapter.setMovieList(movies);
            } else {
                Toast.makeText(this, "No movies found or network error.", Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("DEBUG", "onCreate finished");
    }
}
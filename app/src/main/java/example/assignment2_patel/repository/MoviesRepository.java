package example.assignment2_patel.repository;

import androidx.lifecycle.MutableLiveData;
import java.util.List;
import example.assignment2_patel.model.Movie;
import example.assignment2_patel.model.MovieSearchResponse;
import example.assignment2_patel.network.OmdbApi;
import example.assignment2_patel.network.OmdbApi;
import example.assignment2_patel.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private final OmdbApi api;
    private final String apiKey = "136ccaa3";

    public MoviesRepository() {
        api = RetrofitClient.getInstance().create(OmdbApi.class);
    }

    public void searchMovies(String query, MutableLiveData<List<Movie>> moviesLiveData) {
        api.searchMovies(apiKey, query).enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.body() != null) {
                    moviesLiveData.setValue(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                moviesLiveData.setValue(null);
            }
        });
    }
}
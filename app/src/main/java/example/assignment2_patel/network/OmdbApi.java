package example.assignment2_patel.network;

import example.assignment2_patel.model.MovieDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import example.assignment2_patel.model.MovieSearchResponse;

public interface OmdbApi {
    @GET("/")
    Call<MovieSearchResponse> searchMovies(
            @Query("apikey") String apiKey,
            @Query("s") String query
    );

    @GET("/")
    Call<MovieDetails> getMovieDetails(
            @Query("apikey") String apiKey,
            @Query("i") String imdbID
    );
}

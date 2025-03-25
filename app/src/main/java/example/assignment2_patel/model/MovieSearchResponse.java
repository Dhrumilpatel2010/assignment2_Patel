package example.assignment2_patel.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieSearchResponse {
    @SerializedName("Search")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}

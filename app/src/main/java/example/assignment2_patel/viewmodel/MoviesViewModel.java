package example.assignment2_patel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import example.assignment2_patel.model.Movie;
import example.assignment2_patel.repository.MoviesRepository;

public class MoviesViewModel extends ViewModel {

    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private final MoviesRepository repository = new MoviesRepository();

    public void searchMovies(String query) {
        repository.searchMovies(query, moviesLiveData);
    }

    public LiveData<List<Movie>> getMovies() {
        return moviesLiveData;
    }
}
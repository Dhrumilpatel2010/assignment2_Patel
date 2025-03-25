package example.assignment2_patel.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import example.assignment2_patel.R;
import example.assignment2_patel.model.Movie;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MovieViewHolder> {

    private List<Movie> movieList;
    private OnMovieClickListener listener;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public AdapterMovie(List<Movie> movieList, OnMovieClickListener listener) {
        this.movieList = movieList;
        this.listener = listener;
    }

    public void setMovieList(List<Movie> newList) {
        this.movieList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewYear.setText("Year: " + movie.getYear());
        holder.textViewRating.setText("Type: " + movie.getType());
        holder.textViewStudio.setText("IMDB ID: " + movie.getImdbID());

        // Load poster image using Glide
        Glide.with(holder.itemView.getContext())
                .load(movie.getPoster())
                .into(holder.imagePoster);

        holder.itemView.setOnClickListener(v -> listener.onMovieClick(movie));
    }

    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewYear, textViewRating, textViewStudio;
        ImageView imagePoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewYear = itemView.findViewById(R.id.textViewYear);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewStudio = itemView.findViewById(R.id.textViewStudio);
            imagePoster = itemView.findViewById(R.id.imagePoster);
        }
    }
}
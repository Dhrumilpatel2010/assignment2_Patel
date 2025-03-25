package example.assignment2_patel.model;

import com.google.gson.annotations.SerializedName;

public class MovieDetails {
    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Director")
    private String director;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Poster")
    private String poster;

    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getGenre() { return genre; }
    public String getDirector() { return director; }
    public String getPlot() { return plot; }
    public String getPoster() { return poster; }
}
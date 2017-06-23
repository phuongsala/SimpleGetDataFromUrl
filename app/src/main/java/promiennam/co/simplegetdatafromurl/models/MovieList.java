package promiennam.co.simplegetdatafromurl.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Phuong on 19-May-17.
 */

public class MovieList {

    @SerializedName("results")
    private List<Movie> movieList;

    public MovieList() {
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}

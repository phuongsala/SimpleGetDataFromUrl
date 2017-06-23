package promiennam.co.simplegetdatafromurl.interfaces;

import io.reactivex.Observable;
import promiennam.co.simplegetdatafromurl.models.MovieList;
import retrofit2.http.GET;

/**
 * Created by Phuong on 19-May-17.
 */

public interface IDataListener {

    @GET("top_rated?api_key=INSERT_YOUR_API_KEY")
    Observable<MovieList> getMovieList();
}

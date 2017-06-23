package promiennam.co.simplegetdatafromurl.helpers;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import promiennam.co.simplegetdatafromurl.interfaces.ICallback;
import promiennam.co.simplegetdatafromurl.interfaces.IDataListener;
import promiennam.co.simplegetdatafromurl.models.MovieList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Phuong on 19-May-17.
 */

public class RetrofitHelper {

    private static MovieList mMovieList;

    public static void getData(final ICallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.themoviedb.org/3/movie/")
                .build();

        Observable<MovieList> observable = retrofit.create(IDataListener.class).getMovieList();

        if (observable != null) {
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MovieList>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull MovieList movieList) {
                            RetrofitHelper.mMovieList = movieList;

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("data", e.getMessage() + " cause " + e.getCause());

                        }

                        @Override
                        public void onComplete() {
                            if (callback != null) {
                                callback.onComplete(mMovieList);
                            }
                        }
                    });
        }
    }
}

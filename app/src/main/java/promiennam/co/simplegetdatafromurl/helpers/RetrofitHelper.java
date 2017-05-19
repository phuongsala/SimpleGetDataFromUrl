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
import promiennam.co.simplegetdatafromurl.models.GeonameList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Phuong on 19-May-17.
 */

public class RetrofitHelper {

    private ICallback mCallback;
    private GeonameList mGeonameList;

    public RetrofitHelper(ICallback callback) {
        mCallback = callback;
    }

    public void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.geonames.org/")
                .build();

        Observable<GeonameList> observable = retrofit.create(IDataListener.class).getGeonameList();

        if (observable != null) {
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GeonameList>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull GeonameList geonameList) {
                            Log.d("data", "" + geonameList.getGeonameList());
                            mGeonameList = geonameList;

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("data", e.getMessage() + " cause " + e.getCause());

                        }

                        @Override
                        public void onComplete() {
                            if (mCallback != null) {
                                mCallback.onComplete(mGeonameList);
                            }
                        }
                    });
        }
    }
}

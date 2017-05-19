package promiennam.co.simplegetdatafromurl.interfaces;

import io.reactivex.Observable;
import promiennam.co.simplegetdatafromurl.models.GeonameList;
import retrofit2.http.GET;

/**
 * Created by Phuong on 19-May-17.
 */

public interface IDataListener {

    @GET("citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo")
    Observable<GeonameList> getGeonameList();
}

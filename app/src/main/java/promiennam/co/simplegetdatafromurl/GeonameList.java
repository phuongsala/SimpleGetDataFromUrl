package promiennam.co.simplegetdatafromurl;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Phuong on 19-May-17.
 */

public class GeonameList {

    @SerializedName("geonames")
    private List<Geoname> geonameList;

    public GeonameList() {
    }

    public List<Geoname> getGeonameList() {
        return geonameList;
    }

    public void setGeonameList(List<Geoname> geonameList) {
        this.geonameList = geonameList;
    }
}

package yezhenwang.newgenerationspotify;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yezhen on 2016/8/11.
 */
public class LocationResults implements Parcelable {

    //    String events;
//    String venue;
    String id;
    String name;
    String address;
    String city;
    String state;
    String country;

    String url;String zipcode;
    double latitude;
    double longitude;


    public LocationResults(String id, String name, String address, String city, String state, String country, String zipcode, String url, double latitude, double longitude) {
//
//        this.events = events;
//        this.venue = venue;
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    protected LocationResults(Parcel in) {
//            events = in.readString();
//            venue = in.readString();
        id = in.readString();
        name = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        url = in.readString();
        zipcode = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//            dest.writeString(events);
//            dest.writeString(venue);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(url);
        dest.writeString(zipcode);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LocationResults> CREATOR = new Parcelable.Creator<LocationResults>() {
        @Override
        public LocationResults createFromParcel(Parcel in) {
            return new LocationResults(in);
        }

        @Override
        public LocationResults[] newArray(int size) {
            return new LocationResults[size];
        }
    };
}


//    public Results(String events, String venue, String id, String name, String city, String state, String country, String zipcode, String url, double latitude, double longitude) {
//    }
//
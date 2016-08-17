package yezhenwang.newgenerationspotify;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Yezhen on 2016/8/11.
 */
public class LocationGetter extends AsyncTask<String, Integer, ArrayList> {

    LocationActivity locationActivity;

    public LocationGetter(LocationActivity locationActivity){
        this.locationActivity = locationActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList doInBackground(String... params) {

        String locationUrl = "http://api.jambase.com/events?zipCode="+ params[0] +"&page=0&api_key=ev835tr3hf6ra45asd4adubz&o=json";
        ArrayList<LocationResults> resultsArrayList = new ArrayList<LocationResults>();


        try {
            URL theURL = new URL(locationUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(theURL.openConnection().getInputStream(), "UTF-8"));
            String json = reader.readLine();

            JSONObject jsonObject = new JSONObject(json);
            JSONArray eventsArray = jsonObject.getJSONArray("Events");

            for (int i =0; i<eventsArray.length(); i++){
                publishProgress((i+1)*13);
                JSONObject eventObject = eventsArray.getJSONObject(i);
                JSONObject venueObject = eventObject.getJSONObject("Venue");

                String id = venueObject.getString("Id");
                String name = venueObject.getString("Name");
                String address = venueObject.getString("Address");
                String city = venueObject.getString("City");
                String state = venueObject.getString("State");
                String country = venueObject.getString("Country");
                String zipcode = venueObject.getString("ZipCode");
                String url = venueObject.getString("Url");
                double latitude = Double.parseDouble(venueObject.getString("Latitude"));
                double longitude = Double.parseDouble(venueObject.getString("Longitude"));


                LocationResults locationResults = new LocationResults(id, name, address, city, state, country, zipcode, url, latitude, longitude);
                resultsArrayList.add(locationResults);
            }





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return resultsArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        locationActivity.drawListView(arrayList);
    }
}
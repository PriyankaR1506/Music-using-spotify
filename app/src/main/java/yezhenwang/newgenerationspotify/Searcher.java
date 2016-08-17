package yezhenwang.newgenerationspotify;

import android.os.AsyncTask;

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
 * Created by Yezhen on 2016/8/4.
 */
public class Searcher extends AsyncTask<String, Integer, ArrayList> {

    SearchFragment SearchFragment;
    ArrayList<Results> resultsArrayList;
    String spotifyURL, artistName, artistTops;
    String[] artistID;

    public Searcher(SearchFragment SearchFragment) {
       this.SearchFragment = SearchFragment;
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

        if (strings[0].equals("artist")) {
            spotifyURL = "https://api.spotify.com/v1/search?q=" + strings[1] + "&type=artist";
        }

        resultsArrayList = new ArrayList<Results>();

        try {
            URL theUrl = new URL(spotifyURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(theUrl.openConnection().getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject artistsObject = jsonObject.getJSONObject("artists");
            JSONArray itemsArray = artistsObject.getJSONArray("items");

            artistID = new String[itemsArray.length()];

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject artistObject = itemsArray.getJSONObject(i);
                artistName = artistObject.getString("name");
                artistID[i] = artistObject.getString("id");

                artistTops = "https://api.spotify.com/v1/artists/"+ artistID +"/top-tracks?country=US";

                Results result = new Results(artistName, null, null);
                resultsArrayList.add(result);
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
            SearchFragment.drawListView(arrayList);
    }
}

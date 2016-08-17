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
import java.util.Arrays;

/**
 * Created by Yezhen on 2016/8/4.
 */
public class TopTrackDownloader extends AsyncTask<String, Integer, ArrayList> {

    ArrayList<Results> resultsArrayList;
    ArtistActivity artistActivity;
    String topTrackURL;
    String trackNametoParse, album, artist;
    String[] previewURL, trackNametoParseS, albumS, artistS;

    public  TopTrackDownloader(ArtistActivity artistActivity) {
        this.artistActivity = artistActivity;
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

        topTrackURL = "https://api.spotify.com/v1/artists/"+ strings[0] +"/top-tracks?country=US";

        resultsArrayList = new ArrayList<Results>();

        try {
            URL theUrl = new URL(topTrackURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(theUrl.openConnection().getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject tracksObject = new JSONObject(sb.toString());
            JSONArray tracksArray = tracksObject.getJSONArray("tracks");

            trackNametoParse = new String();
            album = new String();
            artist = new String();

            previewURL = new String[tracksArray.length()];
            trackNametoParseS = new String[tracksArray.length()];
            albumS = new String[tracksArray.length()];
            artistS = new String[tracksArray.length()];


            for (int i = 0; i < tracksArray.length(); i++) {
                JSONObject trackObject = tracksArray.getJSONObject(i);
                String trackName = trackObject.getString("name");
                previewURL[i] = trackObject.getString("preview_url");
                trackNametoParse = trackName;
                trackNametoParseS[i] = trackName;

                JSONObject albumObject = trackObject.getJSONObject("album");
                String albumName = albumObject.getString("name");
                album = albumName;
                albumS[i] = albumName;

                JSONArray artistsArray = trackObject.getJSONArray("artists");
                String[] artistName = new String[artistsArray.length()];
                for (int j = 0; j < artistsArray.length(); j++) {
                    JSONObject artistObject = artistsArray.getJSONObject(j);
                    artistName[j] = artistObject.getString("name");
                }
                String nameInOne = Arrays.toString(artistName);
                nameInOne = nameInOne.substring(1, nameInOne.length() - 1);
                artist = nameInOne;
                artistS[i] = nameInOne;

                Results result = new Results(artist, album, trackName);
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

    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        artistActivity.drawListView(arrayList);
    }

}

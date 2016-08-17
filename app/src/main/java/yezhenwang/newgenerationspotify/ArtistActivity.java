package yezhenwang.newgenerationspotify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ArtistActivity extends AppCompatActivity {

    String[] artistID;
    int index;
    ListView topList;

    final TopTrackDownloader downloader = new TopTrackDownloader(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        topList = (ListView) findViewById(R.id.topList);

        //get id and index from searchactivity
        artistID = getIntent().getStringArrayExtra("ID");
        index = getIntent().getIntExtra("Index", 1);

        downloader.execute(artistID[index]);

        topList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ArtistActivity.this, PlaySampleTrack.class);
                intent.putExtra("URL", downloader.previewURL);
                intent.putExtra("trackName", downloader.trackNametoParseS);
                intent.putExtra("artist", downloader.artistS);
                intent.putExtra("album", downloader.albumS);
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });
    }

    public void drawListView(ArrayList<Results> resultsArray) {
        ResultsAdapter adapter = new ResultsAdapter(this, resultsArray);
        topList.setAdapter(adapter);
    }
}

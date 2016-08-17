package yezhenwang.newgenerationspotify;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PlaySampleTrack extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button buttonPlay, buttonPause;
    String[] theURL, trackName, album, artist;
    TextView trackText, albumText, artistText;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sample_track);

        buttonPlay = (Button) findViewById(R.id.playPreview);
        buttonPause = (Button) findViewById(R.id.pausePreview);

        trackText = (TextView) findViewById(R.id.trackText);
        albumText = (TextView) findViewById(R.id.albumText);
        artistText = (TextView) findViewById(R.id.artistText);

        theURL = getIntent().getStringArrayExtra("URL");
        trackName = getIntent().getStringArrayExtra("trackName");
        album = getIntent().getStringArrayExtra("album");
        artist = getIntent().getStringArrayExtra("artist");
        i = getIntent().getIntExtra("index", 1);

        trackText.setText(trackName[i]);
        albumText.setText(album[i]);
        artistText.setText(artist[i]);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    mediaPlayer.setDataSource(theURL[i]);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "Invalid URL", Toast.LENGTH_LONG).show();
                } catch (SecurityException e) {
                    Toast.makeText(getApplicationContext(), "Invalid URL", Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), "Invalid URL", Toast.LENGTH_LONG).show();
                }catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    mediaPlayer.prepare();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "Invali URL", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer != null) {
            if (this.isFinishing() || mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
    }
}

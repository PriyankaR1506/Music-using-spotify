package yezhenwang.newgenerationspotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainPageActivity extends AppCompatActivity {

    ArtistPicFragment artistPicFragment;

    TextView greetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        artistPicFragment = (ArtistPicFragment) getFragmentManager().findFragmentById(R.id.artist_pic_fragment);


    }

}

package yezhenwang.newgenerationspotify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by Yezhen on 2016/8/11.
 */
public class VenueActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        webView = (WebView) findViewById(R.id.webView);

        LocationResults result = getIntent().getParcelableExtra("result");

        webView.loadUrl(result.url);


    }
}
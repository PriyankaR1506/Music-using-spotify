package yezhenwang.newgenerationspotify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by Yezhen on 2016/8/11.
 */
public class LocationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    ArrayList<LocationResults> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        listView = (ListView) findViewById(R.id.listView);

        String zipCode = getIntent().getStringExtra("zipCode");


        LocationGetter locationGetter = new LocationGetter(this);
        locationGetter.execute(zipCode);
    }

    public void drawListView(ArrayList<LocationResults> resultsArray) {
        results = new ArrayList<LocationResults>();
        results = resultsArray;
        LocationAdapter adapter = new LocationAdapter(this, resultsArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        LocationResults result = results.get(position);
        Intent intent = new Intent(this,VenueActivity.class);
        intent.putExtra("result", result);

        startActivity(intent);

    }
}

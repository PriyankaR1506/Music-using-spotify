package yezhenwang.newgenerationspotify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Yezhen on 2016/8/7.
 */
public class SearchFragment extends Fragment {

    EditText searchContent;
    Button searchArtist, clearEdit;
    ListView searchResult;
    String[] artistID;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("FRAGMENT A", "onCreateView");

        View searchView = inflater.inflate(R.layout.search_fragment, container, false);

        searchContent = (EditText) searchView.findViewById(R.id.searchContent);
        searchArtist = (Button) searchView.findViewById(R.id.searchArtist);
        clearEdit = (Button) searchView.findViewById(R.id.clearText);
        searchResult = (ListView) searchView.findViewById(R.id.searchResult);

        final Searcher downloader = new Searcher(SearchFragment.this);

        searchArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloader.execute("artist", searchContent.getText().toString().replaceAll(" ", "%20"));
                searchArtist.setEnabled(false);
            }
        });

        clearEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchContent.setText("");
            }
        });

        searchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                artistID = downloader.artistID;

                Intent intent = new Intent(getActivity(), ArtistActivity.class);

                //pass id and index to ArtistActivity
                intent.putExtra("ID", downloader.artistID);
                intent.putExtra("Index", i);
                startActivity(intent);
            }
        });

        return searchView;
    }

    public void drawListView(ArrayList<Results> resultsArray) {
        SearchAdapter adapter = new SearchAdapter(getActivity(), resultsArray);
        searchResult.setAdapter(adapter);
        searchArtist.setEnabled(true);
    }
}

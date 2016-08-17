package yezhenwang.newgenerationspotify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Yezhen on 2016/8/3.
 */
public class ResultsAdapter extends ArrayAdapter {

    ArrayList<Results> results;
    Context context;

    public ResultsAdapter(Context context, ArrayList<Results> results) {
        super(context, R.layout.track_row, results);
        this.results = results;
        this.context = context;
    }

    class ResultsViewHolder {
        TextView trackName;
        TextView trackArtist;
        TextView trackAlbum;

        ResultsViewHolder(View view) {
            trackName = (TextView) view.findViewById(R.id.trackName);
            trackArtist = (TextView) view.findViewById(R.id.trackArtist);
            trackAlbum = (TextView) view.findViewById(R.id.trackAlbum);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ResultsViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.track_row, parent, false);
            holder = new ResultsViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ResultsViewHolder) row.getTag();
        }
        Results result = results.get(position);

        holder.trackArtist.setText(result.artistName);
        holder.trackName.setText(result.trackName);
        holder.trackAlbum.setText(result.albumName);

        return row;
    }
}

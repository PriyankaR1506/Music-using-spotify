package yezhenwang.newgenerationspotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yezhen on 2016/8/11.
 */
public class LocationAdapter extends ArrayAdapter {

    ArrayList<LocationResults> results;
    Context context;


    public LocationAdapter(Context context, ArrayList<LocationResults> results) {
        super(context, R.layout.map_row, results);
        this.results = results;
        this.context = context;
    }

    class ResultsViewHolder {
        TextView myName;
        TextView myCity;
        TextView myAddress;


        ResultsViewHolder(View v) {

            myName = (TextView) v.findViewById(R.id.name);
            myAddress = (TextView) v.findViewById(R.id.address);
            myCity = (TextView) v.findViewById(R.id.city);

        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ResultsViewHolder resultsViewHolder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.map_row, parent, false);
            resultsViewHolder = new ResultsViewHolder(row);
            row.setTag(resultsViewHolder);
        }else {
            resultsViewHolder = (ResultsViewHolder) row.getTag();
        }
        LocationResults result = results.get(position);
        resultsViewHolder.myName.setText("Events" +result.name);
        resultsViewHolder.myAddress.setText("Address : " +result.address+","+result.city+","+result.state);
        resultsViewHolder.myCity.setText("Venue : " +result.city);


        return row;
    }
}

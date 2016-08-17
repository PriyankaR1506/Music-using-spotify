package yezhenwang.newgenerationspotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Yezhen on 2016/8/4.
 */
public class SearchAdapter extends ArrayAdapter {

    ArrayList<Results> results;
    Context context;

    public SearchAdapter(Context context, ArrayList<Results> results) {
        super(context, R.layout.search_row, results);
        this.results = results;
        this.context = context;
    }

    class ResultsViewHolder {
        TextView artistName;

        ResultsViewHolder(View view) {
            artistName = (TextView) view.findViewById(R.id.artistNameS);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ResultsViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.search_row, parent, false);
            holder = new ResultsViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ResultsViewHolder) row.getTag();
        }
        Results result = results.get(position);
        holder.artistName.setText(result.artistName);

        return row;
    }
}
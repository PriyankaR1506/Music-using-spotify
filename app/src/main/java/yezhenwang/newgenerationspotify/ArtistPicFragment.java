package yezhenwang.newgenerationspotify;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dhana on 7/29/2016.
 */
public class ArtistPicFragment extends Fragment {

    private Button previousButton, nextButton;
    int count = 0;
    public int[] images = {R.drawable.adam, R.drawable.drake, R.drawable.katty, R.drawable.rihana, R.drawable.taylor};
    ImageView imageView;

    public ArtistPicFragment (){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.artist_pic_fragment, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        previousButton = (Button) view.findViewById(R.id.previous_button);
        nextButton = (Button) view.findViewById(R.id.next_button);
        final Activity mainPageActivity = getActivity();
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                MainPageActivity mainPageActivity = (MainPageActivity) getActivity();

//                mainPageActivity.incrementValue(count);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                MainPageActivity mainPageActivity = (MainPageActivity) getActivity();
            }
        });


        return view;
    }
}

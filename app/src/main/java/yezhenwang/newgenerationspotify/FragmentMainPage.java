package yezhenwang.newgenerationspotify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentMainPage extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main_page);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdater(getSupportFragmentManager()));

    }

    class MyAdater extends FragmentStatePagerAdapter {


        public MyAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0: {
                    fragment = new SearchFragment();
                    break;
                }
                case 1: {
                    fragment = new ConcertFragment();
                    break;
                }
                default: {
                    fragment = new SearchFragment();
                    break;
                }
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String returnValue = new String();
            if (position == 0) {
                returnValue = "Search Artist";
            } else {
                returnValue = "Concert";
            }
            return returnValue;
        }
    }
}
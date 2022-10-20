package com.sandeepgupta.NEWS;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class FixedTabPagerAdapter extends FragmentStatePagerAdapter {

    public FixedTabPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NewsFragment();
            case 1:
                return new SportsFragment();
            case 2:
                return new EntertainmentFragment();
            case 3:
                return new TechnologyFragment();
            case 4:
                return new ScienceFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Home";
            case 1:
                return "Sports";
            case 2:
                return "Movies";
            case 3:
                return "Tech";
            case 4:
                return "Science";

            default:
                return null;
        }
    }
}

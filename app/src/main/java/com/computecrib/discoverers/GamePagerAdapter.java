package com.computecrib.discoverers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by bhavy on 4/7/2018.
 */

public class GamePagerAdapter extends FragmentPagerAdapter {
    private String[] names = {"Challenges", "Progress"};

    public GamePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {

    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new ChallengesFragment();
            case 1:
                return new ProgressFragment();
//            case 2:
//                return new PlacesFragment();
            default:
                return new SongsFragment();
        }
    }

    @Override
    public int getCount() {
        return names.length;
    }
}

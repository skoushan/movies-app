package com.skoushan.movies.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dx166-xl on 2014-05-01.
 */
public class MovieListPagerAdapter extends FragmentPagerAdapter {
    public MovieListPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public static final String MOVIE_LISTS_KEY = "movie_lists";
    private String[] MOVIE_LISTS = {"Box Office", "In Theatres", "Upcoming", "Opening"};
    private String[] MOVIE_LISTS_PARAM = {
            "lists/movies/box_office.json?limit=16&country=us",
            "lists/movies/in_theaters.json?page_limit=10&page=1&country=us",
            "lists/movies/upcoming.json?page_limit=16&page=1&country=us",
            "lists/movies/opening.json?limit=16&country=us"
    };

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new MovieListFragment();
        Bundle args = new Bundle();

        args.putString(MOVIE_LISTS_KEY, MOVIE_LISTS_PARAM[position]);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MOVIE_LISTS[position];
    }
}

package com.skoushan.movies.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by dx166-xl on 2014-05-01.
 */
// TURN TO LIST FRAGMENT!!!
//http://developer.android.com/training/implementing-navigation/lateral.html
public class MovieListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_movielist, container, false);
        Bundle args = getArguments();

        ListView lv = (ListView) rootView.findViewById(R.id.listView);
        new GetMovies(args.getString(MovieListPagerAdapter.MOVIE_LISTS_KEY), getActivity(), lv).execute();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        Log.d("Tab tracking:", "destroyed!");
        super.onDestroyView();
    }
}

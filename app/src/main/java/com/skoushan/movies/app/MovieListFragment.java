package com.skoushan.movies.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.Map;

/**
 * Created by dx166-xl on 2014-05-01.
 */
//http://developer.android.com/training/implementing-navigation/lateral.html
public class MovieListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        MovieListAdapter mla = (MovieListAdapter) l.getAdapter();
        Map<String,String> m =  mla.getItem(position);
        String movieID = m.get(GetMovies.ID);
        Log.d("Movie click:", "id = " + movieID);

        Intent i = new Intent(getActivity(), MovieProfileActivity.class);
        i.putExtra("movie_id", movieID);
        startActivity(i);
    }

    @Override
    public void onDestroyView() {
        Log.d("Tab tracking:", "destroyed!");
        super.onDestroyView();
    }
}

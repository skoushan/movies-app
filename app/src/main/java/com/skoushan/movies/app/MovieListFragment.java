package com.skoushan.movies.app;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;

/**
 * Created by dx166-xl on 2014-05-01.
 */
// TODO TURN TO LIST FRAGMENT!!!
//http://developer.android.com/training/implementing-navigation/lateral.html
public class MovieListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        //View rootView = inflater.inflate (R.layout.fragment_movielist, container, false);
//
//        // TODO http://developer.android.com/guide/components/activities.html#SavingActivityState
//        //ListView lv = (ListView) rootView.findViewById(R.id.listView);
//        return rootView;
//    }

    @Override
    public void onDestroyView() {
        Log.d("Tab tracking:", "destroyed!");
        super.onDestroyView();
    }
}

package com.skoushan.movies.app;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;


public class SearchResultsActivity extends FragmentActivity {

    MovieListFragment lf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent){
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String query = intent.getStringExtra(SearchManager.QUERY);
        query.trim();
        query = query.replace(' ', '+');
        Log.d("Query:", query);


       // Bundle args = new Bundle();
       // args.putString(MovieListPagerAdapter.MOVIE_LISTS_KEY, query);

        //lf = new MovieListFragment();

        lf = (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.movielistfragment);

        //lf.setArguments(args);
        new GetMovies("movies.json?q=" + query, this, lf).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_results, menu);
        return true;
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

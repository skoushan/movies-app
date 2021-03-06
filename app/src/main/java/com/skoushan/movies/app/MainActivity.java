package com.skoushan.movies.app;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SearchView;


public class MainActivity extends ActionBarActivity {

    private ProgressDialog pDialog;

    ListView lv;

    MovieListPagerAdapter mListPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // http://developer.android.com/design/building-blocks/tabs.html#scrollable
        mListPagerAdapter = new MovieListPagerAdapter(getSupportFragmentManager(),this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mListPagerAdapter);

//        lv = (ListView) findViewById(R.id.listView);
//        new GetMovies(inTheatresParams, this.getApplicationContext(), lv).execute();

    }

    // http://www.androidhive.info/2013/11/android-working-with-action-bar/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if(id == R.id.action_search){
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

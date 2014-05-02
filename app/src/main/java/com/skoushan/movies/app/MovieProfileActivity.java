package com.skoushan.movies.app;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class MovieProfileActivity extends ActionBarActivity {

    String movieID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_profile);
        movieID = getIntent().getStringExtra("movie_id");

        ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        JSONObject API_result;
        try {
            API_result = new APICaller().execute("movies/" + movieID + ".json?").get();

            String title = API_result.getString("title");
            String synopsis = API_result.getString("synopsis");
            JSONObject ratings = API_result.getJSONObject("ratings");
            int critics_score = ratings.getInt("critics_score");
            int audience_score = ratings.getInt("audience_score");
            JSONObject posters = API_result.getJSONObject("posters");
            String poster_URL = posters.getString("profile");

            actionBar.setTitle(title);

            // TODO use one common image loader
            ImageLoader imageLoader = new ImageLoader(this);

            ImageView v = (ImageView) findViewById(R.id.poster);
            if(v != null){
                imageLoader.DisplayImage(poster_URL,v);
            }

            TextView title_tv = (TextView) findViewById(R.id.movie_title);
            if(title_tv != null){
                title_tv.setText(title);
            }

            TextView critics_score_tv = (TextView) findViewById(R.id.critic_score);
            if(critics_score_tv != null){
                critics_score_tv.setText("Critic score: " + critics_score + "%");
            }

            TextView audience_score_tv = (TextView) findViewById(R.id.audience_score);
            if(audience_score_tv != null){
                audience_score_tv.setText("Audience score: " + audience_score + "%");
            }

            TextView synopsis_tv = (TextView) findViewById(R.id.synopsis);
            if(synopsis_tv != null){
                synopsis_tv.setText(synopsis);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movie_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}

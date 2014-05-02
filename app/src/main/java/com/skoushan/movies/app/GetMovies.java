package com.skoushan.movies.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Async task class to get json by making HTTP call
 * */
public class GetMovies extends AsyncTask<Object, Void, ArrayList<Map<String,String>>> {

    private static String API_base_URI = "http://api.rottentomatoes.com/api/public/v1.0/";
    private static String API_key = "apikey=avmv5ydp2qu4scw4hr4ky7uz";

    public static final String TITLE = "title", THUMBNAIL = "thumbnail";

    private String params;
    private MovieListFragment lf;
    private Context c;

    private ProgressDialog pDialog;

    /**
     *  @param params
     * @param c
     * @param lf
     */
    public GetMovies(String params, Context c, MovieListFragment lf){
        super();
        this.params = params;
        this.c = c;
        this.lf = lf;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        // TODO
//        pDialog = new ProgressDialog(c);
//        pDialog.setMessage("Loading...");
//        pDialog.setCancelable(false);
//        pDialog.show();

    }

    @Override
    protected ArrayList<Map<String,String>> doInBackground(Object... args) {
        ArrayList<Map<String,String>> movieList = new ArrayList<Map<String, String>>();

        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(API_base_URI + params + "&" + API_key, ServiceHandler.GET);

        Log.d("Response: ", "> " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                // Getting JSON Array node
                JSONArray movies = jsonObj.getJSONArray("movies");

                // looping through All Contacts
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject c = movies.getJSONObject(i);

                    String title = c.getString(TITLE);
                    String thumbnail = c.getJSONObject("posters").getString(THUMBNAIL);

                    // tmp hashmap for single movie
                    HashMap<String, String> movie = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    movie.put(TITLE, title);
                    movie.put(THUMBNAIL, thumbnail);

                    // adding contact to contact list
                    movieList.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return movieList;
    }

    @Override
    protected void onPostExecute(ArrayList<Map<String,String>> result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        // TODO
//        if (pDialog.isShowing())
//            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
//        ListAdapter adapter = new SimpleAdapter(
//                a, result,
//                R.layout.list_item, new String[] { TITLE, THUMBNAIL}, new int[] { R.id.textView,
//                R.id.imageView});

        MovieListAdapter adapter = new MovieListAdapter(c, R.layout.list_item, result);

        lf.setListAdapter(adapter);
    }

}

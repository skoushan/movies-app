package com.skoushan.movies.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Async task class to get json by making HTTP call
 * */
public class GetMovies extends AsyncTask<Object, Void, ArrayList<HashMap<String,String>>> {

    private static String API_base_URI = "http://api.rottentomatoes.com/api/public/v1.0/";
    private static String API_key = "apikey=avmv5ydp2qu4scw4hr4ky7uz";

    private static final String TITLE = "title", THUMBNAIL = "thumbnail";

    private String params;
    private ListView lv;
    private Activity a;

    private ProgressDialog pDialog;

    /**
     *
     * @param params
     * @param a
     * @param lv
     */
    public GetMovies(String params, Activity a, ListView lv){
        super();
        this.params = params;
        this.a = a;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(a);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected ArrayList<HashMap<String,String>> doInBackground(Object... args) {
        ArrayList<HashMap<String,String>> movieList = new ArrayList<HashMap<String, String>>();

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
    protected void onPostExecute(ArrayList<HashMap<String,String>> result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(
                a, result,
                R.layout.list_item, new String[] { TITLE, THUMBNAIL}, new int[] { R.id.textView,
                R.id.imageView});

        lv.setAdapter(adapter);
    }

}

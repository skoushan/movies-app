package com.skoushan.movies.app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dx166-xl on 2014-05-02.
 */
public class APICaller extends AsyncTask<String, Void, JSONObject> {

    private static String API_base_URI = "http://api.rottentomatoes.com/api/public/v1.0/";
    private static String API_key = "apikey=avmv5ydp2qu4scw4hr4ky7uz";

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jsonObject = null;

        ServiceHandler sh = new ServiceHandler();
        String jsonStr = sh.makeServiceCall(API_base_URI + params[0] + "&" + API_key, ServiceHandler.GET);

        if(jsonStr != null){
            try {
               jsonObject = new JSONObject(jsonStr);
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
        else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return jsonObject;
    }
}

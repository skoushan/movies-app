package com.skoushan.movies.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dx166-xl on 2014-05-01.
 */
//http://stackoverflow.com/questions/541966/how-do-i-do-a-lazy-load-of-images-in-listview
public class MovieListAdapter extends ArrayAdapter<Map<String,String>>{
    public MovieListAdapter(Context context, int resource) {
        super(context, resource);
    }

    ImageLoader imageLoader;

    public MovieListAdapter(Context context, int resource, ArrayList<Map<String, String>> items) {
        super(context, resource, items);
        imageLoader = new ImageLoader(getContext());
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){


        if(convertView == null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.list_item, null);
        }

        Map<String,String> movie = getItem(position);

        // https://github.com/thest1/LazyList
        if(movie != null){

            ImageView v = (ImageView) convertView.findViewById(R.id.movieThumbnail);
            if(v != null){
                imageLoader.DisplayImage(getItem(position).get(GetMovies.THUMBNAIL),v);
            }

            TextView tv = (TextView) convertView.findViewById(R.id.movieDescription);
            if(tv != null){
                tv.setText(getItem(position).get(GetMovies.TITLE));
            }
        }

        return convertView;
    }
}

package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private final String murl;
    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        murl= url;

    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        if ( murl == null) {
            return null;
        }
        return QuakeUtils.fetchQuakeData(murl);
    }



    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}

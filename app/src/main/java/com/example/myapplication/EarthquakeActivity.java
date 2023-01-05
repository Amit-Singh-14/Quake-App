/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private static final int EARTHQUAKE_LOADER_ID = 1;
    private CustomAdapter customAdapter;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public static final String USRG_URL = " https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&limit=20";
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        mEmptyStateTextView = (TextView)findViewById(R.id.emptyview);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        customAdapter = new CustomAdapter(
                this, new ArrayList<Earthquake>());
        earthquakeListView.setAdapter(customAdapter);


//        QuakeAsync quakeAsync = new QuakeAsync();
//        quakeAsync.execute(USRG_URL);

        // Create a fake list of earthquake locations.
//        ArrayList<Earthquake> earthquakes = ;
//        earthquakes.add(new Earthquake("6.2","San Francisco","10-12-2022") );
//        earthquakes.add(new Earthquake("6.2","London","10-12-2022"));
//        earthquakes.add(new Earthquake("6.2","Tokyo","10-12-2022"));
//        earthquakes.add(new Earthquake("6.2","Mexico City","10-12-2022"));
//        earthquakes.add(new Earthquake("6.2","Moscow","10-12-2022"));
//        earthquakes.add(new Earthquake("6.2","Rio de Janeiro","10-12-2022"));
//        earthquakes.add(new Earthquake("6.2","Paris","10-12-2022"));

        // Find a reference to the {@link ListView} in the layout


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
        }
        else{
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText("no internet connection");
        }

    }



    @NonNull
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.v(LOG_TAG,"oncreate");
        return new EarthquakeLoader(this,USRG_URL);
    }



    @Override
    public void onLoadFinished(@NonNull Loader<List<Earthquake>> loader, List<Earthquake> data) {
//        Log.v(LOG_TAG,""+data);

        View laodingindicator = findViewById(R.id.loading_indicator);
            laodingindicator.setVisibility(View.GONE);



        if(data != null && !data.isEmpty()){
            customAdapter.addAll(data);
        }else{
            mEmptyStateTextView.setText("no_earthquake");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Earthquake>> loader) {
    customAdapter.clear();
    }




//
//    private class QuakeAsync extends AsyncTask<String,Void,List<Earthquake>> {
//
//        @Override
//        protected List<Earthquake> doInBackground(String... url) {
//            if (url.length < 1 || url[0] == null) {
//                return null;
//            }
//            return QuakeUtils.fetchQuakeData(url[0]);
//        }
//
//        @Override
//        protected void onPostExecute(List<Earthquake> earthquakes) {
//
//           customAdapter.clear();
//           if(earthquakes !=null && !earthquakes.isEmpty()){
//               customAdapter.addAll(earthquakes);
//           }
//        }
//    }
    }


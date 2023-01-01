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

import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public static final String USRG_URL = " https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&limit=20";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        QuakeAsync quakeAsync = new QuakeAsync();
        quakeAsync.execute(USRG_URL);

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


    }


    private class QuakeAsync extends AsyncTask<String,Void,ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... url) {
            if (url.length < 1 || url[0] == null) {
                return null;
            }
            return QuakeUtils.fetchQuakeData(url[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {

            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            CustomAdapter adapter = new CustomAdapter(
                    EarthquakeActivity.this, earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);
        }
    }
    }


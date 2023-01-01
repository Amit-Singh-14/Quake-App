package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Earthquake> {

    public CustomAdapter(@NonNull Activity context, @NonNull ArrayList<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if ((listItemView == null)){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Earthquake earth = getItem(position);


        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String output = decimalFormat.format(earth.getMagnittude());

        TextView magnitude = listItemView.findViewById(R.id.magnitude);
        magnitude.setText(output);

        GradientDrawable magnitudecircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(earth.getMagnittude());
        Log.v("EarthQuakeActivity",""+magnitudeColor + " " + earth.getMagnittude() + magnitudecircle);

        magnitudecircle.setColor(magnitudeColor);




        String string = earth.getCity();
        String [] parts = new String[2];


        if(string.contains(",")){

            parts = string.split(",");
        }else{
            parts[0] = "Near the";
            parts[1] = string;

        }

        Log.v("EarthquakeActivity",parts[0]+" " + parts[1]);
        TextView city  = listItemView.findViewById(R.id.primary_location);
        city.setText(parts[1]);

        TextView place  = listItemView.findViewById(R.id.location_offset);
        place.setText(parts[0]);

        Date dateobject = new Date(earth.getDate());

        TextView date = listItemView.findViewById(R.id.date);
        date.setText(formatDate(dateobject));

        TextView time = listItemView.findViewById(R.id.time);
        time.setText(formatetime(dateobject));




        return listItemView;
    }




    private String formatDate(Date dateobject){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return simpleDateFormat.format(dateobject);

    }
    private String formatetime(Date dateobject){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        return simpleDateFormat.format(dateobject);
    }


    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        Log.v("EarthquakeAcitivity",magnitudeColorResourceId + "");
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}


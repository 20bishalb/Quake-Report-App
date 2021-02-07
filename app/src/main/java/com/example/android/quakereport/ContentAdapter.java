package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

import androidx.core.content.ContextCompat;

public class ContentAdapter extends ArrayAdapter<Content> {

    private static final String LOCATION_SEPARATOR = "of";

    public ContentAdapter(Activity context, ArrayList<Content> contents) {
        super(context, 0, contents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Content currentContent = getItem(position);

        // Magnitude TextView
        TextView richterScaleTextView = (TextView)listItemView.findViewById(R.id.richter_scale);
        String formattedMag = formatMag(currentContent.getRichterScale());
        richterScaleTextView.setText(formattedMag);

        // Background Color
        GradientDrawable magnitudeCircle = (GradientDrawable)richterScaleTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentContent.getRichterScale());
        magnitudeCircle.setColor(magnitudeColor);

        // Location
        String exactLocation = currentContent.getCity();
        String locationOffset;
        String primaryLocation;

        if (exactLocation.contains(LOCATION_SEPARATOR)) {
            String parts[] = exactLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1].trim();
        }else {
            locationOffset = getContext().getString(R.string.near);
            primaryLocation = exactLocation.trim();
        }

        TextView locationOffsetTextView = (TextView)listItemView.findViewById(R.id.location_offset);
        locationOffsetTextView.setText(locationOffset);

        TextView primaryLocationTextView = (TextView)listItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation);

        // Date and Time TextView
        Date dateObject = new Date(currentContent.getTimeInMillisecond());

        String formattedDate = formatDate(dateObject);
        TextView dateView = (TextView)listItemView.findViewById(R.id.date);
        dateView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        TextView timeView = (TextView)listItemView.findViewById(R.id.time);
        timeView.setText(formattedTime);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
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
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private String formatMag(double doubleRichterScale) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(doubleRichterScale);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}

package com.example.android.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ArrayList<Content> earthquakes = QueryUtils.extractEarthquakes();

        ListView earthquakeListView = (ListView)findViewById(R.id.list);

        final ContentAdapter adapter = new ContentAdapter(this, earthquakes);
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Find the current earthquake that was clicked on
                Content currentEarthquake = adapter.getItem(position);

                // Convert the String Url into Uri object to pass into the Intent constructor
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new Intent to view the earthquake Uri
                Intent earthquakeIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(earthquakeIntent);
            }
        });
    }
}
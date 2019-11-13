package me.oz.demo.mapbox.mapbox;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import me.oz.demo.mapbox.R;

public class MapBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_box);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapBoxActivityFragment()).commit();

    }

}

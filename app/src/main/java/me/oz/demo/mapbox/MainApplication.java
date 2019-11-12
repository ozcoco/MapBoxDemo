package me.oz.demo.mapbox;

import android.app.Application;

import me.oz.demo.mapbox.helper.ObjectBox;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ObjectBox.init(this);

    }
}

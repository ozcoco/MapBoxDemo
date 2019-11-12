package me.oz.demo.mapbox.helper;

import android.content.Context;

import io.objectbox.BoxStore;
import me.oz.demo.mapbox.beans.MyObjectBox;

public final class ObjectBox {

    private static BoxStore  boxStore;

    private ObjectBox() {
    }

    public static void init(Context context){

        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();

    }

    public static BoxStore get() {
        return boxStore;
    }


}

package me.oz.demo.mapbox.mapbox;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.china.constants.ChinaStyle;
import com.mapbox.mapboxsdk.plugins.china.maps.ChinaMapView;
import com.mapbox.mapboxsdk.plugins.localization.LocalizationPlugin;
import com.mapbox.mapboxsdk.style.layers.HillshadeLayer;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.RasterDemSource;

import java.util.ArrayList;
import java.util.List;

import me.oz.demo.mapbox.R;

public class MapBoxActivityFragment extends Fragment {

    public final static String TAG = MapBoxActivityFragment.class.getCanonicalName();

    private ChinaMapView mapView;

    public final static String MAPBOX_TOKEN = "sk.eyJ1Ijoib3pjb21jbiIsImEiOiJjazJ4MmpzbHIwa2ZiM2NscTFnejRvcTM0In0.j_vb4cgI5nqn2NOTXSqFCQ";

    private static final String LAYER_ID = "hillshade-layer";
    private static final String SOURCE_ID = "hillshade-source";
    private static final String SOURCE_URL = "mapbox://styles/mapbox/dark-zh-v1";
    private static final String HILLSHADE_HIGHLIGHT_COLOR = "#008924";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(getContext(), MAPBOX_TOKEN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_map_box, container, false);

        mapView = root.findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapStyle(mapboxMap);

//                loacation(mapboxMap);

                annotations(mapboxMap);
            }
        });
    }


    private void annotations(@NonNull MapboxMap mapboxMap) {

        addMark(mapboxMap);

        addpolygon(mapboxMap);

        addPolyline(mapboxMap);

    }

    private void addMark(@NonNull MapboxMap mapboxMap) {

        mapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.85819, 2.29458))
                .title("Eiffel Tower"));


        // Create an Icon object for the marker to use
        IconFactory iconFactory = IconFactory.getInstance(getContext());
//        Icon icon = iconFactory.fromResource(R.drawable.ic_action_name);
        Icon icon = iconFactory.fromAsset("ic_action_name");

// Add the marker to the map
        mapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(-37.821648, 144.978594))
                .icon(icon))
                .setTitle("icon marker");

        mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

// Show a toast with the title of the selected marker
                Toast.makeText(getContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    private void addPolyline(@NonNull MapboxMap mapboxMap) {


        List<LatLng> points = new ArrayList<>();

        points.add(new LatLng(45.522585, -122.685699));
        points.add(new LatLng(45.534611, -122.708873));
        points.add(new LatLng(45.530883, -122.678833));
        points.add(new LatLng(45.547115, -122.667503));
        points.add(new LatLng(45.530643, -122.660121));
        points.add(new LatLng(45.533529, -122.636260));
        points.add(new LatLng(45.521743, -122.659091));
        points.add(new LatLng(45.510677, -122.648792));
        points.add(new LatLng(45.515008, -122.664070));
        points.add(new LatLng(45.502496, -122.669048));

        mapboxMap.addPolyline(new PolylineOptions()
                .addAll(points)
                .color(Color.parseColor("#3bb2d0"))
                .width(2));
    }

    private void addpolygon(@NonNull MapboxMap mapboxMap) {

        List<LatLng> polygonLatLngList = new ArrayList<>();

        polygonLatLngList.add(new LatLng(45.522585, -122.685699));
        polygonLatLngList.add(new LatLng(45.534611, -122.708873));
        polygonLatLngList.add(new LatLng(45.530883, -122.678833));
        polygonLatLngList.add(new LatLng(45.547115, -122.667503));
        polygonLatLngList.add(new LatLng(45.530643, -122.660121));
        polygonLatLngList.add(new LatLng(45.533529, -122.636260));
        polygonLatLngList.add(new LatLng(45.521743, -122.659091));
        polygonLatLngList.add(new LatLng(45.510677, -122.648792));
        polygonLatLngList.add(new LatLng(45.515008, -122.664070));
        polygonLatLngList.add(new LatLng(45.502496, -122.669048));
        polygonLatLngList.add(new LatLng(45.515369, -122.678489));
        polygonLatLngList.add(new LatLng(45.506346, -122.702007));
        polygonLatLngList.add(new LatLng(45.522585, -122.685699));

        mapboxMap.addPolygon(new PolygonOptions()
                .addAll(polygonLatLngList)
                .fillColor(Color.parseColor("#3bb2d0")));

    }


    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent(@NonNull MapboxMap mapboxMap, @NonNull Style style) {

        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Activate with a built LocationComponentActivationOptions object
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(getContext(), style).build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING_GPS);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

    /*        LatLngBounds latLngBounds = new LatLngBounds.Builder()
                    .include(new LatLng(locationComponent.getLastKnownLocation().getLatitude(),
                            locationComponent.getLastKnownLocation().getLongitude()))
                    .build();*/

            //            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));

            CameraPosition position = new CameraPosition.Builder()
                    .target(new LatLng(51.50550, -0.07520))
                    .zoom(10)
                    .tilt(20)
                    .build();

            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 10);

        } else {

            PermissionsManager permissionsManager = new PermissionsManager(new PermissionsListener() {
                @Override
                public void onExplanationNeeded(List<String> permissionsToExplain) {

                }

                @Override
                public void onPermissionResult(boolean granted) {

                }
            });

            permissionsManager.requestLocationPermissions(getActivity());

        }
    }

    private void loacation(@NonNull MapboxMap mapboxMap) {

/*//                location
        LocationLayerPlugin locationLayerPlugin = new LocationLayerPlugin(mapView, mapboxMap);

        locationLayerPlugin.addCompassListener(new CompassListener() {
            @Override
            public void onCompassChanged(float userHeading) {

                Log.d(TAG, String.format("--->userHeading:%d", userHeading));

            }

            @Override
            public void onCompassAccuracyChange(int compassStatus) {

            }
        });*/

    }

    private void localization(@NonNull MapboxMap mapboxMap, @NonNull Style style) {

        //                localization
        LocalizationPlugin localizationPlugin = new LocalizationPlugin(mapView, mapboxMap, style);

        try {
            localizationPlugin.matchMapLanguageWithDeviceDefault();
        } catch (RuntimeException exception) {
            Log.d(TAG, exception.toString());
        }
    }


    private void expression(@NonNull Style style) {


        SymbolLayer countLayer = new SymbolLayer("SYMBOL_LAYER_COUNT_LAYER_ID", "GEOJSON_SOURCE_ID");

        countLayer.setProperties(
        );
        style.addLayer(countLayer);

    }


    private void mapStyle(@NonNull MapboxMap mapboxMap) {

        mapboxMap.setStyle(Style.DARK, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {

                enableLocationComponent(mapboxMap, style);

                localization(mapboxMap, style);

                StringBuilder builder = new StringBuilder("--->\n");

                builder.append(style.getJson());

                for (Layer layer : style.getLayers()) {

                    builder.append("--->" + layer.getId() + "\n");
                }

                Log.d(TAG, builder.toString());


                // Add hillshade data source to map
                RasterDemSource rasterDemSource = new RasterDemSource(SOURCE_ID, SOURCE_URL);
                style.addSource(rasterDemSource);

                // Create and style a hillshade layer to add to the map
                HillshadeLayer hillshadeLayer = new HillshadeLayer(LAYER_ID, SOURCE_ID).withProperties(
                        PropertyFactory.hillshadeHighlightColor(Color.parseColor(HILLSHADE_HIGHLIGHT_COLOR)),
                        PropertyFactory.hillshadeShadowColor(Color.BLACK)
                );

// Add the hillshade layer to the map
                style.addLayerBelow(hillshadeLayer, "aerialway");

            }

        });

    }


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}

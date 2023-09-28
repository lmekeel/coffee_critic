package com.example.coffeecritic;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;


import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;


public class HomeFragment extends Fragment implements OnMapReadyCallback {



    //MAP VARIABLES
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private MapView mapView;
    private GoogleMap googleMap;
    private Location location = null;
    private LocationManager locationManager;

    //LIST VARIABLES
    RecyclerView coffeeShopList;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        coffeeShopList = (RecyclerView) rootView.findViewById(R.id.coffee_shop_list);


        mapView = (MapView) rootView.findViewById(R.id.google_map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        return rootView;
    }


     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap map) {
            googleMap = map;
            initMap();

            googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    getCurrentLocation();
                    return false;
                }
            });


    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            drawMarker(location, getText(R.string.i_am_here).toString());
            locationManager.removeUpdates(locationListener);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


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
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    private void initMap() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setAllGesturesEnabled(true);
                googleMap.getUiSettings().setZoomControlsEnabled(true);
            }
        } else {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 12);
            }
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 13);
            }
        }

    }

    private void getCurrentLocation() {
        Toast.makeText(getActivity(), "Getting location", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (location != null) {
                    drawMarker(location, getText(R.string.i_am_here).toString());
                }else{

                locationManager = (LocationManager)  getActivity().getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
                locationManager.requestLocationUpdates(bestProvider, 1000, 0, locationListener);
            }
            }
        else {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }

    }



    private void drawMarker(Location location, String title) {
        //googleMap.clear();
        if (this.googleMap != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));

            //MOVED THIS CODE TO BOTTOM
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(title)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            /*
                googleMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius(1000) //WILL BE SET BY THE USER
                    .fillColor(Color.TRANSPARENT)
                    .strokeColor(Color.BLUE));
             */


        }
    }


/*
------------------------------------Setting up list of coffee shops------------------------------
 */



    public void addCoffeeShop(){


    }





}
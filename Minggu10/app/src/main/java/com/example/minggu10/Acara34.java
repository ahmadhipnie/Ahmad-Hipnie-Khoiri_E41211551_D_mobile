package com.example.minggu10;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Acara34 extends AppCompatActivity implements OnMapReadyCallback {
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private Location lastLocation;
    private FusedLocationProviderClient FusedLocationProviderClient;
    private LocationCallback locationCallback;
    private GoogleMap gmaps;

    private Circle lingkaran;

    // jangkauan untuk membuka wbesite
    private static final double CIRCLE_RADIUS = 500;
    private static final double CIRCLE_LAT = -8.1584;
    private static final double CIRCLE_LONG = 113.7218;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acara34);

        // Initialize FusedLocationProviderClient
        FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_PERMISSION);
                Toast.makeText(this, "lokasimu diterima lerrrrr", Toast.LENGTH_LONG).show();
                return;
            }
            gmaps.setMyLocationEnabled(true);
            startLocationUpdates();
        } else {
            Toast.makeText(this, "lokasimu ditolak lerrrrr", Toast.LENGTH_LONG).show();
        }
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(20000);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();

                if (location != null) {
                    if (isLocationSignificantlyChanged(location)) {
                        Toast.makeText(getApplicationContext(), " Location Latitude: " + location.getLatitude() +
                                "\nLongitude: " + location.getLongitude(), Toast.LENGTH_LONG).show();

                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        gmaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                        gmaps.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                        gmaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));


                        //latitude dan lpngitude untuk jangkauan
                        double manualLatitude = -8.1584;
                        double manualLongitude = 113.7218;
                        double radiusInMeters = 500; //lingkaran radius per meter dari jangkauan saat membuka web
                        CircleOptions circleOptions = new CircleOptions()
                                .center(new LatLng(manualLatitude, manualLongitude))
                                .radius(radiusInMeters)
                                .strokeWidth(2)
                                .strokeColor(R.color.colorCircle)
                                .fillColor(R.color.colorCircleFill);
                        lingkaran = gmaps.addCircle(circleOptions);

                        proximity();

                        lastLocation = location;
                    }
                }
            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            FusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmaps = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
            return;
        }

        // Enable My Location on the map
        gmaps.setMyLocationEnabled(true);

        // Start listening for location updates
        startLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (locationCallback != null) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (locationCallback != null) {
            FusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    private boolean isLocationSignificantlyChanged(Location location) {

        if (lastLocation == null) {

            return true;
        }


        float distance = location.distanceTo(lastLocation);


        if (distance > 20) {
            return true;
        }

        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(mapFragment).commit();
        }
    }

    private void proximity() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        float[] distance = new float[2];
        Location.distanceBetween(CIRCLE_LAT, CIRCLE_LONG,
                lingkaran.getCenter().latitude, lingkaran.getCenter().longitude, distance);
        if (distance[0] > CIRCLE_RADIUS) {
            Toast.makeText(getBaseContext(), "Anda diluar jangkauan", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Anda didalam jangkauan", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);
            locationManager.addProximityAlert(latLng.latitude, latLng.longitude, (float) CIRCLE_RADIUS, -1, pendingIntent);
        }
        lastLocation = location;
    }

}

package ru.gozhan.lab06;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.gozhan.lab06.adapter.SightListAdapter;
import ru.gozhan.lab06.model.Sight;
import ru.gozhan.lab06.model.User;
import ru.gozhan.lab06.util.Creator;

public class SightListActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private ListView sightListView;
    private SightListAdapter sightListAdapter;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    private User user;
    private List<Sight> userCitySights;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_list);

        user = new User(
                getIntent().getStringExtra("surname"),
                getIntent().getStringExtra("name"),
                getIntent().getStringExtra("email")
        );

        String greeting = "Привет, " + user.getName() + "!";
        TextView helloUserTextView = findViewById(R.id.helloUser);
        helloUserTextView.setText(greeting);

        sightListView = findViewById(R.id.sightList);

        userCitySights = new ArrayList<>();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        createLocationRequest();
        createLocationCallback();

        if (ContextCompat.checkSelfPermission(SightListActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocation();
        } else {
            ActivityCompat.requestPermissions(SightListActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        Button buttonWhereIsMe = findViewById(R.id.buttonWhereIsMe);
        buttonWhereIsMe.setOnClickListener(v -> startActivity(new Intent(SightListActivity.this, LocationActivity.class)));

        updateSightList();
    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }

                // Получить первое местоположение из результатов
                Location location = locationResult.getLocations().get(0);

                // Остановить обновление местоположения
                stopLocationUpdates();

                // Определить город пользователя
                getAddressFromLocation(location);

                // Обновить список достопримечательностей
                updateSightList();
            }
        };
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    private void getAddressFromLocation(Location location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                String city = address.getLocality();
                if (city != null && !city.isEmpty()) {
                    user.setCity(city);
                    Toast.makeText(this, "Текущий город: " + city, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateSightList() {
        userCitySights.clear();

        List<Sight> allSights = Creator.createSights();

        for (Sight sight : allSights) {
            Log.d("Sight city", "Sight city: " + sight.getCity());
        }

        String userCity = user.getCity();
        Log.d("SightListActivity", "User city: " + userCity);

        if (userCity != null) {
            for (Sight sight : allSights) {
                if (userCity.equals(sight.getCity())) {
                    userCitySights.add(sight);
                }
            }
        }

        sightListAdapter = new SightListAdapter(this, userCitySights);
        sightListView.setAdapter(sightListAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation();
            } else {
                Toast.makeText(this, "Отказано в доступе к местоположению", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

}

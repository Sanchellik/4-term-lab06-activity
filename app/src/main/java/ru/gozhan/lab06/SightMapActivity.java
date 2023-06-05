package ru.gozhan.lab06;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

import ru.gozhan.lab06.model.Sight;

public class SightMapActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_map);

        Sight sight = new Sight(
                getIntent().getStringExtra("sightTitle"),
                getIntent().getStringExtra("sightFullDescription"),
                new Point(
                        getIntent().getDoubleExtra("sightLatitude", 0.0),
                        getIntent().getDoubleExtra("sightLongitude", 0.0)
                )
        );

        mapView = findViewById(R.id.mapView);
        mapView.getMap().move(
                new CameraPosition(sight.getCoordinates(), 15.0f, 0.0f, 0.0f)
        );

        mapView.getMap().getMapObjects().addPlacemark(sight.getCoordinates());

        TextView mapTitleTextView = findViewById(R.id.mapTitle);
        mapTitleTextView.setText((sight.getTitle()));

        TextView descriptionTitleTextView = findViewById(R.id.discTitle);
        descriptionTitleTextView.setText((sight.getFullDescription()));
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }

}

package ru.gozhan.lab06;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.gozhan.lab06.adapter.SightListAdapter;
import ru.gozhan.lab06.model.Sight;
import ru.gozhan.lab06.model.User;
import ru.gozhan.lab06.util.Creator;

public class SightListActivity extends AppCompatActivity {

    private ListView sightListView;
    private SightListAdapter sightListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_list);

        User user = new User(
                getIntent().getStringExtra("surname"),
                getIntent().getStringExtra("name"),
                getIntent().getStringExtra("email")
        );

        String greeting = "Hello, " + user.getName() + "!";
        TextView helloUserTextView = findViewById(R.id.helloUser);
        helloUserTextView.setText(greeting);

        sightListView = findViewById(R.id.sightList);

        List<Sight> sights = Creator.createSights();

        sightListAdapter = new SightListAdapter(this, sights);
        sightListView.setAdapter(sightListAdapter);

        sightListView.setOnItemClickListener((parent, view, position, id) -> {
            Sight selectedSight = sights.get(position);

            Intent intent = new Intent(SightListActivity.this, SightMapActivity.class);
            intent.putExtra("sightTitle", selectedSight.getTitle());
            intent.putExtra("sightFullDescription", selectedSight.getFullDescription());
            intent.putExtra("sightLatitude", selectedSight.getCoordinates().getLatitude());
            intent.putExtra("sightLongitude", selectedSight.getCoordinates().getLongitude());
            startActivity(intent);
        });

        Button buttonWhereIsMe = findViewById(R.id.buttonWhereIsMe);
        buttonWhereIsMe.setOnClickListener(v -> startActivity(new Intent(SightListActivity.this, LocationActivity.class)));
    }

}

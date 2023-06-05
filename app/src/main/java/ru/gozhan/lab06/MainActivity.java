package ru.gozhan.lab06;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.MapKitFactory;

public class MainActivity extends AppCompatActivity {

    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapKitFactory.setApiKey("9fb0904f-cb96-4f7d-8c37-01ec5541b2f4");
        MapKitFactory.initialize(this);

        Button buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(v -> {

            String surname = ((EditText) findViewById(R.id.surnameInput)).getText().toString();
            String name = ((EditText) findViewById(R.id.nameInput)).getText().toString();
            String email = ((EditText) findViewById(R.id.emailInput)).getText().toString();

            Intent intent = new Intent(MainActivity.this, SightListActivity.class);

            intent.putExtra("surname", surname);
            intent.putExtra("name", name);
            intent.putExtra("email", email);

            startActivity(intent);
        });
    }

    //TODO на listSight дать возможность выбрать, чем отображать, выпадающий список
    //TODO по gps показывать достопримечательности текущего города

}
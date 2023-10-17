package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nameInput, locationInput, dateInput, parkingAvailableInput, lengthInput, difficultyLevelInput, descriptionInput;
    Button addButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameInput = findViewById(R.id.hike_name_text);
        locationInput = findViewById(R.id.hike_location_text);
        dateInput = findViewById(R.id.hike_date_text);
        parkingAvailableInput = findViewById(R.id.hike_parking_available_text);
        lengthInput = findViewById(R.id.hike_length_text);
        difficultyLevelInput = findViewById(R.id.hike_difficulty_level_text);
        descriptionInput = findViewById(R.id.hike_description_text);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);
                databaseHelper.addNewHike(
                        nameInput.getText().toString().trim(),
                        locationInput.getText().toString().trim(),
                        dateInput.getText().toString().trim(),
                        parkingAvailableInput.getText().toString().trim(),
                        lengthInput.getText().toString().trim(),
                        difficultyLevelInput.getText().toString().trim(),
                        descriptionInput.getText().toString().trim()
                );
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
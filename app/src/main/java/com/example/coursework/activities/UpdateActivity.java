package com.example.coursework.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coursework.database.DatabaseHelper;
import com.example.coursework.R;
import com.example.coursework.fragment.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateActivity extends AppCompatActivity {

    EditText nameInputUpdate, locationInputUpdate, dateInputUpdate, lengthInputUpdate, estimatedTimeUpdate, descriptionInputUpdate;
    RadioGroup parkingRadioGroup;
    Spinner hikeWeatherForecastSpinner, hikeDifficultyLevelSpinner;
    RadioButton radioButtonYes, radioButtonNo;
    Button updateButton, deleteButton;
    FloatingActionButton backButton;
    String id, name, location, date, parkingAvailable, length, weatherForecast, estimatedTime, difficultyLevel, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameInputUpdate = findViewById(R.id.hike_name_text_update);
        locationInputUpdate = findViewById(R.id.hike_location_text_update);
        dateInputUpdate = findViewById(R.id.hike_date_text_update);
        lengthInputUpdate = findViewById(R.id.hike_length_text_update);
        estimatedTimeUpdate = findViewById(R.id.hike_time_estimated_text_update);
        descriptionInputUpdate = findViewById(R.id.hike_description_text_update);
        hikeWeatherForecastSpinner = findViewById(R.id.hike_weather_forecast_spinner);
        hikeDifficultyLevelSpinner = findViewById(R.id.hike_difficulty_level_spinner);
        parkingRadioGroup = findViewById(R.id.parking_available_radio_group);
        radioButtonYes = findViewById(R.id.radio_yes);
        radioButtonNo = findViewById(R.id.radio_no);

        deleteButton = findViewById(R.id.deleteButton);
        updateButton = findViewById(R.id.updateButton);
        backButton = findViewById(R.id.return_button);

        // First we call this
        getData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                name = nameInputUpdate.getText().toString().trim();
                location = locationInputUpdate.getText().toString().trim();
                date = dateInputUpdate.getText().toString().trim();
                length = lengthInputUpdate.getText().toString().trim();
                estimatedTime = estimatedTimeUpdate.getText().toString().trim();

                description = descriptionInputUpdate.getText().toString().trim();

                int selectedId = parkingRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                parkingAvailable = selectedRadioButton.getText().toString();

                weatherForecast = hikeWeatherForecastSpinner.getSelectedItem().toString();
                difficultyLevel = hikeDifficultyLevelSpinner.getSelectedItem().toString();

                databaseHelper.updateHikeInformation(id, name, location, date, parkingAvailable, length, weatherForecast, estimatedTime, difficultyLevel, description);

                // Return HomeFragment
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                intent.putExtra("fragmentToLoad", "home_fragment");
                startActivity(intent);
                finish(); // end UpdateActivity
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToHomeFragment();
            }
        });
    }

    public void getData(){
        if (
            getIntent().hasExtra("id") &&
            getIntent().hasExtra("name") &&
            getIntent().hasExtra("location") &&
            getIntent().hasExtra("date") &&
            getIntent().hasExtra("parkingAvailable") &&
            getIntent().hasExtra("length") &&
            getIntent().hasExtra("weatherForecast") &&
            getIntent().hasExtra("estimatedTime") &&
            getIntent().hasExtra("difficultyLevel") &&
            getIntent().hasExtra("description")
        ){
            // Getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            location = getIntent().getStringExtra("location");
            date = getIntent().getStringExtra("date");
            parkingAvailable = getIntent().getStringExtra("parkingAvailable");
            length = getIntent().getStringExtra("length");
            weatherForecast = getIntent().getStringExtra("weatherForecast");
            String weatherForecast = getIntent().getStringExtra("weatherForecast");
            estimatedTime = getIntent().getStringExtra("estimatedTime");
            String difficultyLevel = getIntent().getStringExtra("difficultyLevel");
            description = getIntent().getStringExtra("description");

            // Setting spinner data
            setupSpinner(hikeWeatherForecastSpinner, R.array.weather_forecast_array, weatherForecast);
            setupSpinner(hikeDifficultyLevelSpinner, R.array.difficulty_level_array, difficultyLevel);

            // Setting intent data
            nameInputUpdate.setText(name);
            locationInputUpdate.setText(location);
            dateInputUpdate.setText(date);
            lengthInputUpdate.setText(length);
            estimatedTimeUpdate.setText(estimatedTime);
            descriptionInputUpdate.setText(description);

            // Setting radio button for parking availability
            if (parkingAvailable.equals("Yes")) {
                radioButtonYes.setChecked(true);
            } else if (parkingAvailable.equals("No")) {
                radioButtonNo.setChecked(true);
            }
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    public void setupSpinner(Spinner spinner, int arrayResource, String selectedValue) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayResource, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        int position = adapter.getPosition(selectedValue);
        spinner.setSelection(position);
    }
    public void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.deleteOneHikeInformation(id);
                // Return HomeFragment
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                intent.putExtra("fragmentToLoad", "home_fragment");
                startActivity(intent);
                finish(); // end UpdateActivity
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void returnToHomeFragment(){
        // Return HomeFragment
        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
        intent.putExtra("fragmentToLoad", "home_fragment");
        startActivity(intent);
        finish(); // end UpdateActivity
    }
}

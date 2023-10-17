package com.example.coursework;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nameInputUpdate, locationInputUpdate, dateInputUpdate, parkingAvailableInputUpdate, lengthInputUpdate, difficultyLevelInputUpdate, descriptionInputUpdate;
    Button updateButton, backButton, deleteButton;
    String id, name, location, date, parkingAvailable, length, difficultyLevel, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameInputUpdate = findViewById(R.id.hike_name_text_update);
        locationInputUpdate = findViewById(R.id.hike_location_text_update);
        dateInputUpdate = findViewById(R.id.hike_date_text_update);
        parkingAvailableInputUpdate = findViewById(R.id.hike_parking_available_text_update);
        lengthInputUpdate = findViewById(R.id.hike_length_text_update);
        difficultyLevelInputUpdate = findViewById(R.id.hike_difficulty_level_text_update);
        descriptionInputUpdate = findViewById(R.id.hike_description_text_update);

        deleteButton = findViewById(R.id.deleteButton);
        updateButton = findViewById(R.id.updateButton);
        backButton = findViewById(R.id.backButton);

        // First we call this
        getData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                name = nameInputUpdate.getText().toString().trim();
                location = locationInputUpdate.getText().toString().trim();
                date = dateInputUpdate.getText().toString().trim();
                parkingAvailable = parkingAvailableInputUpdate.getText().toString().trim();
                length = lengthInputUpdate.getText().toString().trim();
                difficultyLevel = difficultyLevelInputUpdate.getText().toString().trim();
                description = descriptionInputUpdate.getText().toString().trim();
                databaseHelper.updateHikeInformation(id, name, location, date, parkingAvailable, length, difficultyLevel, description);
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
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
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
            difficultyLevel = getIntent().getStringExtra("difficultyLevel");
            description = getIntent().getStringExtra("description");

            // Setting intent data
            nameInputUpdate.setText(name);
            locationInputUpdate.setText(location);
            dateInputUpdate.setText(date);
            parkingAvailableInputUpdate.setText(parkingAvailable);
            lengthInputUpdate.setText(length);
            difficultyLevelInputUpdate.setText(difficultyLevel);
            descriptionInputUpdate.setText(description);
        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
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
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
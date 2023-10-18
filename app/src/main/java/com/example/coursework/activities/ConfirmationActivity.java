package com.example.coursework.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.coursework.R;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Get data passed from AddFragment
        Intent intent = getIntent();
        String hikeName = intent.getStringExtra("HikeName");
        String hikeLocation = intent.getStringExtra("HikeLocation");
        String hikeDate = intent.getStringExtra("HikeDate");
        String hikeParking = intent.getStringExtra("HikeParking");
        String hikeLength = intent.getStringExtra("HikeLength");
        String hikeDifficulty = intent.getStringExtra("HikeDifficulty");
        String hikeDescription = intent.getStringExtra("HikeDescription");

        // Display the data in an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hike Confirmation");
        builder.setMessage("Hike Name: " + hikeName +
                "\nHike Location: " + hikeLocation +
                "\nDate of Hike: " + hikeDate +
                "\nParking Available: " + hikeParking +
                "\nLength of Hike: " + hikeLength +
                "\nDifficulty Level: " + hikeDifficulty +
                "\nDescription: " + hikeDescription);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle OK button click
                finish(); // Close the ConfirmationActivity
            }
        });

        builder.show();
    }
}

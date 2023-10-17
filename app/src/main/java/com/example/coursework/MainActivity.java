package com.example.coursework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton, deleteButton;

    DatabaseHelper databaseHelper;
    ArrayList<String> hike_id, hike_name, hike_location, hike_date, hike_parking_available, hike_length, hike_difficulty_level, hike_description;
    HikerAdapter hikerAdapter;

    ImageView emptyImageView;
    TextView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        emptyImageView = findViewById(R.id.empty_imageView);
        noData = findViewById(R.id.no_data_textview);
        deleteButton = findViewById(R.id.deleteButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        databaseHelper = new DatabaseHelper(MainActivity.this);
        hike_id = new ArrayList<>();
        hike_name = new ArrayList<>();
        hike_location = new ArrayList<>();
        hike_date = new ArrayList<>();
        hike_parking_available = new ArrayList<>();
        hike_length = new ArrayList<>();
        hike_difficulty_level = new ArrayList<>();
        hike_description = new ArrayList<>();

        showData();

        hikerAdapter = new HikerAdapter(
                MainActivity.this,
                this,
                hike_id,
                hike_name,
                hike_location,
                hike_date,
                hike_parking_available,
                hike_length,
                hike_difficulty_level,
                hike_description
        );
        recyclerView.setAdapter(hikerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    public void showData(){
        Cursor cursor = databaseHelper.readAllHikeInformation();
        if (cursor.getCount() == 0){
            emptyImageView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        }
        else{
            while (cursor.moveToNext()){
                hike_id.add(cursor.getString(0));
                hike_name.add(cursor.getString(1));
                hike_location.add(cursor.getString(2));
                hike_date.add(cursor.getString(3));
                hike_parking_available.add(cursor.getString(4));
                hike_length.add(cursor.getString(5));
                hike_difficulty_level.add(cursor.getString(6));
                hike_description.add(cursor.getString(7));
            }
            emptyImageView.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }

    public void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all?");
        builder.setMessage("Are you sure you want to delete all?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                databaseHelper.deleteAllHikeInformation();
                Toast.makeText(MainActivity.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                // Refresh activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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
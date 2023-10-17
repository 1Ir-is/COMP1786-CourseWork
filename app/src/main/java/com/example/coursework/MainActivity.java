package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    DatabaseHelper databaseHelper;
    ArrayList<String> hike_id, hike_name, hike_location, hike_date, hike_parking_available, hike_length, hike_difficulty_level, hike_description;
    HikerAdapter hikerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
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

    public void showData(){
        Cursor cursor = databaseHelper.readAllData();
        if (cursor.getCount() == 1){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
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
        }
    }
}
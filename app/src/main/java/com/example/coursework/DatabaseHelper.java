package com.example.coursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "HikerApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_hiker";
    private static final String COLUMN_ID = "person_id";
    private static final String COLUMN_NAME = "hike_name";
    private static final String COLUMN_LOCATION = "hike_location";
    private static final String COLUMN_DATE = "hike_date";
    private static final String COLUMN_PARKING_AVAILABLE = "hike_parking_available";
    private static final String COLUMN_LENGTH = "hike_length";
    private static final String COLUMN_DIFFICULTY_LEVEL = "hike_difficulty_level";
    private static final String COLUMN_DESCRIPTION = "hike_description";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_PARKING_AVAILABLE + " TEXT, " +
                COLUMN_LENGTH + " TEXT, " +
                COLUMN_DIFFICULTY_LEVEL + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNewHike(String name, String location, String date, String parkingAvailable, String length, String difficultyLevel, String description) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_LOCATION, location);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_PARKING_AVAILABLE, parkingAvailable);
        contentValues.put(COLUMN_LENGTH, length);
        contentValues.put(COLUMN_DIFFICULTY_LEVEL, difficultyLevel);
        contentValues.put(COLUMN_DESCRIPTION, description);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Add Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = null;
        if (sqLiteDatabase != null){
            cursor = sqLiteDatabase.rawQuery(query,null);
        }
        return cursor;
    }
}

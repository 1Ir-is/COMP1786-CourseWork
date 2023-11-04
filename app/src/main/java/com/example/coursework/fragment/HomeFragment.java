package com.example.coursework.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursework.R;
import com.example.coursework.activities.MainActivity;
import com.example.coursework.activities.UpdateActivity;
import com.example.coursework.adapter.HikerAdapter;
import com.example.coursework.database.DatabaseHelper;

import com.example.coursework.databinding.FragmentAddBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private FragmentAddBinding binding;
    RecyclerView recyclerView;
    FloatingActionButton deleteAllButton;
    DatabaseHelper databaseHelper;
    ArrayList<String> hike_id, hike_name, hike_location, hike_date, hike_parking_available, hike_length, hike_weather_forecast, hike_time_estimated, hike_difficulty_level, hike_description;
    HikerAdapter hikerAdapter;
    ImageView emptyImageView;
    TextView noData;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_home layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        emptyImageView = view.findViewById(R.id.empty_imageView);
        noData = view.findViewById(R.id.no_data_textview);
        deleteAllButton = view.findViewById(R.id.deleteAllButton);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialogDeleteAll();
            }
        });

        // Initialize the database helper
        databaseHelper = new DatabaseHelper(requireContext());

        // Initialize ArrayLists to store data
        hike_id = new ArrayList<>();
        hike_name = new ArrayList<>();
        hike_location = new ArrayList<>();
        hike_date = new ArrayList<>();
        hike_parking_available = new ArrayList<>();
        hike_length = new ArrayList<>();
        hike_weather_forecast = new ArrayList<>();
        hike_time_estimated = new ArrayList<>();
        hike_difficulty_level = new ArrayList<>();
        hike_description = new ArrayList<>();

        // Call a method to load data from the database (showData method)
        showData();

        // Initialize the adapter with the data
        hikerAdapter = new HikerAdapter(
            requireActivity(),  // Pass the activity
            requireContext(),  // Pass the context
            hike_id,
            hike_name,
            hike_location,
            hike_date,
            hike_parking_available,
            hike_length,
            hike_weather_forecast,
            hike_time_estimated,
            hike_difficulty_level,
            hike_description
        );

        // Set the adapter for your RecyclerView
        recyclerView.setAdapter(hikerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

    public void showData() {
        Cursor cursor = databaseHelper.readAllHikeInformation();
        if (cursor.getCount() == 0) {
            emptyImageView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                hike_id.add(cursor.getString(0));
                hike_name.add(cursor.getString(1));
                hike_location.add(cursor.getString(2));
                hike_date.add(cursor.getString(3));
                hike_parking_available.add(cursor.getString(4));
                hike_length.add(cursor.getString(5));
                hike_weather_forecast.add(cursor.getString(6));
                hike_time_estimated.add(cursor.getString(7));
                hike_difficulty_level.add(cursor.getString(8));
                hike_description.add(cursor.getString(9));
            }
            emptyImageView.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }

    public void confirmDialogDeleteAll(){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
                databaseHelper.deleteAllHikeInformation();

                // Return to HomeFragment when delete all successfully
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, homeFragment);
                fragmentTransaction.commit();
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
package com.example.coursework.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coursework.R;
import com.example.coursework.activities.MainActivity;
import com.example.coursework.activities.UpdateActivity;
import com.example.coursework.adapter.HikerAdapter;
import com.example.coursework.database.DatabaseHelper;
import com.example.coursework.databinding.FragmentAddBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    ArrayList<String> hike_id, hike_name, hike_location, hike_date, hike_parking_available, hike_length, hike_difficulty_level, hike_description;
    private DatabaseHelper databaseHelper;

    private FragmentAddBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        EditText searchEditText = view.findViewById(R.id.searchEditText);
        Button searchButton = view.findViewById(R.id.searchButton);
        RecyclerView searchResultsRecyclerView = view.findViewById(R.id.searchResultsRecyclerView);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseHelper = new DatabaseHelper(getContext());

        hike_id = new ArrayList<>();
        hike_name = new ArrayList<>();
        hike_location = new ArrayList<>();
        hike_date = new ArrayList<>();
        hike_parking_available = new ArrayList<>();
        hike_length = new ArrayList<>();
        hike_difficulty_level = new ArrayList<>();
        hike_description = new ArrayList<>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    // Delete the previous data
                    hike_id.clear();
                    hike_name.clear();
                    hike_location.clear();
                    hike_date.clear();
                    hike_parking_available.clear();
                    hike_length.clear();
                    hike_difficulty_level.clear();
                    hike_description.clear();

                    Cursor cursor = databaseHelper.searchHikesByName(query);
                    if (cursor.getCount() == 0) {
                        Toast.makeText(getContext(), "Not found!", Toast.LENGTH_SHORT).show();
                    } else {
                        while (cursor.moveToNext()) {
                            hike_id.add(cursor.getString(0));
                            hike_name.add(cursor.getString(1));
                            hike_location.add(cursor.getString(2));
                            hike_date.add(cursor.getString(3));
                            hike_parking_available.add(cursor.getString(4));
                            hike_length.add(cursor.getString(5));
                            hike_difficulty_level.add(cursor.getString(6));
                            hike_description.add(cursor.getString(7));
                        }

                        HikerAdapter adapter = new HikerAdapter(getActivity(), getContext(), hike_id, hike_name, hike_location, hike_date, hike_parking_available, hike_length, hike_difficulty_level, hike_description);
                        searchResultsRecyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Please type something to search!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

}
package com.example.coursework.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursework.R;
import com.example.coursework.activities.ConfirmationActivity;
import com.example.coursework.databinding.FragmentAddBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    private FragmentAddBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        binding = FragmentAddBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        final EditText hikeNameEditText = view.findViewById(R.id.hike_name_text);
        final EditText hikeLocationEditText = view.findViewById(R.id.hike_location_text);
        final EditText hikeDateEditText = view.findViewById(R.id.hike_date_text);
        final EditText hikeParkingEditText = view.findViewById(R.id.hike_parking_available_text);
        final EditText hikeLengthEditText = view.findViewById(R.id.hike_length_text);
        final EditText hikeDifficultyEditText = view.findViewById(R.id.hike_difficulty_level_text);
        final EditText hikeDescriptionEditText = view.findViewById(R.id.hike_description_text);

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data entered by the user
                String hikeName = hikeNameEditText.getText().toString();
                String hikeLocation = hikeLocationEditText.getText().toString();
                String hikeDate = hikeDateEditText.getText().toString();
                String hikeParking = hikeParkingEditText.getText().toString();
                String hikeLength = hikeLengthEditText.getText().toString();
                String hikeDifficulty = hikeDifficultyEditText.getText().toString();
                String hikeDescription = hikeDescriptionEditText.getText().toString();

                // Create an AlertDialog to confirm data entry
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Confirm Data Entry");
                builder.setMessage("Are you sure you want to add this information?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User confirmed, proceed to the new activity
                        Intent intent = new Intent(getActivity(), ConfirmationActivity.class);
                        intent.putExtra("HikeName", hikeName);
                        intent.putExtra("HikeLocation", hikeLocation);
                        intent.putExtra("HikeDate", hikeDate);
                        intent.putExtra("HikeParking", hikeParking);
                        intent.putExtra("HikeLength", hikeLength);
                        intent.putExtra("HikeDifficulty", hikeDifficulty);
                        intent.putExtra("HikeDescription", hikeDescription);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User canceled, do nothing
                    }
                });
                builder.show();
            }
        });

        return view;
    }
}
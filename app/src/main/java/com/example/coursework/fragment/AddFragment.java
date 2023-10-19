package com.example.coursework.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.coursework.R;
import com.example.coursework.activities.ConfirmationActivity;
import com.example.coursework.databinding.FragmentAddBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the fragment_home layout
        binding = FragmentAddBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Find the TextView by their IDs
        TextView nameHikeLabel = view.findViewById(R.id.nameHike);
        TextView locationHikeLabel = view.findViewById(R.id.nameLocation);
        TextView dateHikeLabel = view.findViewById(R.id.dateHike);
        TextView parkingAvailableHikeLabel = view.findViewById(R.id.parkingAvailableHike);
        TextView lengthHikeLabel = view.findViewById(R.id.lengthHike);
        TextView difficultyLevelHikeLabel = view.findViewById(R.id.levelHike);
        TextView descriptionHikeLabel = view.findViewById(R.id.descriptionHike);

        // Create a red asterisk (*)
        String redAsterisk = " *";

        // Create a SpannableString with the label text and the red asterisk
        SpannableString nameHikeSpan = new SpannableString(nameHikeLabel.getText() + redAsterisk);
        SpannableString locationHikeSpan = new SpannableString(locationHikeLabel.getText() + redAsterisk);
        SpannableString dateHikeSpan = new SpannableString(dateHikeLabel.getText() + redAsterisk);
        SpannableString parkingAvailableHikeSpan = new SpannableString(parkingAvailableHikeLabel.getText() + redAsterisk);
        SpannableString lengthHikeSpan = new SpannableString(lengthHikeLabel.getText() + redAsterisk);
        SpannableString difficultyLevelHikeSpan = new SpannableString(difficultyLevelHikeLabel.getText() + redAsterisk);

        // Set the text color of the asterisk to red
        int redColor = Color.RED;
        nameHikeSpan.setSpan(new ForegroundColorSpan(redColor), nameHikeSpan.length() - 1, nameHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        locationHikeSpan.setSpan(new ForegroundColorSpan(redColor), locationHikeSpan.length() - 1, locationHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        dateHikeSpan.setSpan(new ForegroundColorSpan(redColor), dateHikeSpan.length() - 1, dateHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        parkingAvailableHikeSpan.setSpan(new ForegroundColorSpan(redColor), parkingAvailableHikeSpan.length() - 1, parkingAvailableHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        lengthHikeSpan.setSpan(new ForegroundColorSpan(redColor), lengthHikeSpan.length() - 1, lengthHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        difficultyLevelHikeSpan.setSpan(new ForegroundColorSpan(redColor), difficultyLevelHikeSpan.length() - 1, difficultyLevelHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableStrings as the text for the TextViews
        nameHikeLabel.setText(nameHikeSpan);
        locationHikeLabel.setText(locationHikeSpan);
        dateHikeLabel.setText(dateHikeSpan);
        parkingAvailableHikeLabel.setText(parkingAvailableHikeSpan);
        lengthHikeLabel.setText(lengthHikeSpan);
        difficultyLevelHikeLabel.setText(difficultyLevelHikeSpan);

        // Find the EditText by their IDs
        final EditText hikeNameEditText = view.findViewById(R.id.hike_name_text);
        final EditText hikeLocationEditText = view.findViewById(R.id.hike_location_text);
        final EditText hikeDateEditText = view.findViewById(R.id.hike_date_text);
        final RadioGroup radioGroupParking = view.findViewById(R.id.radioGroupParking);
        final EditText hikeLengthEditText = view.findViewById(R.id.hike_length_text);
        final Spinner spinnerDifficulty = view.findViewById(R.id.spinnerDifficulty);
        final EditText hikeDescriptionEditText = view.findViewById(R.id.hike_description_text);

        // Set up the Difficulty Level Spinner
        List<String> difficultyLevels = new ArrayList<>();
        difficultyLevels.add("Easy");
        difficultyLevels.add("Moderate");
        difficultyLevels.add("Difficult");
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, difficultyLevels);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(difficultyAdapter);

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data entered by the user
                String hikeName = hikeNameEditText.getText().toString();
                String hikeLocation = hikeLocationEditText.getText().toString();
                String hikeDate = hikeDateEditText.getText().toString();

                // Get the selected parking option
                String hikeParking = "";
                int checkedId = radioGroupParking.getCheckedRadioButtonId();
                if (checkedId == R.id.radioButtonYes) {
                    hikeParking = "Yes";
                } else if (checkedId == R.id.radioButtonNo) {
                    hikeParking = "No";
                } else {
                    // Handle the case where no radio button is selected or other error handling
                    hikeParking = ""; // Assign a default value or handle it accordingly
                }

                String hikeLength = hikeLengthEditText.getText().toString();

                // Get the selected difficulty level from the Spinner
                String hikeDifficulty = spinnerDifficulty.getSelectedItem().toString();

                String hikeDescription = hikeDescriptionEditText.getText().toString();

                // Create an AlertDialog to confirm data entry
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Confirm Information!");
                builder.setMessage("Are you sure you want to add this information?");
                String finalHikeParking = hikeParking;
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User confirmed, proceed to the new activity
                        Intent intent = new Intent(getActivity(), ConfirmationActivity.class);
                        intent.putExtra("HikeName", hikeName);
                        intent.putExtra("HikeLocation", hikeLocation);
                        intent.putExtra("HikeDate", hikeDate);
                        intent.putExtra("HikeParking", finalHikeParking);
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

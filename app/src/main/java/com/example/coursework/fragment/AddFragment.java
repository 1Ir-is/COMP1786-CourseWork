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

                        // Validation checks
                        boolean isNameOfHikeValid = isNameOfHikeValid(hikeName);
                        boolean isDateOfHikeValid = isDateOfHikeValid(hikeDate);

                        if (hikeName.isEmpty() || hikeLocation.isEmpty() || hikeDate.isEmpty() || hikeLength.isEmpty() || hikeDifficulty.isEmpty()){
                            showValidationError("All fields marked with * are required!");
                        }
                        else if (!isDateOfHikeValid) {
                            showValidationError("Invalid date format. Please input again, use dd/mm/yyyy format!");
                        }
                        else if (!isNameOfHikeValid) {
                            showValidationError("Invalid name. Please enter again!");
                        }
                        else if (hikeDescription.isEmpty()) {
                            showValidationError("Please fill in all fields!");
                        }
                        else {
                            // Create an intent to start the ConfirmationActivity
                            Intent intent = new Intent(getActivity(), ConfirmationActivity.class);

                            //Pass user-input data as extras to the intent
                            intent.putExtra("HikeName", hikeName);
                            intent.putExtra("HikeLocation", hikeLocation);
                            intent.putExtra("HikeDate", hikeDate);
                            intent.putExtra("HikeParking", finalHikeParking);
                            intent.putExtra("HikeLength", hikeLength);
                            intent.putExtra("HikeDifficulty", hikeDifficulty);
                            intent.putExtra("HikeDescription", hikeDescription);

                            // Start the ConfirmationActivity
                            startActivity(intent);
                        }
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

    // Helper method to show validation error
    private void showValidationError(String errorMessage){
        new AlertDialog.Builder(getActivity()).setTitle("Missing Information").setMessage(errorMessage).setPositiveButton("OK", null).show();
    }

    // Validation method for name of the hike
    private boolean isNameOfHikeValid(String name){
        return name.matches("[a-zA-Z ]+");
    }

    // Validation method for date in "dd/mm/yyyy" format
    private boolean isDateOfHikeValid(String date){
        // Define a regular expression pattern for a valid date in "dd/mm/yyyy" format
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d\\d$";

        // Check if the input matches the pattern
        if (date.matches(regex)){
            // Split the date into day, month and year
            String[] dateParts = date.split("/");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // Check if the year is a leap year (for February validation)
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

            // Validate day, month, and year
            if (month >= 1 && month <= 12) {
                if (month == 2) {
                    if (isLeapYear && (day >= 1 && day <= 29)) {
                        // Valid leap year date
                        return true;
                    } else {
                        // Valid non-leap year date
                        return !isLeapYear && (day >= 1 && day <= 28);
                    }
                } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day >= 1 && day <= 30)) {
                    // Valid 30-day month date
                    return true;
                } else {
                    // Valid 31-day month date
                    return (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (day >= 1 && day <= 31);
                }
            }
        }
        // Invalid date of hike
        return false;
    }
}

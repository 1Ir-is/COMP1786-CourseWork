package com.example.coursework.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.coursework.R;
import com.example.coursework.activities.ConfirmationActivity;
import com.example.coursework.databinding.FragmentAddBinding;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        TextView weatherForecastLabel = view.findViewById(R.id.weatherHike);
        TextView estimatedTimeLabel = view.findViewById(R.id.estimatedTimeHike);

        // Create a red asterisk (*)
        String redAsterisk = " *";

        // Create a SpannableString with the label text and the red asterisk
        SpannableString nameHikeSpan = new SpannableString(nameHikeLabel.getText() + redAsterisk);
        SpannableString locationHikeSpan = new SpannableString(locationHikeLabel.getText() + redAsterisk);
        SpannableString dateHikeSpan = new SpannableString(dateHikeLabel.getText() + redAsterisk);
        SpannableString parkingAvailableHikeSpan = new SpannableString(parkingAvailableHikeLabel.getText() + redAsterisk);
        SpannableString lengthHikeSpan = new SpannableString(lengthHikeLabel.getText() + redAsterisk);
        SpannableString difficultyLevelHikeSpan = new SpannableString(difficultyLevelHikeLabel.getText() + redAsterisk);
        SpannableString weatherForecastSpan = new SpannableString(weatherForecastLabel.getText() + redAsterisk);
        SpannableString estimatedTimeSpan = new SpannableString(estimatedTimeLabel.getText() + redAsterisk);

        // Set the text color of the asterisk to red
        int redColor = Color.RED;
        nameHikeSpan.setSpan(new ForegroundColorSpan(redColor), nameHikeSpan.length() - 1, nameHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        locationHikeSpan.setSpan(new ForegroundColorSpan(redColor), locationHikeSpan.length() - 1, locationHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        dateHikeSpan.setSpan(new ForegroundColorSpan(redColor), dateHikeSpan.length() - 1, dateHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        parkingAvailableHikeSpan.setSpan(new ForegroundColorSpan(redColor), parkingAvailableHikeSpan.length() - 1, parkingAvailableHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        lengthHikeSpan.setSpan(new ForegroundColorSpan(redColor), lengthHikeSpan.length() - 1, lengthHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        difficultyLevelHikeSpan.setSpan(new ForegroundColorSpan(redColor), difficultyLevelHikeSpan.length() - 1, difficultyLevelHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        weatherForecastSpan.setSpan(new ForegroundColorSpan(redColor), weatherForecastSpan.length() - 1, weatherForecastSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        estimatedTimeSpan.setSpan(new ForegroundColorSpan(redColor), estimatedTimeSpan.length() - 1, estimatedTimeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableStrings as the text for the TextViews
        nameHikeLabel.setText(nameHikeSpan);
        locationHikeLabel.setText(locationHikeSpan);
        dateHikeLabel.setText(dateHikeSpan);
        parkingAvailableHikeLabel.setText(parkingAvailableHikeSpan);
        lengthHikeLabel.setText(lengthHikeSpan);
        difficultyLevelHikeLabel.setText(difficultyLevelHikeSpan);
        weatherForecastLabel.setText(weatherForecastSpan);
        estimatedTimeLabel.setText(estimatedTimeSpan);

        // Find the EditText by their IDs
        final EditText hikeNameEditText = view.findViewById(R.id.hike_name_text);
        final EditText hikeLocationEditText = view.findViewById(R.id.hike_location_text);
        final Button dateButton = view.findViewById(R.id.hike_date_button);
        final RadioGroup radioGroupParking = view.findViewById(R.id.radioGroupParking);
        final EditText hikeLengthEditText = view.findViewById(R.id.hike_length_text);
        final Spinner spinnerDifficulty = view.findViewById(R.id.spinnerDifficulty);
        final Spinner spinnerWeatherForecast = view.findViewById(R.id.hike_weather_spinner);
        final Button timeButton = view.findViewById(R.id.hike_estimated_time_button);
        final EditText hikeDescriptionEditText = view.findViewById(R.id.hike_description_text);

        // Set up the Difficulty Level Spinner
        List<String> difficultyLevels = new ArrayList<>();

        // Create a List of difficulty level
        difficultyLevels.add("Easy");
        difficultyLevels.add("Moderate");
        difficultyLevels.add("Difficult");

        // Create a adapter to connect to the Spinner
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, difficultyLevels);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner
        spinnerDifficulty.setAdapter(difficultyAdapter);

        // Set up the Weather Spinner
        List<String> weatherForecast = new ArrayList<>();

        // Create a List of weather
        weatherForecast.add("Sunny");
        weatherForecast.add("Cloudy");
        weatherForecast.add("Rainy");
        weatherForecast.add("Snowy");

        // Create a adapter to connect to the Spinner
        ArrayAdapter<String> weatherAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, weatherForecast);
        weatherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner
        spinnerWeatherForecast.setAdapter(weatherAdapter);

        // Set up date button
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateButton.setText(selectedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        // Set up time button
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        timeButton.setText(selectedTime);
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));

                timePickerDialog.show();
            }
        });

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data entered by the user
                String hikeName = hikeNameEditText.getText().toString();
                String hikeLocation = hikeLocationEditText.getText().toString();
                String hikeDescription = hikeDescriptionEditText.getText().toString();

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

                // Get the selected weather forecast from the Spinner
                final String[] hikeWeatherForecast = {spinnerWeatherForecast.getSelectedItem().toString()};

                // Get the date and time
                String hikeDate = dateButton.getText().toString();
                String hikeTimeEstimated = timeButton.getText().toString();

                // Get the selected difficulty level from the Spinner
                final String[] hikeDifficulty = {spinnerDifficulty.getSelectedItem().toString()};

                // Create an AlertDialog to confirm data entry
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Confirm Information!");
                builder.setMessage("Are you sure you want to add this information?");
                String finalHikeParking = hikeParking;
                String finalHikeParking1 = hikeParking;
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Validation checks
                        boolean isNameOfHikeValid = isNameOfHikeValid(hikeName);
                        boolean isLocationOfHikeValid = isNameOfHikeValid(hikeLocation);
                        boolean isLengthOfHikeValid = isLengthOfHikeValid(hikeLength);
                        boolean isParkingValid = finalHikeParking1.equals("Yes") || finalHikeParking1.equals("No");

                        if (
                            hikeName.isEmpty() ||
                            hikeLocation.isEmpty() ||
                            hikeDate.isEmpty() ||
                            hikeLength.isEmpty() ||
                            hikeTimeEstimated.isEmpty() ||
                            !isParkingValid
                        ){
                            showValidationError("All fields marked with * are required!");
                        }
                        else if (!isNameOfHikeValid) {
                            showValidationError("Invalid name. Please enter again!");
                        } else if (!isLocationOfHikeValid) {
                            showValidationError("Invalid location. Please enter again!");
                        } else if (!isLengthOfHikeValid) {
                            showValidationError("Invalid length. Please enter again!");
                        } else {
                            // Create an intent to start the ConfirmationActivity
                            Intent intent = new Intent(getActivity(), ConfirmationActivity.class);

                            //Pass user-input data as extras to the intent
                            intent.putExtra("HikeName", hikeName);
                            intent.putExtra("HikeLocation", hikeLocation);
                            intent.putExtra("HikeDate", hikeDate);
                            intent.putExtra("HikeParking", finalHikeParking);
                            intent.putExtra("HikeLength", hikeLength);
                            intent.putExtra("HikeWeatherForecast", hikeWeatherForecast[0]);
                            intent.putExtra("HikeTimeEstimated", hikeTimeEstimated);
                            intent.putExtra("HikeDifficulty", hikeDifficulty[0]);
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

    // Validation method for location of the hike
    private boolean isLocationOfHikeValid(String name){
        return name.matches("[a-zA-Z ]+");
    }

    // Validation method for location of the hike
    private boolean isLengthOfHikeValid(String length) {
        return length.matches("\\d+\\s?(m|km|M|KM)");
    }

}

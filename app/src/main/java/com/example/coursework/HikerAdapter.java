package com.example.coursework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HikerAdapter extends RecyclerView.Adapter<HikerAdapter.MyViewHolder> {

    Context context;
    ArrayList hike_id, hike_name, hike_location, hike_date, hike_parking_available, hike_length, hike_difficulty_level, hike_description;

    HikerAdapter(
        Context context,
        ArrayList hike_id,
        ArrayList hike_name,
        ArrayList hike_location,
        ArrayList hike_date,
        ArrayList hike_parking_available,
        ArrayList hike_length,
        ArrayList hike_difficulty_level,
        ArrayList hike_description
    ){
        this.context = context;
        this.hike_id = hike_id;
        this.hike_name = hike_name;
        this.hike_location = hike_location;
        this.hike_date = hike_date;
        this.hike_parking_available = hike_parking_available;
        this.hike_length = hike_length;
        this.hike_difficulty_level = hike_difficulty_level;
        this.hike_description = hike_description;

    }


    @NonNull
    @Override
    public HikerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikerAdapter.MyViewHolder holder, int position) {
        holder.hike_id_txt.setText(String.valueOf(hike_id.get(position)));
        holder.hike_name_txt.setText(String.valueOf(hike_name.get(position)));
        holder.hike_location_txt.setText(String.valueOf(hike_location.get(position)));
        holder.hike_date_txt.setText(String.valueOf(hike_date.get(position)));

    }

    @Override
    public int getItemCount() {
        return hike_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hike_id_txt, hike_name_txt, hike_location_txt, hike_date_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hike_id_txt = itemView.findViewById(R.id.hike_id_txt);
            hike_name_txt = itemView.findViewById(R.id.hike_name_txt);
            hike_location_txt = itemView.findViewById(R.id.hike_location_txt);
            hike_date_txt = itemView.findViewById(R.id.hike_date_txt);

        }
    }
}

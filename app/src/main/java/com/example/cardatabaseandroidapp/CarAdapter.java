package com.example.cardatabaseandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardatabaseandroidapp.room.CarDao;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarsViewHolder> {

    private List<Cars> carList;
    private CarDao carDao;
    private Context context;

    // Constructor
    public CarAdapter(List<Cars> carList, CarDao carDao, Context context) {
        this.carList = carList;
        this.carDao = carDao;
        this.context = context;
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new CarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        Cars car = carList.get(position);
        holder.txtName.setText("Name: " + car.getName());
        holder.txtMake.setText("Make: " + car.getMake());
        holder.txtManufacturer.setText("Manufacturer: " + car.getManufacturer());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    // Method to delete an item from the list and the database
    public void deleteItem(int position) {
        // Ensure the position is valid
        if (position < 0 || position >= carList.size()) {
            Toast.makeText(context, "Invalid position", Toast.LENGTH_SHORT).show();
            return;
        }

        Cars car = carList.get(position);

        // Check if the car object is not null
        if (car == null) {
            Toast.makeText(context, "Car is null", Toast.LENGTH_SHORT).show();
            return;
        }

        // Remove the item from the list
        carList.remove(position);
        notifyItemRemoved(position);

        // Show a toast message indicating the car was deleted
        Toast.makeText(context, "Car deleted", Toast.LENGTH_SHORT).show();

        // Remove the item from the database in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    carDao.delete(car);
                } catch (Exception e) {
                    // Log the error for debugging
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // ViewHolder class for the adapter
    public static class CarsViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtMake, txtManufacturer;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMake = itemView.findViewById(R.id.txtMake);
            txtManufacturer = itemView.findViewById(R.id.txtManufacturer);
        }
    }
}

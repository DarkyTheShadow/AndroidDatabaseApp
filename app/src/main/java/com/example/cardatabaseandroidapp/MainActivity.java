package com.example.cardatabaseandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardatabaseandroidapp.room.CarDao;
import com.example.cardatabaseandroidapp.room.DatabaseClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Adapter for managing and binding car data to the RecyclerView
    private CarAdapter CarAdapter;

    // List of cars to display in the RecyclerView
    private List<Cars> CarList;

    // Data Access Object (DAO) for performing database operations related to cars
    private CarDao CarDao;

    // RecyclerView for displaying the list of cars in the UI
    private RecyclerView recyclerView;

    // Button for adding a new car
    private Button add_Car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge layout for full-screen immersive design
        EdgeToEdge.enable(this);

        // Set the content layout from XML
        setContentView(R.layout.activity_main);

        // Ensure the UI handles system bars (status bar, navigation bar) by applying appropriate padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the RecyclerView for car items and set a vertical LinearLayoutManager for it
        recyclerView = findViewById(R.id.RecyclerViewCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load the car data from the database and set up the RecyclerView adapter
        loadCar();

        // Enable swipe-to-delete functionality on the RecyclerView
        // ItemTouchHelper allows swipe gestures for deleting items
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(CarAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Initialize the "Add Car" button from the layout
        add_Car = findViewById(R.id.addCar);

        // Set an OnClickListener for the "Add Car" button to launch AddCarActivity when clicked
        add_Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_Car(); // Method that handles the logic for adding a new car
            }
        });
    }

    // Method to load car data from the database and bind it to the RecyclerView adapter
    private void loadCar() {
        // Get all cars from the database using the DAO
        CarList = DatabaseClient.getInstance(getApplicationContext()).getCarDatabase().CarDao().getAllCars();

        // Initialize the DAO for further operations if needed
        CarDao = DatabaseClient.getInstance(getApplicationContext()).getCarDatabase().CarDao();

        // Create a new adapter with the car list, DAO, and application context, then set it on the RecyclerView
        CarAdapter = new CarAdapter(CarList, CarDao, getApplicationContext());
        recyclerView.setAdapter(CarAdapter); // Attach the adapter to the RecyclerView
    }

    // Method that starts the AddCarActivity to allow the user to add a new car
    private void add_Car() {
        // Create an intent to launch AddCarActivity
        Intent intentAddCar = new Intent(this, AddCarActivity.class);
        startActivity(intentAddCar); // Start the new activity
    }
}

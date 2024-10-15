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

import com.example.cardatabaseandroidapp.room.DatabaseClient;

public class AddCarActivity extends AppCompatActivity {

    private EditText editTextName, editTextMake, editTextManufacturer;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_car);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize EditText and Button views
        editTextName = findViewById(R.id.editTextName);
        editTextMake = findViewById(R.id.editTextMake);
        editTextManufacturer = findViewById(R.id.editTextManufacturer);
        buttonSave = findViewById(R.id.buttonSave);

        // Set onClickListener to the button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCar();  // Call saveCar() when the button is clicked
            }
        });
    }


    private void saveCar() {
        // Get text from EditText views
        String name = editTextName.getText().toString();
        String make = editTextMake.getText().toString();
        String manufacturer = editTextManufacturer.getText().toString();

        // Validate input fields
        if (name.isEmpty() || make.isEmpty() || manufacturer.isEmpty()) {
            Toast.makeText(this, "Please, fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Car object
        Cars car = new Cars(name, make, manufacturer);

        // Insert car into the database
        DatabaseClient.getInstance(getApplicationContext())
                .getCarDatabase().CarDao().insert(car);

        // Show confirmation message
        Toast.makeText(this, "Car added", Toast.LENGTH_SHORT).show();

        // Navigate back to MainActivity
        startActivity(new Intent(this, MainActivity.class));
    }
}

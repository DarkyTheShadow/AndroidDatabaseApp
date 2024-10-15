package com.example.cardatabaseandroidapp.room;
import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {

    // The context used to interact with application resources and classes
    private Context context;

    // Singleton instance of DatabaseClient to ensure only one instance is used throughout the app
    private static DatabaseClient instance;

    // Instance of the CarDatabase (Room database) for accessing the car-related data
    private CarDatabase CarDatabase;

    // Private constructor to prevent direct instantiation from outside
    // Initializes the Room database with the specified configuration
    private DatabaseClient(Context context) {
        this.context = context;

        // Build the Room database named "CarDB" and allow queries on the main thread (not recommended for production)
        CarDatabase = Room.databaseBuilder(context, CarDatabase.class, "CarDB")
                .allowMainThreadQueries() // Allows queries on the main thread (for simplicity in this example)
                .build();
    }

    // Synchronized method to get the single instance of DatabaseClient (singleton pattern)
    // Ensures thread-safe access to the single instance
    public static synchronized DatabaseClient getInstance(Context context) {
        // If the instance is not yet created, create a new one
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        // Return the single instance
        return instance;
    }

    // Method to get the CarDatabase instance for accessing car data
    public CarDatabase getCarDatabase() {
        return CarDatabase;
    }
}

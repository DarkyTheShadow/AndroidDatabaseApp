package com.example.cardatabaseandroidapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cardatabaseandroidapp.Cars;

@Database(entities = {Cars.class}, version = 1) // Defines the database configuration and declares the entities (tables)
public abstract class CarDatabase extends RoomDatabase {

    // Abstract method to get the DAO (Data Access Object) for the car entity
    // This method will be implemented by Room at runtime
    public abstract CarDao CarDao();
}

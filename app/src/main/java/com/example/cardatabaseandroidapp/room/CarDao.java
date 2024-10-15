package com.example.cardatabaseandroidapp.room;
import com.example.cardatabaseandroidapp.Cars;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao // Marks this interface as a Data Access Object (DAO) for Room
public interface CarDao {

    // Insert a car entity into the database
    @Insert
    void insert(Cars cars);

    // Delete a car entity from the database
    @Delete
    void delete(Cars cars);

    // Query to fetch all cars from the database
    // The table name is case-sensitive, so it's updated to match the actual entity (if lowercase)
    @Query("SELECT * FROM Cars")
    List<Cars> getAllCars(); // Returns a list of all car entities
}
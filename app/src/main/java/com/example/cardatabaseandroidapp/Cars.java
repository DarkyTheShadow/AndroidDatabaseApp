package com.example.cardatabaseandroidapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Entity class for the Cars table in the database
@Entity
public class Cars {

    @PrimaryKey(autoGenerate = true) // Primary key with auto-generation
    private int id;
    private String name;
    private String make;
    private String manufacturer;

    // No-argument constructor (required by Room)
    public Cars() {}

    // Parameterized constructor to create a car object
    public Cars(String name, String make, String manufacturer) {
        this.name = name;
        this.make = make;
        this.manufacturer = manufacturer;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id; // Avoid using this unless necessary
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name; // Consider adding validation if necessary
    }

    // Getter for Make
    public String getMake() {
        return make;
    }

    // Setter for Make
    public void setMake(String make) {
        this.make = make; // Consider adding validation if necessary
    }

    // Getter for Manufacturer
    public String getManufacturer() {
        return manufacturer;
    }

    // Setter for Manufacturer
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer; // Consider adding validation if necessary
    }

    // Override toString() for easier debugging
    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", make='" + make + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}

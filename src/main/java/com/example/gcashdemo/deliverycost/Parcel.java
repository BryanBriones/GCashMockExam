package com.example.gcashdemo.deliverycost;

public class Parcel {
    private double weight; // in kg
    private double height; // in cm
    private double width; // in cm
    private double length; // in cm


    public Parcel(double weight, double height, double width, double length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;

    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
    
    public double getWidth() {
        return width;
    }
    public double getLength() {
        return length;
    }
    // Constructor, getters, and setters
}
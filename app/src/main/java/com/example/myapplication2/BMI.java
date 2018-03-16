package com.example.myapplication2;

public abstract class BMI {
    double mass, height;

    public BMI(double _mass, double _height){
        mass = _mass;
        height = _height;
    }

    public abstract double calculateBMI();

    public abstract boolean areDataCorrect() throws IllegalArgumentException;
}
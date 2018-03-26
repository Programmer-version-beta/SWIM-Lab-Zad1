package com.example.myapplication2;

public abstract class BMI {
    double mass, height;
    private static final double MAX_VALUE_OF_OVERWEIGHT = 30;
    private static final double MIN_VALUE_OF_OVERWEIGHT = 25;
    private static final double MAX_VALUE_OF_UNDERWEIGHT = 18.5;
    private static final String RED = "#FF0000";
    private static final String YELLOW = "#FFD700";
    private static final String GREEN = "#32CD32";

    BMI(double mass, double height){
        this.mass = mass;
        this.height = height;
    }

    public abstract double calculateBMI();

    public abstract boolean areDataCorrect() throws IllegalArgumentException;

    String setColor(double bmi){
        String color;

        if(bmi>MAX_VALUE_OF_OVERWEIGHT)
            color = RED;
        else if(bmi>MIN_VALUE_OF_OVERWEIGHT || bmi<MAX_VALUE_OF_UNDERWEIGHT)
            color = YELLOW;
        else
            color = GREEN;

        return color;
    }
}
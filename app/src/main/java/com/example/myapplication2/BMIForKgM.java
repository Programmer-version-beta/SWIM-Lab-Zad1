package com.example.myapplication2;

public class BMIForKgM extends BMI {

    private static final double MIN_VALUE_OF_MASS = 0;
    private static final double MAX_VALUE_OF_MASS = 1000;
    private static final double MIN_VALUE_OF_HEIGHT = 0;
    private static final double MAX_VALUE_OF_HEIGHT = 3;

    BMIForKgM(double mass, double height){
        super(mass, height);
    }

    public double calculateBMI() {
        return mass/(height*height);
    }

    public boolean areDataCorrect() throws IllegalArgumentException{
        if(mass <= MIN_VALUE_OF_MASS || mass > MAX_VALUE_OF_MASS || height <= MIN_VALUE_OF_HEIGHT || height > MAX_VALUE_OF_HEIGHT)
            throw new IllegalArgumentException();
        else
            return true;
    }
}
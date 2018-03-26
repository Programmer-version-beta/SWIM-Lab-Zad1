package com.example.myapplication2;

public class BMIForFtIb extends BMI{

    private static final double MIN_VALUE_OF_MASS = 0;
    private static final double MAX_VALUE_OF_MASS = 4540;
    private static final double MIN_VALUE_OF_HEIGHT = 0;
    private static final double MAX_VALUE_OF_HEIGHT = 9.8425;
    private static final double MULTIPLIER_FOR_FT_LBS = 4.882427948858424;

    BMIForFtIb(double mass, double height) {
        super(mass, height);
    }

    public double calculateBMI() {
        return mass/(height*height)* MULTIPLIER_FOR_FT_LBS;
    }

    public boolean areDataCorrect() throws IllegalArgumentException{
        if(mass <= MIN_VALUE_OF_MASS || mass > MAX_VALUE_OF_MASS || height <= MIN_VALUE_OF_HEIGHT || height > MAX_VALUE_OF_HEIGHT)
            throw new IllegalArgumentException();
        else
            return true;
    }
}
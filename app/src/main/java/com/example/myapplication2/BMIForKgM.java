package com.example.myapplication2;

public class BMIForKgM extends BMI {

    public BMIForKgM(double _mass, double _height){
        super(_mass,_height);
    }

    public double calculateBMI() {
        return mass/(height*height);
    }

    public boolean areDataCorrect() throws IllegalArgumentException{
        if(mass<=0 || mass>1000 || height<=0 || height>3)
            throw new IllegalArgumentException();
        else
            return true;
    }
}
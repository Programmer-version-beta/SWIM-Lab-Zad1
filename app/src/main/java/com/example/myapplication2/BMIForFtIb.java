package com.example.myapplication2;

public class BMIForFtIb extends BMI{

    public BMIForFtIb(double _mass, double _height) {
        super(_mass,_height);
    }

    public double calculateBMI() {
        return mass/(height*height)*4.882427948858424;
    }

    public boolean areDataCorrect() throws IllegalArgumentException{
        if(mass<=0 || mass>4540 || height<=0 || height>9.8425)
            throw new IllegalArgumentException();
        else
            return true;
    }
}
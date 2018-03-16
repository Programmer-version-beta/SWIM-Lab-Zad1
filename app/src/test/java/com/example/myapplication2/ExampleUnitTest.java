package com.example.myapplication2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void for_valid_data_bmi_return_value_kg_m() throws Exception {
        BMI ob = new BMIForKgM(50,1.7);
        double bmi = ob.calculateBMI();
        assertEquals(17.301, bmi,0.001);
    }

    @Test
    public void for_valid_data_bmi_return_value_lbs_ft() throws Exception {
        BMI ob = new BMIForFtIb(220,6.56);
        double bmi = ob.calculateBMI();
        assertEquals(24.960, bmi,0.001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void for_big_values_bmi_throw_exception(){
        BMI ob = new BMIForKgM(2000,4);
        ob.areDataCorrect();
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_negative_values_bmi_throw_exception(){
        BMI ob = new BMIForKgM(-5,-23);
        ob.areDataCorrect();
    }
}
package caloriescalc.model;

import caloriescalc.util.Rounder;

public class BmiCalc {

    public double bmiCalculation(double weightKg, double heightCm){
        return Rounder.roundOff(weightKg/(heightCm/100*heightCm/100));
    }
}

package caloriescalc.util;

public class BmiCalc {

    public static double bmiCalculation(double weightKg, double heightCm){
        return Rounder.roundOff(weightKg/(heightCm/100*heightCm/100));
    }
}

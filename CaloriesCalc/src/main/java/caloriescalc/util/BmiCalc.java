package caloriescalc.util;

/**
 * Class to calculate BMI number
 */
public class BmiCalc {

    /**
     * Calculates BMI by the given parametres
     *
     * @param weightKg a person's weight in Kg
     * @param heightCm a person's height in Cm
     * @return the calculated BMI number
     */
    public static double bmiCalculation(double weightKg, double heightCm){
        return Rounder.roundOff(weightKg/(heightCm/100*heightCm/100));
    }
}

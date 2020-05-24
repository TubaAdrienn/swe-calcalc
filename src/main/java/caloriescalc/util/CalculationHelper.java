package caloriescalc.util;

/**
 * Class to help calculations.
 */
public class CalculationHelper {

    /**
     * Rounds off given parameter.
     *
     * @param toRound number to round off
     * @return the rounded number
     */
    public static double roundOff(double toRound){
        return Math.round(toRound * 100.0)/100.0;
    }

    /**
     * Calculates BMI by the given parametres.
     *
     * @param weightKg a person's weight in Kg
     * @param heightCm a person's height in Cm
     * @return the calculated BMI number
     */
    public static double bmiCalculation(double weightKg, double heightCm){
        return roundOff(weightKg/(heightCm/100*heightCm/100));
    }
}

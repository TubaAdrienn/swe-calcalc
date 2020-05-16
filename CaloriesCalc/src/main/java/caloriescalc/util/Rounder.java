package caloriescalc.util;

/**
 * Class to round off numbers
 */
public class Rounder {

    /**
     * Rounds off given parameter
     *
     * @param toRound number to round off
     * @return the rounded number
     */
    public static double roundOff(double toRound){
        return Math.round(toRound * 100.0)/100.0;
    }

}

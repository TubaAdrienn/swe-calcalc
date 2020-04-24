package caloriescalc.util;

public class Rounder {

    public static double roundOff(double toRound){
        return Math.round(toRound * 100.0)/100.0;
    }

}

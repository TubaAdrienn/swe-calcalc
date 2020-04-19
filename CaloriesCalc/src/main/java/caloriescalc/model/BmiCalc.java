package caloriescalc.model;

public class BmiCalc {

    private double roundOff(double toRound){
        return Math.round(toRound * 100.0)/100.0;
    }

    public double bmiCalculation(double weightKg, double heightCm){
        return roundOff(weightKg/(heightCm/100*heightCm/100));
    }
}

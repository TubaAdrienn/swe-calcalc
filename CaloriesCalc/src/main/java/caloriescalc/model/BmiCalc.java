package caloriescalc.model;

public class BmiCalc {

    private int weightKg;
    private int heightCm;

    private double roundOff(double toRound){
        return Math.round(toRound * 100.0)/100.0;
    }

    public double bmiCalculation(){
        return roundOff(this.weightKg/(this.heightCm/100*this.heightCm/100));
    }
}

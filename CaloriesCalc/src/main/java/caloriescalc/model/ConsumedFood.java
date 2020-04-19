package caloriescalc.model;

import java.util.Date;

public class ConsumedFood {

    private Date date;
    private double allCalories;
    private double allFat;
    private double allCarbo;
    private double allProtein;

    public ConsumedFood(Date date, double allCalories, double allFat, double allCarbo, double allProtein) {
        this.date = date;
        this.allCalories = allCalories;
        this.allFat = allFat;
        this.allCarbo = allCarbo;
        this.allProtein = allProtein;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAllCalories() {
        return allCalories;
    }

    public void setAllCalories(double allCalories) {
        this.allCalories = allCalories;
    }

    public double getAllFat() {
        return allFat;
    }

    public void setAllFat(double allFat) {
        this.allFat = allFat;
    }

    public double getAllCarbo() {
        return allCarbo;
    }

    public void setAllCarbo(double allCarbo) {
        this.allCarbo = allCarbo;
    }

    public double getAllProtein() {
        return allProtein;
    }

    public void setAllProtein(double allProtein) {
        this.allProtein = allProtein;
    }
}

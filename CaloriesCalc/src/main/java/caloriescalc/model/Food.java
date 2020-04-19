package caloriescalc.model;

public class Food {

    private String name;
    private double calories;
    private double fat;
    private double carbo;
    private double protein;

    public Food(String name, double carolies, double fat, double carbo, double protein) {
        this.name = name;
        this.calories = carolies;
        this.fat = fat;
        this.carbo = carbo;
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbo() {
        return carbo;
    }

    public double getProtein() {
        return protein;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCarbo(double carbo) {
        this.carbo = carbo;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }
}

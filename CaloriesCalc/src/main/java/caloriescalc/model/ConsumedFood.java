package caloriescalc.model;

public class ConsumedFood {

    private String name;
    private double carolies;
    private double fat;
    private double carbo;
    private double protein;

    public ConsumedFood(String name, double carolies, double fat, double carbo, double protein) {
        this.name = name;
        this.carolies = carolies;
        this.fat = fat;
        this.carbo = carbo;
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public double getConsumedCarolies(double consumedGrams) {
        return this.carolies*consumedGrams;
    }

    public double getConsumedFat(double consumedGrams) {
        return this.fat*consumedGrams;
    }

    public double getConsumedCarboCarbo(double consumedGrams) {
        return carbo*consumedGrams;
    }

    public double getProtein(double consumedGrams) {
        return protein*consumedGrams;
    }
}

package caloriescalc.model;

import lombok.Data;

@Data
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

}

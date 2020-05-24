package caloriescalc.model;

import caloriescalc.util.CalculationHelper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Class representing 100 grams of a food item.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "calories", "fat", "carbo", "protein"})
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {

    /**
     * Name of the food.
     */
    private String name;

    /**
     * Calorie content of the food in 100g/kcal.
     */
    private double calories;

    /**
     * Fat content of the food in grams.
     */
    private double fat;

    /**
     * Carbohydrate of the food in grams.
     */
    private double carbo;

    /**
     * Protein of the food in grams.
     */
    private double protein;

    /**
     * Calculates the calorie intake by the given amount of consumption.
     *
     * @param grams the amount of consumption
     * @return the calculated calorie intake
     */
    public double getCalPortion(double grams){
        return CalculationHelper.roundOff((grams/100)*this.calories);
    }

    /**
     * Calculates the fat intake by the given amount of consumption.
     *
     * @param grams the amount of consumption
     * @return the calculated fat intake
     */
    public double getFatPortion(double grams){
        return CalculationHelper.roundOff((grams/100)*this.fat);
    }

    /**
     * Calculates the consumed calories by the given amount of consumption.
     *
     * @param grams the amount of consumption
     * @return the calculated carbohydrate intake
     */
    public double getCarboPortion(double grams){
        return CalculationHelper.roundOff((grams/100)*this.carbo);
    }

    /**
     * Calculates the consumed calories by the given amount of consumption.
     *
     * @param grams the amount of consumption
     * @return the calculated protein intake
     */
    public double getProteinPortion(double grams){
        return CalculationHelper.roundOff((grams/100)*this.protein);
    }

    public String getName(){
        return this.name;
    }

}

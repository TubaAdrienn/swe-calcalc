package caloriescalc.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "calories", "fat", "carbo", "protein"})
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    private String name;
    private double calories;
    private double fat;
    private double carbo;
    private double protein;


    public double getCalPortion(double grams){
        return (grams/100)*this.calories;
    }

    public double getFatPortion(double grams){
        return (grams/100)*this.fat;
    }

    public double getCarboPortion(double grams){
        return (grams/100)*this.carbo;
    }

    public double getProteinPortion(double grams){
        return (grams/100)*this.protein;
    }

    public String getName(){
        return this.name;
    }
}

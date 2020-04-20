package caloriescalc.model;

import caloriescalc.dao.DateAdapter;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "calories", "fat", "carbo", "protein","date"})
@Data
public class ConsumedFood{

    private String name;
    private double calories;
    private double fat;
    private double carbo;
    private double protein;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate date;

    public ConsumedFood(){}

    public ConsumedFood(String name, double calories, double fat, double carbo, double protein, LocalDate date) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbo = carbo;
        this.protein = protein;
        this.date = date;
    }

    public String getName() {
        return name;
    }
}

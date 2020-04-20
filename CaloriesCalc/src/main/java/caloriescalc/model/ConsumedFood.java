package caloriescalc.model;

import caloriescalc.dao.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "date", "allCalories", "allFat", "allCarbo", "allProtein"})
public class ConsumedFood {

    private String name;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate date;
    private double allCalories;
    private double allFat;
    private double allCarbo;
    private double allProtein;

    public ConsumedFood(String name, LocalDate date, double allCalories, double allFat, double allCarbo, double allProtein) {
        this.name=name;
        this.date = date;
        this.allCalories = allCalories;
        this.allFat = allFat;
        this.allCarbo = allCarbo;
        this.allProtein = allProtein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

package caloriescalc.model;

import caloriescalc.util.CalculationHelper;
import caloriescalc.util.DateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Class representing user of {@link Journal}
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"date", "username", "cal", "carb", "fat", "prot", "bmi"})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserData {

    /**
     * Date of the logging
     */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate date;

    /**
     * Name of the user
     */
    private String username;

    /**
     * Calorie intake of the user
     */
    private double cal;

    /**
     * Carbohydrate intake of the user
     */
    private double carb;

    /**
     * Fat intake of the user
     */
    private double fat;

    /**
     * Protein intake of the user
     */
    private double prot;

    /**
     * BMI number of the user
     */
    private String bmi;

    public UserData(String username){
        this.date=LocalDate.now();
        this.username=username;
    }

    /**
     * Sets all the double values of the item to zero
     */
    public void zeroValues(){
        this.cal=0;
        this.carb=0;
        this.fat=0;
        this.prot=0;
    }

    /**
     * Checks if all of the double values are zero
     * @return the result of the boolean expression
     */
    public boolean isEverythingZero(){
        if(this.cal+this.carb+this.fat+this.prot==0){
            return true;
        }
        return false;
    }

    /**
     * Adds a rounded off value to the {@link UserData}'s cal attribute
     * @param value value to be added
     */
    public void addToCal(double value){
        this.cal=(CalculationHelper.roundOff(this.cal+=value));
    }

    /**
     * Adds a rounded off value to the {@link UserData}'s carb attribute
     * @param value value to be added
     */
    public void addToCarb(double value){
        this.carb=(CalculationHelper.roundOff(this.carb+=value));
    }

    /**
     * Adds a rounded off value to the {@link UserData}'s fat attribute
     * @param value value to be added
     */
    public void addToFat(double value){
        this.fat=(CalculationHelper.roundOff(this.fat+=value));
    }

    /**
     * Adds a rounded off value to the {@link UserData}'s protein attribute
     * @param value value to be added
     */
    public void addToProt(double value){
        this.prot=(CalculationHelper.roundOff(this.prot+=value));
    }

}

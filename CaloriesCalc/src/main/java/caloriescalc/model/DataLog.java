package caloriescalc.model;

import caloriescalc.util.DateAdapter;
import caloriescalc.util.Rounder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"date", "username", "cal", "carb", "fat", "prot"})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataLog {

    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate date=LocalDate.now();
    private String username;
    private double cal;
    private double carb;
    private double fat;
    private double prot;

    public DataLog(String username){
        this.username=username;
    }

    public void zeroValues(){
        this.cal=0;
        this.carb=0;
        this.fat=0;
        this.prot=0;
    }

    public boolean isEverythingZero(){
        if(this.cal+this.carb+this.fat+this.prot==0){
            return true;
        }
        return false;
    }

    public void addToCal(double value){
        this.cal=(Rounder.roundOff(this.cal+=value));
    }

    public void addToCarb(double value){
        this.carb=(Rounder.roundOff(this.carb+=value));
    }

    public void addToFat(double value){
        this.fat=(Rounder.roundOff(this.fat+=value));
    }

    public void addToProt(double value){
        this.prot=(Rounder.roundOff(this.prot+=value));
    }

}

package caloriescalc.model;

import caloriescalc.util.DateAdapter;
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
    private LocalDate date;
    private String username;
    private double cal;
    private double carb;
    private double fat;
    private double prot;

    public DataLog(String username, LocalDate date){
        this.username=username;
        this.cal=0;
        this.carb=0;
        this.fat=0;
        this.prot=0;
        this.date=date;
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
        this.cal+=value;
    }

    public void addToCarb(double value){
        this.carb+=value;
    }

    public void addToFat(double value){
        this.fat+=value;
    }

    public void addToProt(double value){
        this.prot+=value;
    }

}

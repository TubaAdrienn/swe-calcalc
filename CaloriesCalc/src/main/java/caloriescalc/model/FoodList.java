package caloriescalc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tinylog.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Optional;


@XmlRootElement(name="food")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodList {

    private List<Food> data;

    public Food getFoodItemByName(String nameOfFood){
        Optional<Food> consumedFood=data.stream().filter(name->name.getName()==nameOfFood).findAny();
        Logger.debug((consumedFood.get()));
        return consumedFood.get();
    }

}

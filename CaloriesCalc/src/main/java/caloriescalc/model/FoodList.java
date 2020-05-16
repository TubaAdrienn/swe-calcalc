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

/**
 * Class representing list of {@link FoodItem}
 */
@XmlRootElement(name="food")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodList {

    /**
     * List of {@link FoodItem}
     */
    private List<FoodItem> data;

    /**
     * Searches the list by given name
     *
     * @param nameOfFood name to be searched
     * @return the found {@link FoodItem} item
     */
    public FoodItem getFoodItemByName(String nameOfFood){
        Optional<FoodItem> consumedFood=data.stream().filter(name->name.getName()==nameOfFood).findAny();
        Logger.debug((consumedFood.get()));
        return consumedFood.get();
    }

}

package caloriescalc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tinylog.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.NoSuchElementException;

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

    public void setData(List<FoodItem> data) {
        this.data = data;
    }

    /**
     * Searches the list by given name
     *
     * @param nameOfFood name to be searched
     * @return the found {@link FoodItem} item
     */
    public FoodItem getFoodItemByName(String nameOfFood) throws NoSuchElementException{
            FoodItem consumedFood = data.stream().filter(name -> name.getName() == nameOfFood).findAny().get();
            Logger.debug((consumedFood));
            return consumedFood;
    }

}

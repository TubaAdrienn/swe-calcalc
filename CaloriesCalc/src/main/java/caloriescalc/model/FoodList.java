package caloriescalc.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Optional;

@XmlRootElement(name="food")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class FoodList {

    private List<ConsumedFood> data;

    public FoodList(List<ConsumedFood> data) {
        this.data = data;
    }

    public FoodList(){}

    public ConsumedFood getFoodItemByName(String nameOfFood){
        Optional<ConsumedFood> consumedFood=data.stream().filter(name->name.getName()==nameOfFood).findAny();
        System.out.println(consumedFood.get());
        return consumedFood.get();
    }

    public List<ConsumedFood> getData() {
        return data;
    }
}

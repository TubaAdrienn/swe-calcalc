package caloriescalc.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="food")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class FoodList {

    private List<ConsumedFood> data;

    public FoodList(List<ConsumedFood> data) {
        this.data = data;
    }

    public void setData(List<ConsumedFood> data) {
        this.data = data;
    }

    public List<ConsumedFood> getData() {
        return data;
    }

    public FoodList(){}
}

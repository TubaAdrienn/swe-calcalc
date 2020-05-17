package caloriescalc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * Class representing a journal of nutritional intake
 */
@XmlRootElement(name="logs")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Journal {

    /**
     * List of {@link UserData}
     */
    private List<UserData> data;

}
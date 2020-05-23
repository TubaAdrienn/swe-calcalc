package caloriescalc.dao;

import caloriescalc.model.FoodList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Class to save and load files from XML using JAXB.
 */
public class DatabaseXML {

    /**
     * Deserializes a FoodList object from XML.
     *
     * @return the resulting object
     * @throws Exception if problem occurs during deserialization
     */
    public static FoodList loadFood() throws Exception{
        try {
            JAXBContext context = JAXBContext.newInstance(FoodList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (FoodList) unmarshaller.unmarshal(DatabaseXML.class.getResourceAsStream("/data/food.xml"));
        } catch(Exception e) {
            throw e;
        }
    }
}

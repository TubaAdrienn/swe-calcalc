package caloriescalc.dao;

import caloriescalc.model.FoodList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Database {

    public void saveLog(FoodList foodlog) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(foodlog.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(foodlog, new FileOutputStream("consumedfood.xml"));
        } catch(JAXBException e) {
            throw e;
        }
    }

    public <T> T loadFood(Class<T> clazz, File file) throws JAXBException, IOException {
        try {
            FileInputStream is=new FileInputStream(file);
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(is);
        } catch(JAXBException e) {
            throw e;
        }
    }


}

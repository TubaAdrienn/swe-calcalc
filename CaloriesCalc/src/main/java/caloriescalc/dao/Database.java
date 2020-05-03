package caloriescalc.dao;

import caloriescalc.model.FoodList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

public class Database {

    public static void saveLog(FoodList foodlog) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(foodlog.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            URL url = Database.class.getResource("/data/consumedfood.xml");
            System.out.println(url);
            File file = new File(url.toURI());
            System.out.println(file);
            OutputStream outputStream = new FileOutputStream(file);
            marshaller.marshal(foodlog, file);
            marshaller.marshal(foodlog,System.out);
        } catch(JAXBException e) {
            throw e;
        }
    }

    public static <T> T loadFood(Class<T> clazz, String file) throws JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            System.out.println((Database.class.getResource(file)));
            return (T) unmarshaller.unmarshal(Database.class.getResource(file));
        } catch(JAXBException e) {
            throw e;
        }
    }


}

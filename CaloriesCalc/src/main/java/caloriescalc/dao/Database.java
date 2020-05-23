package caloriescalc.dao;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class to save and load files from XML using JAXB
 */
public class Database {


    /**
     * Serializes an object to XML. The output document is written in UTF-8 encoding.
     *
     * @param o is the object to serialize
     * @param path the file path to serialize to
     * @throws Exception if problem occurs during serialization
     */
    public static void saveXML(Object o, String path) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //URL url = Database.class.getResourceAsStream(path);
            //File file = new File(url.toURI());
            File file=new File(String.valueOf(Database.class.getResourceAsStream(path)));
            marshaller.marshal(o, file);
        } catch(JAXBException e) {
            throw e;
        }
    }

    /**
     * Deserializes an object from XML.
     * @param clazz the class of the object
     * @param file the file path to deserialize from
     * @return the resulting object
     * @throws JAXBException if problem occurs during deserialization
     */
    public static <T> T loadXML(Class<T> clazz, String file) throws Exception{
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(Database.class.getResource(file));
        } catch(Exception e) {
            throw e;
        }
    }


}

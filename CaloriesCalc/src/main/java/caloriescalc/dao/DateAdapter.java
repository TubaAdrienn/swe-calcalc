package caloriescalc.dao;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

        public LocalDate unmarshal(String s) throws Exception {
            return LocalDate.parse(s);
        }

        public String marshal(LocalDate date) throws Exception {
            return date.toString();
        }

    }


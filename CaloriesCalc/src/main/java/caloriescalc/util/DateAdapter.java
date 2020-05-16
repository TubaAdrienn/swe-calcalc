package caloriescalc.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Class to format date
 */
public class DateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * Converts the given {@link String} to {@link LocalDate}
     *
     * @param s a string to be converted
     * @return the formatted date
     */
        public LocalDate unmarshal(String s) {
            return LocalDate.parse(s);
        }

    /**
     *Converts the given {@link LocalDate} to {@link String}
     *
     * @param date the date to be converted
     * @return the formatted date
     */
        public String marshal(LocalDate date) {
            return date.toString();
        }

    }


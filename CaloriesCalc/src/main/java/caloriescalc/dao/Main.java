package caloriescalc.dao;

import caloriescalc.model.ConsumedFood;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws Exception{

        ConsumedFood food=
                new ConsumedFood("töltöttkáposzta", LocalDate.now(),
                        2111d, 334d, 234d, 34.04);

        JAXBHelper.toXML(food, System.out);
    }
}

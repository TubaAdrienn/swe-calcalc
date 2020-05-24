package model;

import caloriescalc.model.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class UserDataTest {

    private UserData data1;
    private UserData data2;

    @BeforeEach
    public void setUp(){
        data1=new UserData(10L,LocalDate.now(),"user1", 1003.5, 45.3,67.5,82.3,"20.1");
        data2=new UserData(11L,LocalDate.now(), "user2", 453.3, 24, 143.4, 34, "5");
    }

    @AfterEach
    public void tearDown(){
        data1=null;
        data2=null;
    }

    @Test
    private void testZeroValues(){
        data1.zeroValues();
        assertEquals(0, data1.getCal());
        assertEquals(0, data1.getCarb());
        assertEquals(0, data1.getFat());
        assertEquals(0, data1.getProt());
    }

    @Test
    public void testIsEverythingZero(){
        testZeroValues();
        assertEquals(true, data1.isEverythingZero());
        assertEquals(false, data2.isEverythingZero());
    }

    @Test
    public void testAddToPortions(){
        data1.addToCal(145.68);
        data1.addToCarb(76.45);
        data1.addToFat(23.4);
        data1.addToProt(12.54);
        assertEquals(1149.18, data1.getCal());
        assertEquals(121.75, data1.getCarb());
        assertEquals(90.9, data1.getFat());
        assertEquals(94.84, data1.getProt());
    }


}


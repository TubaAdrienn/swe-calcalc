package util;

import caloriescalc.util.CalculationHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationHelperTest {

    @Test
    public void roundOffTest(){
        double testValue= CalculationHelper.roundOff(3.4535646);
        assertEquals(3.45, testValue);
    }

    @Test
    public void bmiCalculationTest(){
        double testBmi=CalculationHelper.bmiCalculation(50,156);
        assertEquals(20.55,testBmi);
    }
}

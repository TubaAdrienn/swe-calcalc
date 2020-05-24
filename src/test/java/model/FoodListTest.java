package model;

import caloriescalc.model.FoodItem;
import caloriescalc.model.FoodList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FoodListTest {

    private FoodList foodlist;
    private FoodItem item1;
    private FoodItem item2;
    private FoodItem item3;


    @BeforeEach
    public void setUp(){
        item1=new FoodItem("alma", 15, 2.4, 5.7, 3.6);
        item2=new FoodItem("körte", 20, 3.5, 6.7, 2.3);
        item3=new FoodItem("csirke", 120, 2.6, 0, 22.5);

        foodlist=new FoodList();

        List<FoodItem> content=List.of(item1,item2,item3);
        foodlist.setData(content);

    }

    @AfterEach
    public void tearDown(){
        foodlist=null;
        item1=null;
        item2=null;
        item3=null;
    }

    @Test
    public void testGetFoodItemByName(){
        String nameOfItem="alma";
        assertEquals(item1, foodlist.getFoodItemByName(nameOfItem));
    }

    @Test
    public void testGetFoodItemByNameShouldThrowNoSuchElementException(){
        String name="marha";
        NoSuchElementException exception=assertThrows(NoSuchElementException.class, () -> {
            foodlist.getFoodItemByName(name);
        });
    }

    @Test
    public void testPortionsOfFood(){
        double portion=40.3;
        assertEquals(6.05,item1.getCalPortion(portion));
        assertEquals(0.97,item1.getFatPortion(portion));
        assertEquals(2.30,item1.getCarboPortion(portion));
        assertEquals(1.45,item1.getProteinPortion(portion));
    }


}

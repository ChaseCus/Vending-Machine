package com.techelevator.Items;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinksTest {
    @Test
    public void testToString_returns_Cola_1_25() {
        Drinks drinksTest = new Drinks("Cola", BigDecimal.valueOf(1.25));
        Assert.assertEquals("Cola 1.25",drinksTest.toString());
    }

    @Test
    public void getVendingMessage_give_cola_return_Glug_Glug_Yum() {
        Drinks drinksTest = new Drinks("Cola", BigDecimal.valueOf(1.25));
        Assert.assertEquals("Glug Glug, Yum!",drinksTest.getVendingMessage());
    }

}
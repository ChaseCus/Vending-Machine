package com.techelevator.Items;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void getName_pass() {
        Chips chipTest = new Chips("Grain Waves", BigDecimal.valueOf(2.75));
        Assert.assertEquals("Grain Waves", chipTest.getName());

    }

    @Test
    public void getPrice() {
        Candy candyTest = new Candy("Moonpie", BigDecimal.valueOf(1.80));
        Assert.assertEquals(BigDecimal.valueOf(1.8), candyTest.getPrice());
    }
}
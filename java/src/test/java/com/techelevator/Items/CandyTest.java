package com.techelevator.Items;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void testToString_returns_Wonka_Bar_1_25() {
        Candy candyTest = new Candy("Wonka Bar", BigDecimal.valueOf(1.25));
        Assert.assertEquals("Wonka Bar 1.25",candyTest.toString());
    }

    @Test
    public void getVendingMessage_give_candy_return_Munch_Munch_Yum() {
        Candy candyTest = new Candy("Wonka Bar", BigDecimal.valueOf(1.25));
        Assert.assertEquals("Munch Munch, Yum!",candyTest.getVendingMessage());
    }

}
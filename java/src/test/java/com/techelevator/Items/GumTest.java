package com.techelevator.Items;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {
    @Test
    public void testToString_returns_Little_League_Chew_95() {
        Gum gumsTest = new Gum("Little League Chew", BigDecimal.valueOf(.95));
        Assert.assertEquals("Little League Chew 0.95",gumsTest.toString());
    }

    @Test
    public void getVendingMessage_give_Little_League_Chew_return_Chew_Chew_Yum() {
        Gum gumsTest = new Gum("Little League Chew", BigDecimal.valueOf(.95));
        Assert.assertEquals("Chew Chew, Yum!",gumsTest.getVendingMessage());
    }


}
package com.techelevator.Items;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipsTest {
    @Test
    public void testToString_returns_Grain_Waves_2_75() {
        Chips chipsTest = new Chips("Grain Waves", BigDecimal.valueOf(2.75));
        Assert.assertEquals("Grain Waves 2.75",chipsTest.toString());
    }

    @Test
    public void getVendingMessage_give_chips_return_Crunch_Crunch_Yum() {
        Chips chipsTest = new Chips("Grain Waves", BigDecimal.valueOf(2.75));
        Assert.assertEquals("Crunch Crunch, Yum!",chipsTest.getVendingMessage());
    }

}
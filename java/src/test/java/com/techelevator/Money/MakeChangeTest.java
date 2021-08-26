package com.techelevator.Money;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MakeChangeTest { //these will now return coin count instead of "thank you"

    @Test
    public void makeChange_balance_is_1dollar_50cent_return_6_quarters() {
        MakeChange makeChange = new MakeChange();

        assertEquals("6 Quarters returned.",makeChange.makeChange(BigDecimal.valueOf(1.50)));


    }

    @Test
    public void makeChange_balance_is_10dollar_15cent_return_40_quarters_1_dime_1nickel() {
        MakeChange makeChange = new MakeChange();

        assertEquals("40 Quarters 1 Dime 1 Nickel returned.",makeChange.makeChange(BigDecimal.valueOf(10.15)));

    }

}
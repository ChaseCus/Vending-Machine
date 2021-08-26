package com.techelevator.Items;

import java.math.BigDecimal;

public class Drinks extends Item{
    public Drinks(String name, BigDecimal price) {
        super(name, price);

    }

    public String toString() {      // public: whenever slot is entered by user, Drinks() is called -> vales derived from here
        return name + " " + price;  // values for name and item derived from Item (*abstract*)

    }


    public String getVendingMessage() {
        return "Glug Glug, Yum!";
    }
}

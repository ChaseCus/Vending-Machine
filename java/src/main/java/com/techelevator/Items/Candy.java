package com.techelevator.Items;

import java.math.BigDecimal;

public class Candy extends Item { //extends from parent Class Item
    public Candy(String name, BigDecimal price) {
        super(name, price);
    }


    public String toString() {  // public: whenever slot is entered by user, Candy() is called -> vales derived from here
        return name + " " + price;  // values for name and item derived from Item (*abstract*)

    }


    public String getVendingMessage() {
        return "Munch Munch, Yum!";
    }
}

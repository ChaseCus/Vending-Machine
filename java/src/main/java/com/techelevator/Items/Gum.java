package com.techelevator.Items;

import java.math.BigDecimal;

public class Gum extends Item {
    public Gum(String name, BigDecimal price) {
        super(name, price);
    }


    public String toString() {  // public: whenever slot is entered by user, Gum() is called -> vales derived from here
        return name + " " + price;  // values for name and item derived from Item (*abstract*)

    }


    public String getVendingMessage() {
        return "Chew Chew, Yum!";
    }
}

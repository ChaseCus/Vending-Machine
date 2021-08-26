package com.techelevator.Items;

import java.math.BigDecimal;

public class Chips extends Item {           // values derived from Items parent class
    public Chips(String name, BigDecimal price) {
        super(name, price);
    }


    public String toString() {  // public: whenever slot is entered by user, Chip() is called -> vales derived from here
        return name + " " + price;  // values for name and item derived from Item (*abstract*)

    }


    public String getVendingMessage() {
        return "Crunch Crunch, Yum!";
    }  // message based on subclass (type)
}

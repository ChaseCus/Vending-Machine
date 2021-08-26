package com.techelevator.Items;

import java.math.BigDecimal;

public abstract class Item {
    protected String name;                  // protected -> subclasses can still use
    protected BigDecimal price;
    protected String vendingMessage;


    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }


    public abstract String getVendingMessage(); //gets called by child classes to print specific noise base on item type


    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }


}
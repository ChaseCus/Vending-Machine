package com.techelevator.Logging;

import com.techelevator.Money.MakeChange;
import com.techelevator.Items.Item;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class VendingMachine {

    private LinkedHashMap<String, List<Item>> listOfItems = new LinkedHashMap<>();
    private final Logger logger = new Logger();
    private BigDecimal balance = new BigDecimal(0);

    public BigDecimal getBalance() {
        return balance;
    }

                // method called when item purchase being made
    public void buyItem(String slot) {
        List<Item> itemListStockCount = listOfItems.get(slot);
        if (outOfStock(itemListStockCount)) {
            System.out.println("SOLD OUT");     // handling of condition for chosen item's count being zero
        } else {
            Item item = itemListStockCount.get(0);
            BigDecimal itemPrice = item.getPrice();
            if (balance.doubleValue() < itemPrice.doubleValue())  // handling of condition for available funds being less
                System.out.println("Insufficient Funds");         // than cost of chosen item

            else {
                System.out.println("\nVending " + item.getName() + "..." + "\n" + item.getVendingMessage() + "\n");
                BigDecimal startingBalance = balance;

                balance = balance.subtract(itemPrice);  // balance altered based on purchase amount (subtracted)

                logger.logPurchase(slot, item, startingBalance, balance); //Log and Sales Report
                itemListStockCount.remove(0);
            }
        }
    }

    private boolean outOfStock(List<Item> items) {
        return items.isEmpty();
    }

    public void feedMoney(BigDecimal money) {
        balance = balance.add(money);
        System.out.println("Inserted $" + money.toString() + " dollars.");
        logger.logFeed(money, balance);

    }

    public void displayItems() {
        System.out.println();
        System.out.printf("%-5s%-19s%10s%10s%n", "Slot", "Name", "Price", "Count");  // Formatting of currency into USD
        System.out.println("*********************************************************************");
        for (Map.Entry<String, List<Item>> entry : listOfItems.entrySet()) {
            if (!outOfStock(entry.getValue())) {
                String itemSlot = entry.getKey();
                String itemName = entry.getValue().get(0).getName();
                String itemPrice = "$" + entry.getValue().get(0).getPrice().toString();
                String itemCount = String.valueOf(entry.getValue().size());

                System.out.printf("%-5s%-19s%10s%10s%n", itemSlot, itemName, itemPrice, itemCount);
            } else {
                String itemSlot = entry.getKey();
                System.out.printf("%-15s%7s%n", itemSlot, "SOLD OUT"); //Prevents Array Out Of Bounds error
            }
        }
        System.out.println("*********************************************************************");
    }

    public Map<String, List<Item>> getItemsInTheMachine() {
        return listOfItems;
    }


    public void resetBalance() {
        balance = new BigDecimal(0);
    }  // invoked when transactions have completely ended
                                                                    // (change is given)

    public void loadInventory() {
        Importer importer = new Importer();
        listOfItems = importer.mapImport();
    }

    public void returnChange() {
        if (balance.doubleValue() >= 0) {  //no change message if balance is 0 or less
            MakeChange change = new MakeChange();
            BigDecimal changeGiven = getBalance();

            System.out.println("\n" + change.makeChange(getBalance()));

            resetBalance();  // change has been given/transactions ended; balance reset to 0
            logger.logChange(changeGiven, getBalance());   // log entry made
        }
    }
                    // method handling formatting of currency
    public String bigDecimalToDollarValue() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(balance.doubleValue());
    }

}
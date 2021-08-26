package com.techelevator.Money;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import com.techelevator.Logging.*;
public class MakeChange extends VendingMachine{

    private BigDecimal balance;

    private static final Money[] coins = new Money[]{       // values for each type of currency declared in Money array
                new Quarter(), new Dime(), new Nickel()};   // called 'coin' - based on classes for each form of currency

    public String makeChange(BigDecimal money) {
        StringBuilder coinReturnString = new StringBuilder();
        int amount = (int) (money.doubleValue() * 100);  // temporarily ridding ourselves of the decimal for math purposes
        LinkedHashMap<Money, Integer> change = new LinkedHashMap<>();
        this.balance = money;

        for (Money coin : coins) {
            if (amount <= 0) {
                break;
            }
            int count = amount / coin.getValue();
            if (count > 0) {
                amount = amount % (coin.getValue() * count);
                change.put(coin, count);
            }
        }

        for (Money coin : change.keySet()) {
            String isPlural = (change.get(coin) > 1) ? "s " : " ";
            coinReturnString.append(change.get(coin)).append(" ").append(coin.getName()).append(isPlural);

        }
        System.out.println("\n***********************************************");
        System.out.println("\nChange amount: " + money);
        coinReturnString.append("returned.");
        System.out.println(coinReturnString.toString().trim());         //edited to return coin count instead of "Thank you"
        return coinReturnString.toString().trim();

    }

    public BigDecimal getBalance() {
        return balance;
    }
}

package com.techelevator.Logging;

import com.techelevator.Items.Item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {
    private final File outputFile = new File("Log.txt");  // Default log file

                // Handling of log entries for purchases
    public void logPurchase(String slot, Item item, BigDecimal startingBalance, BigDecimal endingBalance) {
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();  // will create new file with outputFile's current value ONLY if
            }                                // a file with the same name doesn't already exist
        } catch (IOException e) {
            e.printStackTrace();
        }

        String event = item.getName() + " " + slot;  // Name and slot location for item added as event
        String entry = buildLogEntryString(event, startingBalance, endingBalance);  // entry calling on method below
                                                                                    // to construct the log entry
        printToFile(entry);
    }
                // Handling of log entries when money is fed into machine
    public void logFeed(BigDecimal amountFed, BigDecimal endingBalance) {
        String event = "FEED MONEY";
        String entry = buildLogEntryString(event, amountFed, endingBalance);

        printToFile(entry);
    }
                // Handling of log entries when change is given
    public void logChange(BigDecimal changeGiven, BigDecimal endingBalance) {
        String event = "GIVE CHANGE";
        String entry = buildLogEntryString(event, changeGiven, endingBalance);

        printToFile(entry);
    }
                // method used for each log entry type above
    private String buildLogEntryString(String event, BigDecimal startingBalance, BigDecimal endingBalance) {
        Date dt = new Date();
        SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a ");
        String date = f.format(dt);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String startingBalanceString = (numberFormat.format(startingBalance.doubleValue()));
        String endingBalanceString = (numberFormat.format(endingBalance.doubleValue()));
                // each type of log entry will include the following
        return String.format("%-5s%-19s%10s%10s%n", date, event, startingBalanceString, endingBalanceString);
    }


    private void printToFile(String entry) {
        try (FileOutputStream f = new FileOutputStream(outputFile, true);
             PrintWriter pw = new PrintWriter(f)) {

            pw.println(entry);   // each entry buffered
            pw.flush();          // flushed to write to log file
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Could not find file");
        }
    }
}
package com.techelevator.Logging;

import com.techelevator.Items.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.LinkedHashMap;

public class Importer { //this is the FileReader
                        //csv file is passed through here to make a HashMap using the list of items

    private final LinkedHashMap<String, List<Item>> listOfItems = new LinkedHashMap<>();

    public String[] parseInputFileByLine(File inputFile) {
        StringBuilder line = new StringBuilder();
        try (Scanner in = new Scanner(inputFile)) {
            while (in.hasNextLine()) {
                line.append(in.nextLine()).append("\n"); //puts new item into next line everytime one is added
            }

        } catch (IOException e) {
            System.out.println("Could not find " + inputFile + " file");
        }
        return line.toString().split("[\n]"); //splits parsed items using \n we added above

    }

    private void createInventoryMap(String[] parsedFile) {
        Arrays.sort(parsedFile);     // Sorts array based on order it's taken in
        for (String line : parsedFile) { //for each loop through the parsed file

            String[] inputFileItems = line.split("[|]");    //uses the pipe character to split and identify the variables
            List<Item> itemList = new ArrayList<>();            //using index [3] (itemType) and sort accordingly
            String slot = inputFileItems[0];
            String itemType = inputFileItems[3];
            String name = inputFileItems[1];
            BigDecimal price = new BigDecimal(inputFileItems[2]);
            if (itemType.equals("Chip")) {
                Item item = new Chips(name, price);
                itemLoader(slot, item);
            } else if (itemType.equals("Candy")) {
                Item item = new Candy(name, price);
                itemLoader(slot, item);
            } else if (itemType.equals("Drink")) {
                Item item = new Drinks(name, price);
                itemLoader(slot, item);
            } else if (itemType.equals("Gum")) {
                Item item = new Gum(name, price);
                itemLoader(slot, item);
            }

        }
    }

    private void itemLoader(String slot, Item item) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            itemList.add(item);
        }
        listOfItems.put(slot, itemList);
    }


    public LinkedHashMap<String, List<Item>> mapImport() {
        String[] parsedFile = parseInputFileByLine(new File("vendingmachine.csv"));
        createInventoryMap(parsedFile);
        return listOfItems;
    }


}

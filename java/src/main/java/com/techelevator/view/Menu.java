package com.techelevator.view;

import com.techelevator.Logging.VendingMachine;
import com.techelevator.Items.Item;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private final PrintWriter out;		// Any output from VendingMachine and VendingMachineCLI streamed here
	private final Scanner in;			// Any input from VendingMachine and VendingMachineCLI streamed here


	public Menu(InputStream input, OutputStream output) {
		out = new PrintWriter(output);
		in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;			// to prevent error being thrown if user hits enter with no option chosen
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;				// User's selection changes behavior based on selection
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;			// to prevent error being thrown if user hits enter with no selection entered
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {  // Will still run if number of options is expanded
				choice = options[selectedOption - 1];  // User selection referencing an index from options
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;  	// If choice is valid, return its value
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	public void purchaseMenu(VendingMachine vendingMachine) {
		String input = in.nextLine().toUpperCase();		// will accept user input regardless of casing
		Map<String, List<Item>> itemsInTheMachine = vendingMachine.getItemsInTheMachine();
		boolean containsKey = itemsInTheMachine.containsKey(input);

		if (containsKey || input.equals("X")) {
			if (containsKey) {
				vendingMachine.buyItem(input);
			}
		} else {
			System.out.println("Not a valid slot.");
		}
	}

	public void feedMoneyMenu(VendingMachine vendingMachine) {
		while (true) {
			System.out.println("*********************************************************************");
			System.out.println("");
			System.out.println("Current Balance: " + vendingMachine.bigDecimalToDollarValue());
			System.out.println("Please insert $1, $2, $5, or $10 dollars");
			System.out.print("or enter 0 to go back to the previous menu: >>>");
			String userInput = in.nextLine();

			if (userInput.matches("\\d+")) {  //Checks to make sure it is an int
				Integer currency = Integer.valueOf(userInput);
				boolean isRealDenomination = (currency == 1 || currency == 2 || currency == 5 || currency == 10);

				if (isRealDenomination) {
					vendingMachine.feedMoney(new BigDecimal(currency));
				} else if (currency == 0) {
					break;
				} else {
					System.out.println("*********************************************************************");
					System.out.println("This machine only accepts $1, $2, $5, or $10 dollar bills.");
					System.out.println("Please insert a valid form of currency");
					System.out.println("*********************************************************************");
				}

			} else {
				System.out.println("*********************************************************************");
				System.out.println("This machine only accepts $1, $2, $5, or $10 dollar bills.");
				System.out.println("Please insert a valid form of currency");
				System.out.println("*********************************************************************");
			}
		}
	}


}
import java.util.Scanner;

public class Main {
	// === FIELD VARIABLES === //
	static Scanner sc;
	private static int defaultSize = closestPrime(15);
	private static int customSize = 0;
	private static HashTable hashTable = new HashTable(setSize());

	// === MAIN METHOD === //
	public static void main(String[] args) {
		Menu();
	}// end method

	/*
	 * The PrintMenuChoices method returns a formatted string for MainMenu. This
	 * formatted string is displayed to the user when they run the code for the
	 * first time. The method is also passed as a parameter to other methods that
	 * use the 'prompt' String.
	 */
	public static String printMenuChoices() {
		String MenuChoicesAsString =
		//@formatter:off
								"\n" + 
								"       {MainMenu}\n"+ 
								"-=======================-\n"+ 
								"| (1) : Search	 \n" +
								"| (2) : Insert \n" + 
								"| (3) : Delete \n" + 
								"| (4) : Display \n" + 
								"| (5) : Exit \n" + 
								"-=======================-\n"+ 
								"UserInput%> ";
				//@formatter:on
		return MenuChoicesAsString;
	}// end method

	/*
	 * The MenuChoices method contains the following operations: Search, Insert,
	 * Delete, Display, and Exit. This method calls the PrintMenuChoices that prints
	 * out the choices for modifying the HashTable that is chosen by the user.
	 * MenuChoices method also handles miss inputs of the user and loops if it
	 * detects one.
	 */
	//@formatter:off
	public static void Menu() {
		System.out.print(printMenuChoices());

		switch (checkUserInputMenu(printMenuChoices(),false)) {
		case 1: {// Search
			System.out.print("\n:: Enter the string to search. \nUserInput%> ");
			System.out.println(hashTable.search(checkUserInput("\n:: Enter the string to search. \nUserInput%> ")));
			break;
		}

		case 2: {// Insert
			System.out.print("\n:: Enter the string to insert. \nUserInput%> ");
			hashTable.insert(checkUserInput("\n:: Enter the string to insert. \nUserInput%> "));
			break;
		}

		case 3: {// Delete
			System.out.print("\n:: Enter the string to delete. \nUserInput%> ");
			hashTable.delete(checkUserInput("\n:: Enter the string to delete. \nUserInput%> "));
			break;
		}
		case 4: {// Display
			hashTable.display();
			break;
		}
		case 5: {// Exit
			System.out.println(":: Exiting now...");
			System.exit(0);
			break;
		}
		default:
			// @formatter:off
			System.out.println("\n" +
					"Warning: Input is not a valid Menu choice. \n" +
					"\nNotice: \033[3mPlease enter only 1 to 5 as input\033[0m \n");
			// @formatter:on
			break;
		}// end method

		Menu();
		//@formatter:on
	}// end method

	/*
	 * The CheckUserInputMenu method scans the user's input and checks if it is an
	 * integer. If the input is an integer, it is stored in the 'value' variable and
	 * returns it. If the input is not an integer, an error message is displayed,
	 * and the user is prompted to enter an integer value. The 'prompt' parameter is
	 * used for different scenarios of printing, and the 'isNotAllowed' parameter is
	 * for the setSize method. If 'isNotAllowed' is passed a 'true', then the
	 * checkUserMenu method would not accept any value that is less than 15.
	 */
	// TLDR - METHOD FOR DEALING WITH INTEGER INPUT
	public static int checkUserInputMenu(String prompt, boolean isNotAllowed) {
		sc = new Scanner(System.in);

		if (sc.hasNextInt()) {
			int value = sc.nextInt();

			if (isNotAllowed && value < defaultSize) {
			// @formatter:off
			System.out.println("\n" +
					"Warning: hashTable Length should NOT be less than " + defaultSize + ". \n" +
					"\nNotice: \033[3mPlease only enter values GREATER than " +  defaultSize + ". \033[0m \n");
			// @formatter:on
				System.out.print(prompt);
				return checkUserInputMenu(prompt, isNotAllowed);
			}
			return value;
		} // end if

		System.out.println(printCustomError("integer"));

		System.out.print(prompt);
		return checkUserInputMenu(prompt, isNotAllowed);
	}// end if

	/*
	 * The CheckUserInput is an overload of CheckUserMenu method, This method scans
	 * the user's input and checks if it is a string. If the input is a string, it
	 * is stored in the 'input' variable and returns it. If the input is not a
	 * string, an error message is displayed, and the user is prompted to enter a
	 * string value. The 'prompt' parameter is used for different scenarios of
	 * printing
	 */
	// TLDR - METHOD FOR DEALING WITH STRING INPUT
	public static String checkUserInput(String prompt) {
		sc = new Scanner(System.in);

		if (!sc.hasNextInt()) {
			String input = sc.nextLine();
			return input;
		} // end if

		System.out.println(printCustomError("string"));

		System.out.print(prompt);
		return checkUserInput(prompt);
	}// end if

	/*
	 * The setSize is a menu method for setting up the initial size of the hashTable
	 * array. It asks the user if they want to change the default size of 15. If the
	 * user enters y/Y, the method will ask the user to input a new size and if the
	 * user enters n/N, the method will return the defaultSize variable and break
	 * Lastly, if the user tries to enter any other input than y/Y or n/N, the
	 * method prints an error and calls itself again until the user gives a correct
	 * input.
	 */
	public static int setSize() {
		sc = new Scanner(System.in);
		System.out.println("Notice: \033[3mThe program must be able to store 15 String values. \n "
				+ "Computing the temporary size by the load factor percentage...\n "
				+ "Getting the nearest prime...\n The hashTable can now hold " + defaultSize + " elements.\033[0m");
		System.out.print("\n:: Would you like to change the default HashTable length? [y/N]: ");
		String input = sc.nextLine().toLowerCase();

		switch (input) {
		case "y": {
			System.out.print("\n:: Resize the table to a length NOT less than " + defaultSize + ". \nUserInput%> ");
			customSize = checkUserInputMenu(
					":: Resize the table to a length NOT less than " + defaultSize + ". \nUserInput%> ", true);
			customSize = (isPrime(customSize) ? customSize : closestPrime(customSize));
			return customSize;
		}
		case "n": {
			return defaultSize;
		}
		case "": {
			return defaultSize;
		}
		default:
			System.out.println(printCustomError("char"));
			setSize();
			break;
		}// end switch

		return customSize;
	}// end method

	/*
	 * reinitialize the number of elements to be divided by the common load factor
	 * which is .70 if the stored number is a prime immediately return the stored
	 * number if the stored is not a prime execute the for loop with the starting
	 * index of stored number then check if the next number is a prime and
	 * continuously traverse until it becomes a prime number
	 */
	public static int closestPrime(int nOfElements) {
		nOfElements /= .70;
		if (isPrime(nOfElements))
			return nOfElements;
		for (int index = nOfElements; true; index++) {
			if (isPrime(index))
				return index;
		} // end for
	}// end method

	/*
	 * Conditionals to validate if an odd number is divisible by 0 If True = return
	 * false since if any number greater than 1 and less than itself is not
	 * considered as prime number number/2 = To lessen the iteration process Incase
	 * the loop terminates it returns true which signify that it's indeed a prime
	 * number
	 */

	public static boolean isPrime(int number) {

		for (int i = 2; i < number / 2; i++) {
			if (number % i == 0)
				return false;
		} // end for
		return true;
	}// end method

	/*
	 * The printCustomError is exclusively used by checkUserInput, and
	 * checkUserInputMenu for printing their errors, but this method can by used by
	 * other methods if needed. This method has a parameter called 'type' for
	 * specify what data is needed to be inputed on a method that calls this.
	 */
	public static String printCustomError(String type) {
		// @formatter:off
		return "\n" +
			"Warning: Input is not a "+ type +" value. \n\n" +
			"Notice: \033[3mPlease only enter a "+ type +" value.\033[0m \n";
		// @formatter:on
	}// end method
}// end method
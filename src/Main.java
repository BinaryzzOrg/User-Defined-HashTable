import java.util.Scanner;

public class Main {
	// === FIELD VARIABLES === //
	private static int defaultSize = 15;
	private static HashTable hashTable = new HashTable(setSize());

	public static void main(String[] args) {
		Menu();
	}// end method

	/*
	 * The PrintMenuChoices method returns a formatted string for MainMenu. This
	 * formatted string is displayed to the user when they run the code for the
	 * first time. The method is also passed as a parameter to other methods that
	 * use the 'prompt' String.
	 */
	public static String PrintMenuChoices() {
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
		System.out.print(PrintMenuChoices());

		switch (CheckUserInputMenu(PrintMenuChoices())) {
		case 1: {// Search
			
			System.out.print(":: Enter the string to search \n UserInput%> ");
			System.out.println(hashTable.search
								(CheckUserInput
									(printCustomError("string"))));
			break;
		}

		case 2: {// Insert
			System.out.print("UserInput%> ");
			hashTable.insert(CheckUserInput
							(printCustomError("string")));
			break;
		}

		case 3: {// Delete
			System.out.print("UserInput%> ");
			//hashTable.delete(CheckUserInput
			//					(printCustomError("string")));
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
					"Notice: \033[3mPlease enter only 1 to 5 as input\033[0m \n");
			// @formatter:on
			break;
		}// end method

		Menu();
		//@formatter:on
	}// end method

	/*
	 * The CheckUserInput method scans the user's input and checks if it is an
	 * integer. If the input is an integer, it is stored in the 'value' variable and
	 * returns it. If the input is not an integer, an error message is displayed,
	 * and the user is prompted to enter an integer value. The 'prompt' parameter is
	 * used for different scenarios of prin if (input == " ") { return defaultSize;
	 * }else if () {
	 * 
	 * } ting needed for certain menus.
	 */
	static Scanner sc;

	// for dealing with nums as input
	public static int CheckUserInputMenu(String prompt) {
		sc = new Scanner(System.in);

		if (sc.hasNextInt()) {
			int value = sc.nextInt();
			return value;
		} // end if

		System.out.println(printCustomError("integer"));

		System.out.print(prompt);
		return CheckUserInputMenu(prompt);
	}// end if

	// for dealing with string as input
	public static String CheckUserInput(String prompt) {
		sc = new Scanner(System.in);

		if (!sc.hasNextInt()) {
			String input = sc.nextLine();
			return input;
		} // end if

		System.out.println(printCustomError("string"));

		System.out.print(prompt);
		return CheckUserInput(prompt);
	}// end if

	public static int setSize() {
		sc = new Scanner(System.in);
		System.out.print(":: Change the default HashTable lengt of 15? [y/N]: ");
		String input = sc.nextLine().toLowerCase();

		switch (input) {
		case "y": {
			System.out.println("UserInput%> ");
			return CheckUserInputMenu(printCustomError("integer"));
		}
		case "n": {
			break;
		}
		default:
			System.out.println(":: invalid input from CheckUserInput");
			setSize();
			break;
		}
		return defaultSize;
	}// end method

	public static String printCustomError(String type) {
		// @formatter:off
		return "\n" +
			"Warning: Input is not a "+ type +" value. \n\n" +
			"Notice: \033[3mPlease only enter "+ type +" value.\033[0m \n";
		// @formatter:on
	}// end method

}// end method
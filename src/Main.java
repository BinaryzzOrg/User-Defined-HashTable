import java.util.Scanner;

public class Main {
	// field variables
	private static HashTable hashTable = new HashTable();

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
	public static void Menu() {
		System.out.print(PrintMenuChoices());

		switch (CheckUserInput(PrintMenuChoices())) {
		case 1: {// Search
			hashTable.search();
			break;
		}

		case 2: {// Insert
			hashTable.insert();
			break;
		}

		case 3: {// Delete
			hashTable.delete();
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
	}// end method

	/*
	 * The CheckUserInput method scans the user's input and checks if it is an
	 * integer. If the input is an integer, it is stored in the 'value' variable and
	 * returns it. If the input is not an integer, an error message is displayed,
	 * and the user is prompted to enter an integer value. The 'prompt' parameter is
	 * used for different scenarios of printing needed for certain menus.
	 */
	public static int CheckUserInput(String prompt) {
		Scanner sc = new Scanner(System.in);

		if (sc.hasNextInt()) {
			int value = sc.nextInt();
			return value;
		} // end if

		// @formatter:off
			System.out.println("\n" +
					"Warning: Input is not a string value. \n\n" +
					"Notice: \033[3mPlease only enter string value\033[0m \n");
			// @formatter:on

		System.out.print(prompt);
		return CheckUserInput(prompt);
	}// end if
}// end method
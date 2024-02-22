public class HashTable {
	// === FIELD VARIABLES === //
	private String[] hashTable;
	private int size;
	private int numberOfElements;

	// === GETTER SETTER === //
	private String[] getHashTable() {
		return hashTable;
	}// end method

	// === CONSTRUCTOR === //
	public HashTable(int size) {
		this.hashTable = new String[size];
		this.size = size;
	}// end constructor

	/*
	 * Accepts user input isTableEmpty = checks if the table is empty if an element
	 * is found return a string value containing the result of the operation if
	 * probing the potential slots and not finding the user inputed value - it
	 * returns NOT FOUND message
	 */

	// === HASHTABLE OPERATIONS === //
	public String search(String input) {
		if (isTableEmpty()) {
			return "\nNotice: \033[3mTable is currently empty\033[0m.";
		} // end if

		int hashKey = hashFunction(input);

		int counter = 0;
		while (true) {

			String tableElement = getHashTable()[hashKey];
			if (input.equals(tableElement)) {
				return " === ELEMENT FOUND === \n";
			} // end if

			++hashKey;
			hashKey %= size;
			counter++;

			if (counter == size) {
				break;
			} // end if
		} // end while

		return " === ELEMENT NOT FOUND === ";
	}// end method

	/*
	 * Inserts a value to the table Gets the hash key computed from a hash function
	 * Resolves collision through the use of linear probing Updates the number of
	 * elements
	 */
	public void insert(String input) {

		if (isTableFull()) {
			System.out.println("\nNotice: \033[3mHashTable is currently full\033[0m.");
			return;
		} // end if

		int hashKey = linearProbing(hashFunction(input));

		getHashTable()[hashKey] = input;
		numberOfElements++;
	}// end method

	/*
	 * Also computes first the hashKey ' Has a sets of conditions ' - isSlotEmpty =
	 * checks if a specific slot is empty, further used to avoid
	 * NullPointerException Error and when checking the element if its equal to the
	 * user input removes the value and displaying the result
	 */
	public void delete(String input) {

		if (isTableEmpty()) {
			System.out.println("\nNotice: \033[3mTable is currently empty\033[0m.");
			return;
		} // end if

		int hashKey = hashFunction(input);
		for (int theIndex = 0; theIndex < hashTable.length; theIndex++) {
			int theProbingIndex = (hashKey + theIndex) % hashTable.length;
			if (!isSlotEmpty(theProbingIndex) && hashTable[theProbingIndex].equals(input)) {
				hashTable[theProbingIndex] = null;
				System.out.println("\nSuccess: " + input + " is deleted.");
				numberOfElements--;
				return;
			} // end if
		} // end for

		System.out.println("\nNotice: \033[3mElement not found.\033[0m");
	}// end method

	/*
	 * Display the table in tabular form conditions to determined whether a value is
	 * a single digit or containing multiple digits format the display based on
	 * returned value of the conditions
	 */
	public void display() {
		System.out.print(setDesign('-', 23, true));
		System.out.println("\tHash Table");

		for (int theIndex = 0; theIndex < hashTable.length; theIndex++) {
			System.out.print(setDesign('-', 23, true));
			if (theIndex < 10)
				System.out.print("|  " + theIndex + "  |");
			else
				System.out.print("| " + theIndex + "  |");

			if (hashTable[theIndex] != null && hashTable[theIndex].length() < 10)
				System.out.print("    " + hashTable[theIndex] + "    ");
			else
				System.out.print("    " + hashTable[theIndex] + " ");

			System.out.println();
		} // end for
		System.out.print(setDesign('-', 23, true));

	}// end method

	/*
	 * Returns the HashKey converts the characters of the user-inputted string value
	 * into their corresponding ASCII values computes them into summation Lastly
	 * compute it by modulo with the table size
	 */
	// === OTHER FUNCTIONS === //
	public int hashFunction(String input) {

		int hashKey = 0;
		char[] charsOfInput = input.toCharArray();

		for (char chars : charsOfInput) {
			int asciiEquivalent = (int) chars;
			hashKey += asciiEquivalent;
		} // end for each

		return hashKey % size;
	}// end method

	/*
	 * Resolves the collisions By adjusting to next slots until it's available 'In
	 * case if the adjustment starts in the middle indices or last it will crawl to
	 * the left side of the table By getting the modulo of the pre-incremented
	 * key-index and the size which result to index 0 = return to start point
	 */
	public int linearProbing(int hashKey) {
		while (getHashTable()[hashKey] != null) {
			++hashKey;
			hashKey %= size;
		} // end while

		return hashKey;
	}// end method

	public String setDesign(char theSymbol, int theNumOfIteration, boolean toHaveANewLine) {
		String concat = "";
		for (int theIndex = 0; theIndex < theNumOfIteration; theIndex++) {
			concat += theSymbol;
		} // end if

		if (toHaveANewLine)
			concat += "\n";
		else
			return "";

		return concat;
	}// end method

	/*
	 * Conditional methods to be used in 4 operations isTableFull = checks if table
	 * is full isSlotEmptyOnValue = check if a certain element inside the table is
	 * vacated or null isTableEmpty = vice versa of isTableFull
	 */
	private boolean isTableFull() {
		return numberOfElements == hashTable.length;
	}// end method

	public boolean isSlotEmpty(int theProbingIndex) {
		return hashTable[theProbingIndex] == null;
	}// end method

	public boolean isTableEmpty() {
		return numberOfElements == 0;
	}// end method
}// end class
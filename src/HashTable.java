
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

	// === HASHTABLE OPERATIONS === //
	public String search(String input) {

		if (isTableEmpty()) {
			return "\nNotice: Table is currently empty.";
		} // end if

		int hashKey = hashFunction(input);
		String tableElement = getHashTable()[hashKey];

		int counter = 0;
		while (tableElement != null) {
			++hashKey;
			hashKey %= size;

			if (input.equals(tableElement)) {
				return input + " === ELEMENT FOUND === ";
			} // end if
			counter++;

			if (counter == numberOfElements) {
				break;
			} // end if
		} // end while

		return " === ELEMENT NOT FOUND === ";
	}// end method

	public void insert(String input) {

		if (numberOfElements == hashTable.length) {
			System.out.println("Notice: HashTable is currently full.");
			return;
		} // end if

		int hashKey = linearProbing(hashFunction(input));

		getHashTable()[hashKey] = input;
		numberOfElements++;
	}// end method

	public void delete() {

	}// end method

	public void display() {
		System.out.println("\n");
		System.out.println("Key	Value ");
		for (int hashKey = 0; hashKey < hashTable.length; hashKey++) {
			if (hashTable[hashKey] == null) {

				System.out.println(hashKey + "	");
			} else {
				System.out.print(hashKey + " " + hashTable[hashKey] + "\n");
			} // end if else
		} // end for
	}// end method

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

	public int linearProbing(int hashKey) {
		while (getHashTable()[hashKey] != null) {
			++hashKey;
			hashKey %= size;
		} // end while
		return hashKey;
	}// end method

	public boolean isTableEmpty() {
		return numberOfElements == 0;
	}// end method
}// end class
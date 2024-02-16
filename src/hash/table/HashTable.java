package hash.table;

public class HashTable {
	
	private String[] hashTable;
	private int size;
	private int numberOfElements;
	
	public HashTable(int size) {
		this.size = size;
		this.hashTable = new String[size];
	}
	
	private String[] getHashTable() {
		return hashTable;
	}
	
	public void Insert(String key) {
		
		if(numberOfElements == hashTable.length) {
			System.out.println("FULL");
			return;
		}
		
		int hashKey = ComputeHashSum(key);
		
		while(getHashTable()[hashKey] != null) {
			++hashKey;
			hashKey %= size;
		}
		
		getHashTable()[hashKey] = key;
		numberOfElements++;
		
	}
	
	public boolean Search(String key) {
		
		int hashKey = ComputeHashSum(key);
		String tableValue = getHashTable()[hashKey];
		int checkElements = 0;
		
		while(tableValue != null) {
			++hashKey;
			hashKey %= size;
			
			if(key.equals(tableValue)) {
				return true;
			}
			checkElements++;
			
			if(checkElements == numberOfElements ) {
				break;
			}
		}
		return false;
	}
	
	public void Delete(String key) {
		
		int hashKey = ComputeHashSum(key);
		String tableValue = getHashTable()[hashKey];
		int checkElements = 0;
		
		if(tableValue == null) {
			
			while(true) {
				++hashKey;
				hashKey %= size;
				tableValue = getHashTable()[hashKey];
				if(key.equals(tableValue)) {
					hashTable[hashKey] = null;
					numberOfElements--;
					return;
				}
				checkElements++;
				
				if(checkElements == numberOfElements) {
					System.out.println("Element not found");
					return;
				}
			}
		} 
		else if (tableValue != null){
			hashTable[hashKey] = null;
			return;
		}
	}
	
	public void Display() {
		for(String element : hashTable) {
			System.out.print(element + " ");
		}
	}
	
	
	private int ComputeHashSum(String key) {
		
		int asciiKeyTotalValue = 0;
		char[] keyCharacters = key.toCharArray();
		
		for(char character : keyCharacters) {
			int asciiEquivalent = (int) character;
			asciiKeyTotalValue += asciiEquivalent;
		}
		
		return asciiKeyTotalValue % size;
	}
	

}

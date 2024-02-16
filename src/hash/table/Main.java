package hash.table;

public class Main {

	public static void main(String[] args) {
		
		HashTable table = new HashTable(15);
		
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Insert("world");
		table.Delete("world");
		table.Delete("world");
		table.Delete("world");
		table.Delete("world");
		table.Display();
		
	}

}

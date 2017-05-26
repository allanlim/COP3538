import java.text.*;

/**
* A representation of a HashTable that utilizes
* a backing Node array to allow for efficient searching.
*
* @author Allan Lim
* @version April 23, 2017
*/
public class HashTable {

	/**
	 * The amount of free spaces that exist in
	 * the backing array of the hash table.
	 */
	private int freeSpaces;
	
	/**
	 * The backing array of the hash table.
	 */
	private final LinkedList[] LINKED_LIST;
	
	/**
	 * A NumberFormat instance to add commands to
	 * state populations when printing to the console.
	 */
	private static final NumberFormat FORMATTER = NumberFormat.getInstance();
	
	/**
	 * The constructor that initializes the fields
	 * of a hash table.
	 */
	public HashTable() {
		LINKED_LIST = new LinkedList[freeSpaces = 101];
	}
	
	/**
	 * Inserts a Node into its correct position
	 * in the hash table.
	 * 
	 * @param state
	 * 		The name of the state.
	 * @param population
	 * 		The population of the state.
	 */
	public void insert(String state, int population) {
		int hash = toUnicodeModulus(state);
		
		if (LINKED_LIST[hash] == null) {
			freeSpaces--;
			
			LINKED_LIST[hash] = new LinkedList(new Node(state, population));
		} else {
			LINKED_LIST[hash].insert(new Node(state, population));
		}
	}

	/**
	 * Searches in the hash table to locate
	 * a specific state by its name.
	 * 
	 * @param state
	 * 		The name of the state.
	 * @return
	 * 		The state's population if it exists
	 * 		in the hash table, otherwise {@code -1}
	 */
	public double find(String state) {
		LinkedList list = LINKED_LIST[toUnicodeModulus(state)];
		
		if (list == null) {
			System.out.println(state + " is not found");
			
			return -1;
		}
		
		for (Node node = list.head; node != null; node = node.nextNode) {
			if (node.stateName.equals(state)) {
				System.out.println(state + " is found with a population of " + FORMATTER.format(node.statePopulation));
				
				return node.statePopulation;
			}
		}
		
		System.out.println(state + " is not found");
		
		return -1;
	}

	/**
	 * Attempts to delete a state from within
	 * the hash table, if it exists.
	 * 
	 * @param state
	 * 		The name of the state.
	 */
	public void delete(String state) {
		int hash = toUnicodeModulus(state);
		
		if (LINKED_LIST[hash] == null) {
			return;
		}
		
		if (LINKED_LIST[hash].remove(state)) {
			freeSpaces++;
			
			LINKED_LIST[hash] = null;
		}
	}

	/**
	 * Prints a formatted representation of
	 * the hash table to the console.
	 */
	public void display() {
		for (int i = 0; i < LINKED_LIST.length; i++) {
			if (LINKED_LIST[i] == null) {
				System.out.println((i + 1) + ". Empty");
				continue;
			}
			
			for (Node node = LINKED_LIST[i].head; node != null; node = node.nextNode) {
				System.out.print((i + 1) + ". ");
				
				node.printNode();
			}
		}
	}

	/**
	 * Prints the amount of free spaces and collisions
	 * inside the hash table.
	 */
	public void printFreeAndCollisions() {
		int collisions = 0;
		
		for (LinkedList list : LINKED_LIST) {
			if (list == null) {
				continue;
			}
			
			/**
			 * If the head of the list is not
			 * equal to the tail of the list,
			 * then multiple elements must
			 * exist within the list, resulting
			 * in a collision.
			 */
			if (list.head != list.tail) {
				collisions++;
			}
		}
		
		System.out.println("Hash table has " + freeSpaces + " empty spaces and " + collisions + " collisions.");
	}
	
	/**
	 * A helper method (hash function) to convert a String
	 * to its respective hash.
	 * 
	 * @param state
	 * 		The name of the state.
	 * @return
	 * 		The hash resulting from the name of the state.
	 */
	private int toUnicodeModulus(String state) {
		int hash = 0;
		
		for (char c : state.toCharArray()) {
			hash += c;
		}
		
		return hash % LINKED_LIST.length;
	}
	
	private class Node {

		String stateName;

		int statePopulation;

		Node nextNode;

		public Node(String state, int population) {
			stateName = state;
			statePopulation = population;
		}

		public void printNode() {
			System.out.printf("%-25s%,10d\n", stateName, statePopulation);
		}
	}
	
	/**
	 * A LinkedList class that will hold
	 * multiple Node objects with the same
	 * hash.
	 * 
	 * @author Allan
	 * @version April 22, 2017
	 */
	private class LinkedList {
		
		/**
		 * The head of this list.
		 */
		Node head;
		
		/**
		 * The tail of this list.
		 */
		Node tail;
		
		/**
		 * The constructor of a LinkedList
		 * that initializes its fields.
		 * 
		 * @param node
		 * 		The initial head and tail of
		 * 		the list.
		 */
		public LinkedList(Node node) {
			this.head = node;
			this.tail = node;
		}
		
		/**
		 * Inserts a Node at the end of
		 * this list.
		 * 
		 * @param node
		 * 		The Node to insert.
		 */
		public void insert(Node node) {
			tail.nextNode = node;
			tail = node;
		}
		
		/**
		 * Removes a Node from the list.
		 * 
		 * @param state
		 * 		The name of the state.
		 * @return
		 * 		{@code true} if this list should be
		 * 		deleted due to removing the
		 * 		last Node, otherwise {@code false}.
		 */
		public boolean remove(String state) {
			/*
			 * Store the previous Node as it will
			 * be used when attempting to remove
			 * a Node from the middle or end of
			 * the list.
			 */
			Node previousNode = head;
			
			/*
			 * If the Node is the last one in the
			 * list, then return true, deleting the
			 * list from the hash table.
			 */
			if (head.stateName.equals(state) && tail.stateName.equals(state)) {
				System.out.println(state + " has been deleted from hash table");
				return true;
			}
			
			/**
			 * If the Node to remove is the head
			 * of this list...
			 */
			if (head.stateName.equals(state)) {
				head = head.nextNode;
				System.out.println(state + " has been deleted from hash table");
				return false;
			}
			
			/**
			 * Iterate through the list and remove
			 * the correct Node according to its
			 * state name.
			 */
			for (Node node = head; node != null; node = node.nextNode) {
				if (node.stateName.equals(state)) {
					previousNode.nextNode = node.nextNode;
					System.out.println(state + " has been deleted from hash table");
					return false;
				}
				
				previousNode = node;
			}
			
			System.out.println(state + " cannot be found within the hash table and thus was NOT deleted");
			return false;
		}
	}

}

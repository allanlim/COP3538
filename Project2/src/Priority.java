import java.text.NumberFormat;
import java.util.Arrays;

/**
* An implementation of a Priority Queue
* that can only hold State objects, backed by
* an array.
*
* @author Allan Lim
* @version February 26, 2017
*/
public class Priority {

	private int size;
	
	private final State[] states;

	public Priority(int capacity) {
		this.states = new State[capacity];
	}

	/**
	* Adds a State object into the
	* priority queue and updates its
	* size.
	*
	* @param state
	* 		A State object that will be
	* 		added to the priority queue.
	*/
	public void insert(State state) {
		if (isFull()) {
			System.out.println("[Priority Queue] This priority queue is currently full!");
			return;
		}
		
		states[size++] = state;
	}
	
	/**
	 * Returns the State object in the priority
	 * queue with the highest priority, and
	 * swaps the last element of the array
	 * into its place so that the elements
	 * remain contiguous.
	 * 
	 * @return
	 * 		The State object with the highest
	 * 		priority.
	 */
	public State remove() {
		if (isEmpty()) {
			System.out.println("[Priority Queue] This priority queue is currently empty!");
			return null;
		}
		
		int maxIndex = 0;
		
		for (int i = 1; i < size; i++) {
			if (states[i].compareTo(states[maxIndex]) < 0) {
				maxIndex = i;
			}
		}
		
		State state = states[maxIndex];
		
		states[maxIndex] = states[size-- - 1];
		
		return state;
	}

	/**
	 * Prints the contents of the priority
	 * queue in a formatted manner.
	 */
	public void printQueue() {
		System.out.println();
		
		System.out.print(String.format("%1$-19s", "State Name:"));
		System.out.print(String.format("%1$-19s", "Capital City:"));
		System.out.print(String.format("%1$-19s", "State Abbr:"));
		System.out.print(String.format("%1$-19s", "State Population:"));
		System.out.print(String.format("%1$-19s", "Region:"));
		System.out.print(String.format("%1$-19s", "US House Seats:"));
		
		System.out.println();
		
		System.out.println(new String(new char[110]).replace("\0", "-"));
		
		Arrays.sort(states);
		
		for (State state : states) {
			if (state == null) {
				continue;
			}
			
			System.out.print(String.format("%1$-19s", state.getName()));
			System.out.print(String.format("%1$-19s", state.getCapitalCity()));
			System.out.print(String.format("%1$-19s", state.getAbbreviation()));
			System.out.print(String.format("%1$-19s", NumberFormat.getInstance().format(state.getPopulation())));
			System.out.print(String.format("%1$-19s", state.getRegion()));
			System.out.print(String.format("%1$-19s", state.getHouseSeats()));
			
			System.out.println();
		}
	}
	
	/**
	 * Determines if the priority queue currently
	 * holds no elements.
	 * 
	 * @return
	 * 		True if the priority queue is empty,
	 * 		otherwise false.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Determines if the priority queue currently
	 * holds the maximum amount of elements.
	 * 
	 * @return
	 * 		True if the priority queue is full,
	 * 		otherwise false.
	 */
	public boolean isFull() {
		return size == states.length;
	}

}

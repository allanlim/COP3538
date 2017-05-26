import java.text.NumberFormat;

/**
* An implementation of a Stack that can
* only hold State objects, backed by
* an array.
*
* @author Allan Lim
* @version February 26, 2017
*/
public class Stack {
	
	private int top;
	
	private final State[] states;
	
	public Stack(int capacity) {
		this.top = -1;
		this.states = new State[capacity];
	}
	
	/**
	* Adds a State object onto the top
	* of the stack and updates the index
	* of the array that currently contains
	* the element on the top.
	*
	* @param state
	* 		A State object that will be
	* 		added to the top of the stack.
	*/
	public void push(State state) {
		if (isFull()) {
			System.out.println("[Stack] This stack is currently full!");
			return;
		}
		
		states[++top] = state;
	}
	
	/**
	 * Returns the State object at the top
	 * of the stack and modifies the index
	 * of the top element.  We don't need
	 * to set the variable at that index to
	 * null, as changing the index of the top
	 * of the stack suffices.
	 * 
	 * @return
	 * 		The State object at the top of the stack.
	 */
	public State pop() {
		if (isEmpty()) {
			System.out.println("[Stack] This stack is currently empty!");
			return null;
		}
		
		return states[top--];
	}
	
	/**
	 * Prints the contents of the stack
	 * in a formatted manner.
	 */
	public void printStack() {
		System.out.println();
		
		System.out.println("Stack Contents:");
		
		System.out.println();
		
		System.out.print(String.format("%1$-19s", "State Name:"));
		System.out.print(String.format("%1$-19s", "Capital City:"));
		System.out.print(String.format("%1$-19s", "State Abbr:"));
		System.out.print(String.format("%1$-19s", "State Population:"));
		System.out.print(String.format("%1$-19s", "Region:"));
		System.out.print(String.format("%1$-19s", "US House Seats:"));

		System.out.println();
		
		System.out.println(new String(new char[110]).replace("\0", "-"));
		
		for (int i = states.length - 1; i >= 0; i--) {
			State state = states[i];
			
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
	 * Determines if the stack currently
	 * holds no elements.
	 * 
	 * @return
	 * 		True if the stack is empty,
	 * 		otherwise false.
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * Determines if the stack currently
	 * holds the maximum amount of elements.
	 * 
	 * @return
	 * 		True if the stack is full,
	 * 		otherwise false.
	 */
	public boolean isFull() {
		return top == states.length;
	}
	
}

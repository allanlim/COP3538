import java.io.*;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.*;

/**
 * COP 3538: Project 1 – Array Searches and Sorts
 * <p>
 * The main class of our program that
 * handles reading the file, printing out
 * a state's information, sorting the
 * information, etc.
 *
 * @author Allan Lim
 * @version February 1, 2017
 */
public class Project1 {

	/**
	 * The array of states that will be loaded
	 * from a file when the program is run.
	 */
	private static State[] states;
	
	/**
	 * An instance of a Scanner to read in
	 * user input from the console.
	 */
	private static final Scanner SCANNER = new Scanner(System.in);
	
	/**
	 * The main method of our program.
	 * 
	 * @param args
	 * 		Arguments entered on the command
	 * 		line (unused).
	 * @throws IOException
	 * 		If an error occurs when reading
	 * 		a file.
	 */
	public static void main(String[] args) throws IOException {
		printWelcomeMessages();
		
		readFile();
		
		while (true) {
			System.out.println();
			
			try {
				switch (printMenu()) {
					case 1:
						printStateReport();
						break;
					case 2:
						sortByStateName();
						break;
					case 3:
						sortByPopulation();
						break;
					case 4:
						sortByRegion();
						break;
					case 5:
						findAndPrintGivenState();
						break;
					case 6:
						quit();
						break;
					default:
						throw new Exception();
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("Please enter a number from 1 to 6!");
			}
		}
	}
	
	/**
	 * Prints the welcome messages to the
	 * console when the program starts.
	 */
	private static void printWelcomeMessages() {
		System.out.println("Project 1");
		System.out.println("Name: Allan Lim");
		System.out.println("Instructor: Xudong Liu");
		System.out.println();
		System.out.println("Array Searches and Sorts");
	}
	
	/**
	 * Reads the file and stores it in the
	 * states array.
	 * 
	 * @throws IOException
	 * 		If an error occurs when reading
	 * 		the file.
	 */
	private static void readFile() throws IOException {
		File file;
		
		while (true) {
			System.out.print("Enter the file name: ");
			
			file = new File(SCANNER.nextLine());
			
			if (!file.exists()) {
				System.out.println();
				System.out.println("That file doesn't seem to exist!");
				System.out.println();
				continue;
			}
			
			break;
		}
		
		List<String> lines = Files.readAllLines(file.toPath());
		
		states = new State[lines.size() - 1];
		
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			
			String[] information = line.split(",");
			
			String name = information[0];
			String capitalCity = information[1];
			String abbreviation = information[2];
			
			int population = Integer.parseInt(information[3]);
			
			String region = information[4];
			
			int houseSeats = Integer.parseInt(information[5]);
			
			State state = new State(name, capitalCity, abbreviation, population, region, houseSeats);
			
			states[i - 1] = state;
		}
		
		System.out.println();
		System.out.println("There were " + states.length + " state records read.");
	}
	
	/**
	 * Prints the menu to the console and
	 * prompts the user to enter an ID.
	 * 
	 * @return
	 * 		The ID that the user selects.
	 */
	private static int printMenu() {
		System.out.println("1. Print A State Report");
		System.out.println("2. Sort By State Name");
		System.out.println("3. Sort By Population");
		System.out.println("4. Sort By Region");
		System.out.println("5. Find And Print A Given State");
		System.out.println("6. Quit");
		
		System.out.print("Enter your choice: ");
		
		return Integer.parseInt(SCANNER.nextLine());
	}
	
	/**
	 * Prints the information for every
	 * state.
	 */
	private static void printStateReport() {
		System.out.println();
		System.out.printf("%-19s %-16s %-11s %-12s %-16s %-12s", "State Name", "Capital City", "State Abbr", "State Population", "Region", "US House Seats");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------");
		
		for (State state : states) {
			System.out.println(state);
		}
	}
	
	/**
	 * Sorts the states array by name.
	 */
	private static void sortByStateName() {
		for (int outer = 0; outer < states.length - 1; outer++) {
			for (int inner = states.length -1; inner > outer; inner--) {
				if (states[inner].compareTo(states[inner - 1]) > 0) {
					State temp = states[inner];
		            
					states[inner] = states[inner - 1];
					states[inner - 1] = temp;
				}
			}
		}
		
		System.out.println();
		System.out.println("States sorted by name.");
	}
	
	/**
	 * Sorts the states array by population.
	 */
	private static void sortByPopulation() {
		for (int outer = 0; outer < states.length - 1; outer++) {
			int lowest = outer;
			
			for (int inner = outer + 1; inner < states.length; inner++) { 
				if (states[inner].getPopulation() > states[lowest].getPopulation()) {
					lowest = inner;
				}
			}
			
			if(lowest != outer) {
				State temp = states[lowest];
				
				states[lowest] = states[outer];
				states[outer] = temp;
			}
		}
		
		System.out.println();
		System.out.println("States sorted by population.");
	}
	
	/**
	 * Sorts the states array by region.
	 */
	private static void sortByRegion() {
		int inner, outer;

		for (outer = 1; outer < states.length; outer++) {
			State temp = states[outer];

			inner = outer;

			while (inner > 0 && states[inner - 1].getRegion().compareTo(temp.getRegion()) > 0) {
				states[inner] = states[inner - 1];

				inner--;
			}

			states[inner] = temp;
		}
		
		System.out.println();
		System.out.println("States sorted by region.");
	}
	
	/**
	 * Prompts the user to enter the name
	 * of a state and display its information,
	 * if it exists.
	 */
	private static void findAndPrintGivenState() {
		System.out.println();
		System.out.print("Enter the state name: ");
		
		String stateName = SCANNER.nextLine();
		
		State state = null;
		
		for (State s : states) {
			if (s.getName().equalsIgnoreCase(stateName)) {
				state = s;
				
				break;
			}
		}
		
		System.out.println();
		
		if (state == null) {
			System.out.println("Error: " + stateName + " not found!");
			return;
		}
		
		System.out.println(String.format("%1$-19s", "State Name:") + state.getName());
		System.out.println(String.format("%1$-19s", "Capital City:") + state.getCapitalCity());
		System.out.println(String.format("%1$-19s", "State Abbr:") + state.getAbbreviation());
		System.out.println(String.format("%1$-19s", "State Population:") + NumberFormat.getInstance().format(state.getPopulation()));
		System.out.println(String.format("%1$-19s", "Region:") + state.getRegion());
		System.out.println(String.format("%1$-19s", "US House Seats:") + state.getHouseSeats());
	}
	
	/**
	 * Quits the program.
	 */
	private static void quit() {
		System.out.println();
		System.out.println("Have a good day!");
		System.exit(0);
	}
	
}

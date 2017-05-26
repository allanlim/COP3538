import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * COP3538: Project 2 – Stacks and Priority Queues
 * <p>
 * The main class of our program that
 * handles reading the file, adding State
 * objects to a Stack or Priority Queue,
 * displaying their information, etc.
 *
 * @author Allan Lim
 * @version February 26, 2017
 */
public class Project2 {
	
	/**
	 * The Stack of states that will be loaded
	 * from a file when the program is run.
	 */
	private static Stack states;
	
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
		
		states.printStack();
		
		List<State> southernStates = new ArrayList<>();
		
		List<State> westernStates = new ArrayList<>();
		
		List<State> midwesternStates = new ArrayList<>();
		
		while (!states.isEmpty()) {
			State state = states.pop();
			
			switch (state.getRegion()) {
				case "South":
					southernStates.add(state);
					break;
				case "West":
					westernStates.add(state);
					break;
				case "Midwest":
					midwesternStates.add(state);
					break;
			}
		}
		
		Priority south = new Priority(southernStates.size());
		
		Priority west = new Priority(westernStates.size());
		
		Priority midwest = new Priority(midwesternStates.size());
		
		for (State state : southernStates) {
			south.insert(state);
		}
		
		for (State state : westernStates) {
			west.insert(state);
		}
		
		for (State state : midwesternStates) {
			midwest.insert(state);
		}
		
		System.out.println();
		
		System.out.println("South Priority Queue Contents:");
		
		south.printQueue();
		
		System.out.println();
		
		System.out.println("West Priority Queue Contents:");
		
		west.printQueue();
		
		System.out.println();
		
		System.out.println("Midwest Priority Queue Contents:");
		
		midwest.printQueue();
		
		while (!south.isEmpty()) {
			states.push(south.remove());
		}
		
		while (!west.isEmpty()) {
			states.push(west.remove());
		}
		
		while (!midwest.isEmpty()) {
			states.push(midwest.remove());
		}
		
		states.printStack();
		
		System.out.println();
		
		System.out.println("Goodbye!");
	}
	
	/**
	 * Prints some default messages
	 * when the program begins.
	 */
	private static void printWelcomeMessages() {
		System.out.println("COP3538 Project 2");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Name: Allan Lim");
		System.out.println();
		System.out.println("Stacks and Priority Queues");
	}
	
	/**
	 * Reads the file and pushes
	 * certain states to the Stack.
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
		
		List<State> stateList = new ArrayList<>();
		
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			
			String[] information = line.split(",");
			
			String name = information[0];
			String capitalCity = information[1];
			String abbreviation = information[2];
			
			int population = Integer.parseInt(information[3]);
			
			String region = information[4];
			
			if (!region.equals("South") && !region.equals("West") && !region.equals("Midwest")) {
				continue;
			}
			
			int houseSeats = Integer.parseInt(information[5]);
			
			State state = new State(name, capitalCity, abbreviation, population, region, houseSeats);
			
			stateList.add(state);
		}
		
		states = new Stack(stateList.size());
		
		for (State state : stateList) {
			states.push(state);
		}
		
		System.out.println();
		System.out.println("There were " + stateList.size() + " state records put on the stack.");
	}
	
}

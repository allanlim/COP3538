import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * COP 3538: Project 5 – Hash Tables
 * <p>
 * The main class of a program that initializes
 * and inserts state names and their respective
 * population into a hash table, after reading
 * them from a .csv file.
 *
 * @author Allan Lim
 * @version April 23, 2017
 */
public class Project5 {
	
	/**
	 * An instance of a HashTable that will be used
	 * to store states names and their respective
	 * population.
	 */
	private static final HashTable HASH_TABLE = new HashTable();
	
	/**
	 * An instance of a Scanner that will be used
	 * for user input when prompted to enter the
	 * name of the .csv file.
	 */
	private static final Scanner SCANNER = new Scanner(System.in);
	
	/**
	 * The main method of our program.
	 * 
	 * @param args
	 * 		Command-line arguments that are unused.
	 * @throws Exception
	 * 		Any exception received from attempting to
	 * 		read the .csv file.
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("COP 3538 Project 5");
		System.out.println("Hash Tables");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Name: Allan Lim");
		System.out.println();
		
		File file = null;
		
		/*
		 * Prompt the user for an existing file and
		 * store it in a variable. 
		 */
		do {
			System.out.print("Enter the file name: ");
			
			file = new File(SCANNER.nextLine());
		} while (!file.exists());
		
		/*
		 * Read the file into a list of Strings.
		 */
		List<String> lines = Files.readAllLines(file.toPath());
		
		/*
		 * Add the entries to the hash table.
		 */
		for (int i = 1; i < lines.size(); i++) {
			String[] information = lines.get(i).split(",");
			
			HASH_TABLE.insert(information[0], Integer.parseInt(information[3]));
		}
		
		System.out.println("\nThere were " + (lines.size() - 1) + " state records read into the hash table.");
	
		System.out.println("\nHash table content:\n");
		
		HASH_TABLE.display();
		
		System.out.println();
		
		HASH_TABLE.delete("Vermont");
		HASH_TABLE.delete("California");
		HASH_TABLE.delete("South Carolina");
		
		System.out.println();
		
		HASH_TABLE.find("Florida");
		HASH_TABLE.find("Rhode Island");
		HASH_TABLE.find("California");
		
		System.out.println();
		
		HASH_TABLE.delete("Kentucky");
		HASH_TABLE.delete("Minnesota");
		HASH_TABLE.delete("West Virginia");
		
		System.out.println();
		
		HASH_TABLE.display();
		
		System.out.println();
		
		HASH_TABLE.printFreeAndCollisions();
	}
	
}

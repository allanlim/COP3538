import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * COP3538: Project 4 – Binary Search Trees
 * <p>
 * Implements a {@link BinarySearchTree}, traversing
 * it in multiple ways, as well as deleting some specific
 * elements and printing the minimum/maximum value of
 * the tree.
 *
 * @author Allan Lim
 * @version April 4, 2017
 */
public class Project4 {

	public static void main(String[] args) throws Exception {
		System.out.println("COP3538 Project 4");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Name: Allan Lim");
		System.out.println("Binary Search Trees");
		System.out.println();

		Scanner scanner = new Scanner(System.in);

		BinarySearchTree bst = new BinarySearchTree();

		File file;

		while (true) {
			System.out.print("Enter the file name: ");

			file = new File(scanner.nextLine());

			if (file.exists()) {
				break;
			}

			System.out.println(file.getName() + " does not exist!");
		}

		scanner.close();

		System.out.println();

		List<String> lines = Files.readAllLines(file.toPath());

		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);

			String[] state = line.split(",");

			bst.insert(state[0], Integer.parseInt(state[3]));
		}

		System.out.println("There were " + (lines.size() - 1) + " state records put on the tree.");
		System.out.println();
		System.out.println("Inorder Traversal:");
		System.out.println();

		printStateHeader();

		bst.printInorder();

		System.out.println();

		bst.delete("California");
		bst.delete("Florida");
		bst.delete("Michigan");

		System.out.println();
		System.out.println("Preorder Traversal:");
		System.out.println();

		printStateHeader();

		bst.printPreorder();

		System.out.println();

		bst.find("Kentucky");
		bst.find("Rhode Island");
		bst.find("Florida");

		bst.delete("Delaware");
		bst.delete("Wyoming");
		bst.delete("West Virginia");
		bst.delete("South Dakota");

		System.out.println();
		System.out.println("Postorder Traversal:");
		System.out.println();

		printStateHeader();

		bst.printPostorder();

		System.out.println();
		System.out.println("State with the minimum population");
		System.out.println();

		printStateHeader();

		bst.printMinimum();

		System.out.println();
		System.out.println("State with the maximum population");
		System.out.println();

		printStateHeader();

		bst.printMaximum();
	}

	private static void printStateHeader() {
		System.out.printf("%-19s%10s\n", "State Name", "State Population");
		System.out.println("-----------------------------------");
	}

}

import java.text.*;

/**
 * Represents a binary search tree that will store {@link State} objects along
 * with their populations.
 *
 * @author Allan Lim
 * @version April 4, 2017
 */
public class BinarySearchTree {

	/**
	 * The root (parent) {@link Node} of this {@link BinarySearchTree}.
	 */
	private Node root;

	/**
	 * A {@link Node} that stores the {@link State}'s information
	 * with the lowest population.
	 */
	private Node minimumPopulationNode;

	/**
	 * A {@link Node} that stores the {@link State}'s information
	 * with the highest population.
	 */
	private Node maximumPopulationNode;

	/**
	 * Creates an instance of an empty {@link BinarySearchTree}.
	 */
	public BinarySearchTree() {

	}

	/**
	 * Creates a new {@link Node} and inserts it into
	 * the correct position in the tree.
	 *
	 * @param state
	 * 		The name of the state.
	 * @param population
	 * 		The population of the state.
	 */
	public void insert(String state, int population) {
		if (root == null) {
			root = new Node(state, population);
			return;
		}

		root = insert(root, new Node(state, population));
	}

	/**
	 * A helper method to recursively insert a
	 * {@link Node} into the tree.
	 *
	 * @param parent
	 * 		The current parent node.
	 * @param node
	 * 		The node to add.
	 * @return
	 *
	 */
	private Node insert(Node parent, Node node) {
		if (parent == null) {
	        return node;
	    }

		if (node.stateName.compareTo(parent.stateName) > 0) {
	        parent.rightChild = insert(parent.rightChild, node);
	    } else if (node.stateName.compareTo(parent.stateName) < 0) {
	        parent.leftChild = insert(parent.leftChild, node);
	    }

	    return parent;
	}

	/**
	 * Searches through the tree for a specific {@link Node}
	 * and returns its population if it exists, otherwise -1.
	 *
	 * @param state
	 * 		The name of the state.
	 * @return
	 * 		A {@link State}'s population if it exists in the
	 * 		tree, otherwise {@code -1}.
	 */
	public int find(String state) {
		int nonLeafNodes = 0;

		if (root == null) {
			return -1;
		}

		Node node = root;

		while (node != null) {
			if (node.leftChild != null || node.rightChild != null) {
				nonLeafNodes++;
			}

			int compare = state.compareTo(node.stateName);

			if (compare < 0) {
				node = node.leftChild;
			} else if (compare > 0) {
				node = node.rightChild;
			} else {
				System.out.println(state + " is found with a population of " + NumberFormat.getInstance().format(node.statePopulation));
				System.out.println(nonLeafNodes + " nodes visited");
				System.out.println();

				return node.statePopulation;
			}
		}

		System.out.println(state + " is not found");
		System.out.println(nonLeafNodes + " nodes visited");
		System.out.println();

		return -1;
	}

	/**
	 * Deletes a {@link Node} if it exists in the tree.
	 *
	 * @param state
	 * 		The name of the state.
	 */
	public void delete(String state) {
		if (root == null) {
			System.out.println("ERROR: root is null");
			return;
		}

		int parentCompare = 0;

		Node node = root, parent = root;

		while (true) {
			int compare = state.compareTo(node.stateName);

			if (compare < 0) {
				parent = node;
				parentCompare = compare;
				node = node.leftChild;
			} else if (compare > 0) {
				parent = node;
				parentCompare = compare;
				node = node.rightChild;
			} else {
				if (node.leftChild == null && node.rightChild == null) {
					if (parentCompare < 0) {
						parent.leftChild = null;
					} else {
						parent.rightChild = null;
					}
				} else if (node.leftChild == null) {
					if (parentCompare < 0) {
						parent.leftChild = node.rightChild;
					} else {
						parent.rightChild = node.rightChild;
					}
				} else if (node.rightChild == null) {
					if (parentCompare < 0) {
						parent.leftChild = node.leftChild;
					} else {
						parent.rightChild = node.leftChild;
					}
				} else {
					Node tempNode = getMinNode(node.rightChild);

					node = tempNode;

					tempNode = null;
				}

				System.out.println(state + " has been deleted from the tree.");
				break;
			}
		}
	}

	/**
	 * A helper method to get the closest {@link Node} that is greater than or
	 * equal to {@code node}.
	 *
	 * @param node
	 *            The Node whose children to search through.
	 * @return The closest child Node of {@code node} that is either greater
	 *         than or equal to itself.
	 */
	private Node getMinNode(Node node) {
		return node.leftChild == null ? node : getMinNode(node.leftChild);
	}

	/**
	 * Iterates through the tree via inorder traversal and prints
	 * each {@link Node}'s information.
	 */
	public void printInorder() {
		if (root == null) {
			return;
		}

		inorder(root);
	}

	/**
	 * A helper method to iterate through the tree via
	 * inorder traversal and print a {@link Node}'s
	 * information.
	 *
	 * @param node
	 * 		The current node in the traversal.
	 */
	private void inorder(Node node) {
		if (node == null) {
			return;
		}

		inorder(node.leftChild);
		node.printNode();
		inorder(node.rightChild);
	}

	/**
	 * Iterates through the tree via preorder traversal and prints
	 * each {@link Node}'s information.
	 */
	public void printPreorder() {
		if (root == null) {
			return;
		}

		preorder(root);
	}

	/**
	 * A helper method to iterate through the tree via
	 * preorder traversal and print a {@link Node}'s
	 * information.
	 *
	 * @param node
	 * 		The current node in the traversal.
	 */
	private void preorder(Node node) {
		if (node == null) {
			return;
		}

		node.printNode();
		preorder(node.leftChild);
	    preorder(node.rightChild);
	}

	/**
	 * Iterates through the tree via postorder traversal and prints
	 * each {@link Node}'s information.
	 */
	public void printPostorder() {
		if (root == null) {
			return;
		}

		postorder(root);
	}

	/**
	 * A helper method to iterate through the tree via
	 * postorder traversal and print a {@link Node}'s
	 * information.
	 *
	 * @param node
	 * 		The current node in the traversal.
	 */
	private void postorder(Node node) {
		if (node == null) {
			return;
		}

		postorder(node.leftChild);
	    postorder(node.rightChild);
		node.printNode();
	}

	/**
	 * Prints the {@link Node}
	 * with the minimum population;
	 */
	public void printMinimum() {
		if (root == null) {
			return;
		}

		if (minimumPopulationNode == null) {
			minimumPopulationNode = root;
		}

		minimum(root);

		minimumPopulationNode.printNode();
	}

	/**
	 * A helper method to recursively traverse through
	 * the tree and assign {@code minimumPopulationNode}
	 * to the {@link Node} with the lowest population.
	 *
	 * @param node
	 * 		The current node in the traversal.
	 */
	private void minimum(Node node) {
		if (node == null) {
			return;
		}

		if (node.statePopulation < minimumPopulationNode.statePopulation) {
			minimumPopulationNode = node;
		}

		minimum(node.leftChild);
		minimum(node.rightChild);
	}

	/**
	 * Prints the {@link Node}
	 * with the maximum population;
	 */
	public void printMaximum() {
		if (root == null) {
			return;
		}

		if (maximumPopulationNode == null) {
			maximumPopulationNode = root;
		}

		maximum(root);

		maximumPopulationNode.printNode();
	}

	/**
	 * A helper method to recursively traverse through
	 * the tree and assign {@code maximumPopulationNode}
	 * to the {@link Node} with the greatest population.
	 *
	 * @param node
	 * 		The current node in the traversal.
	 */
	private void maximum(Node node) {
		if (node == null) {
			return;
		}

		if (node.statePopulation > maximumPopulationNode.statePopulation) {
			maximumPopulationNode = node;
		}

		maximum(node.leftChild);
		maximum(node.rightChild);
	}

	/**
	* Represents a Node object that will
	* be stored within the {@link BinarySearchTree}.
	*
	* @author Allan
	* @version March 29, 2017
	*/
	private class Node {

		/**
		 * The name of the state.
		 */
		String stateName;

		/**
		 * The population of the state.
		 */
		int statePopulation;

		/**
		 * The left child of this node.
		 */
		Node leftChild;

		/**
		 * The right child of this node.
		 */
		Node rightChild;

		/**
		 * Initializes the Node's instance variables.
		 *
		 * @param state
		 * 		The state's name.
		 * @param population
		 * 		The state's population.
		 */
		public Node(String state, int population) {
			stateName = state;
			statePopulation = population;
		}

		/**
		 * Prints the Node's information in a specific format.
		 */
		public void printNode() {
			System.out.printf("%-25s%,10d\n", stateName, statePopulation);
		}

	}

}

package year2019;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.InputReader;

public class December6th {
	
	public void computeTree(List<String> inputs) {
		BinaryTree tree = new BinaryTree();		
		Node root = null;
		
		for (int i = 0; i < inputs.size(); i++) {
			String line = inputs.get(i);
			String parent = line.substring(0, line.indexOf(')'));
			String child = line.substring(line.indexOf(')') + 1);
			root = tree.addRecursive(root, parent, child);			
		}
		
		tree.computeCheckSum(root, 0);
		int i = 0;
	}

	public class Node {
		String key;
		int numbersOfOrbits;
		List<Node> children;

		public Node(String key) {
			this.key = key;
			this.numbersOfOrbits = 0;
			this.children = new ArrayList<>();
		}
	}

	public class BinaryTree {

		Node root;
		
		public BinaryTree() {
			root = null;
		}

		public Node addRecursive(Node current, String parent, String child) {
			if (current == null) { // root node case
				Node parentNode = new Node(parent);
				Node childNode = new Node(child);
				parentNode.children.add(childNode);
				root = parentNode;
				
				return root;
			}
			
			if (current.key.equals(parent)) { //current node = parent node
				Node childNode = new Node(child);
				current.children.add(childNode);
				current = root;

				return root;
			}

			if (current.key.equals(child)) { //current node = child node
				Node parentNode = new Node(parent);
				parentNode.children.add(current);

				return root;
			}

			for (int i = 0; i < current.children.size(); i++) {
				addRecursive(current.children.get(i), parent, child);
			}

			return root;
		}
		
		public void computeCheckSum(Node current, int numberOfOrbits) {
			current.numbersOfOrbits = numberOfOrbits;

			for (int i = 0; i < current.children.size(); i++) {
				computeCheckSum(current.children.get(i), numberOfOrbits+1);
			}
		}
		
		public int computeFullCheckSum(Node current) {
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		December6th obj = new December6th();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December1st.class, "input6.txt");

		obj.computeTree(inputs);
	}
}

package year2019;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import common.InputReader;

public class December6th {
	
	public void computeTree(List<String> inputs) {
		BinaryTree tree = new BinaryTree();		
		Node root = new Node("COM");
		tree.setRoot(root);
		
		for (int i = 0; i < inputs.size(); i++) {
			String line = inputs.get(i);
			String parent = line.substring(0, line.indexOf(')'));
			String child = line.substring(line.indexOf(')') + 1);
			tree.addNode(parent, child);			
		}
		
		tree.computeCheckSum(root, 0);
		int sum = tree.computeFullCheckSum();
		System.out.println("Sum:" + sum);
	}
	
	public void computeTreePart2(List<String> inputs) {
		BinaryTree tree = new BinaryTree();		
		Node root = new Node("COM");
		tree.setRoot(root);
		
		for (int i = 0; i < inputs.size(); i++) {
			String line = inputs.get(i);
			String parent = line.substring(0, line.indexOf(')'));
			String child = line.substring(line.indexOf(')') + 1);
			tree.addNode(parent, child);			
		}
		
		tree.computeCheckSum(root, 0);
		int sum = tree.computeFullCheckSum();
		System.out.println("Sum:" + sum);
	}

	public class Node {
		String key;
		int numbersOfOrbits;
		Set<Node> children;

		public Node(String key) {
			this.key = key;
			this.numbersOfOrbits = 0;
			this.children = new HashSet<>();
		}
		
		public String toString() {
			return "Node " + key;
		}
	}

	public class BinaryTree {

		Node root;
		Map<String, Node> nodes;
		
		public BinaryTree() {
			root = null;
			nodes = new HashMap<>();
		}
		
		public void setRoot(Node root) {
			this.root = root;
			nodes.put(root.key, root);
		}

		public void addNode(String parent, String child) {			
			if (Objects.nonNull(nodes.get(parent))) { //parent node exists
				Node childNode = Objects.isNull(nodes.get(child)) ? new Node(child) : nodes.get(child);
				nodes.get(parent).children.add(childNode);
				nodes.put(child, childNode);
				
				return;
			}

			if (Objects.nonNull(nodes.get(child))) { //child node exists
				Node parentNode = Objects.isNull(nodes.get(parent)) ? new Node(parent) : nodes.get(parent);
				parentNode.children.add(nodes.get(child));
				nodes.put(parent, parentNode);
				
				return;
			}
			
			//if no node exist
			Node newParentNode = new Node(parent);
			Node newChildNode = new Node(child);
			newParentNode.children.add(newChildNode);
			nodes.put(parent, newParentNode);
			nodes.put(child, newChildNode);
		}
		
		public void computeCheckSum(Node current, int numberOfOrbits) {
			current.numbersOfOrbits = numberOfOrbits;

			for (Node child : current.children) {
				computeCheckSum(child, numberOfOrbits+1);
			}
		}
		
		public int computeFullCheckSum() {			
			int sum = 0;
			
			List<Integer> orbits = nodes.entrySet().stream()
			.map(entry -> entry.getValue().numbersOfOrbits)
			.collect(Collectors.toList());
			
			for (int i=0;i<orbits.size();i++) {
				sum += orbits.get(i);
			}
			
			return sum;
		}
	}

	public static void main(String[] args) throws IOException {
		December6th obj = new December6th();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December1st.class, "input6.txt");

		obj.computeTree(inputs);
	}
}

package year2017;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.InputReader;

public class December7th {

	public static final String FIRST_NODE_TEST = "tknk";
	public static final String FIRST_NODE_REAL = "hlhomy";
	public static final String FIRST_NODE = FIRST_NODE_REAL;

	public int firstStar(List<String> inputs) {

		Map<String, Node> nodes = new HashMap<>();

		// Create list of nodes
		for (String line : inputs) {
			String[] splitties = line.split(" ");
			String name = splitties[0];
			String weightAsString = splitties[1];
			int weight = Integer.parseInt(weightAsString.replace("(", "").replace(")", ""));
			nodes.put(name, new Node(name, weight));
		}

		// Add children to each node
		for (String line : inputs) {
			String[] splitties = line.split(" ");
			String name = splitties[0];
			if (splitties.length > 2) { // isParent
				Set<Node> children = new HashSet<>();
				for (int i = 3; i < splitties.length; i++) {
					String child = splitties[i].replace(",", "");
					children.add(nodes.get(child));
				}
				nodes.get(name).setNode(children);
			}
		}

		Node root = nodes.get(FIRST_NODE);
		root.calculateWeight();

		nodes.values().stream().forEach(n -> n.printWeightOfChildrenAndNode());
		// List<Node> unbalancedNodes = nodes.values().stream().filter(n ->
		// n.areChildrenWeightEquals()).toList();

		// unbalancedNode.printWeightOfChildrenAndNode();
		return 0;
	}

	public static void main(String[] args) throws IOException {
		December7th obj = new December7th();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December7th.class, "input7.txt");
		System.out.println("Count 1: " + obj.firstStar(inputs));
	}

	public class Node {

		private String name;
		private int weight;
		private int weightOfChildren = 0;
		private Set<Node> children;

		public Node(String name, int weight) {
			this.name = name;
			this.weight = weight;
			this.weightOfChildren = 0;
			this.children = Set.of();
		}

		public String toString() {
			return "(" + name + "," + weight + ")";
		}

		public String childrenToString() {
			String s = "";
			for (Node child : this.children) {
				s = s + child.name + ", ";
			}

			return s;
		}

		public void setNode(Set<Node> nodes) {
			this.children = nodes;
		}

		public int calculateWeight() {
			if (this.children.isEmpty()) { // calculate weight of parent, with leaf children
				this.weightOfChildren = 0;
				return this.weight;
			} else if (this.weightOfChildren > 0) { // noLeaf, but weightOfChildrenAlreadyThere
				return this.getTotalWeightOfChildrenPyramid();
			} else {
				int currentChildrenWeigth = 0;
				for (Node child : this.children) {
					currentChildrenWeigth += child.calculateWeight();
				}
				this.weightOfChildren = currentChildrenWeigth;
				return currentChildrenWeigth;
			}
		}

		public int getTotalWeightOfChildrenPyramid() {
			int currentChildrenWeight = 0;
			for (Node child : this.children) {
				currentChildrenWeight = currentChildrenWeight + child.weight + child.weightOfChildren;
			}

			return currentChildrenWeight;
		}

		public boolean areChildrenWeightEquals() {
			int weightOfChild = -1;

			for (Node child : this.children) {
				if (weightOfChild != -1) {
					if (child.weight != weightOfChild) {
						return false;
					}
				} else {
					weightOfChild = child.weight;
				}
			}

			return true;
		}

		public void printWeightOfChildrenAndNode() {
			System.out.println("Name: " + this.name + " ");
			for (Node child : children) {				
				System.out.println(child.weight + " + " + child.weightOfChildren + " = "
						+ (child.weight + child.weightOfChildren));
			}
			System.out.println();
		}
	}
}

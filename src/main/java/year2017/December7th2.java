package year2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.InputReader;

public class December7th2 {

	public static final String FIRST_NODE_TEST = "tknk";
	public static final String FIRST_NODE_REAL = "hlhomy";
	public static final String FIRST_NODE = FIRST_NODE_REAL;

	private Map<String, Disc> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		December7th2 obj = new December7th2();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December7th2.class, "input7.txt");
		obj.firstStar(inputs);
	}

	public void firstStar(List<String> inputs) {
		this.createListOfNodes(inputs);
		this.addChildrenToAllNodes(inputs);

		List<Disc> mismatchedWeight = new ArrayList<>();

		for (Disc disc : map.values()) {
			if (disc.getChildren() != null) {
				List<Disc> children = disc.getChildren();
				int weight = children.get(0).totalWeight();
				for (int i = 1; i < children.size(); i++) {
					int newweight = children.get(i).totalWeight();
					if (newweight != weight) {
						mismatchedWeight.add(disc);
						break;
					}
				}
			}
		}

		for (int i = 0; i < mismatchedWeight.size(); i++) {
			Boolean hasChildren = false;
			for (int j = 0; j < mismatchedWeight.size(); j++) {
				if (i != j) {
					if (mismatchedWeight.get(i).hasChild(mismatchedWeight.get(j))) {
						hasChildren = true;
					}
				}
			}
			if (!hasChildren) {
				Disc offBalance = mismatchedWeight.get(i);
				for (Disc disc : offBalance.getChildren()) {
					System.out.println(disc.totalWeight() + ": " + disc);
				}
			}
		}

	}

	public class Disc {
		private String name = "";
		private int weight = 0;
		private List<Disc> children;

		public Disc(String name, int weight) {
			this.name = name;
			this.weight = weight;
		}

		public String getName() {
			return name;
		}

		public int getWeight() {
			return weight;
		}

		public List<Disc> getChildren() {
			return children;
		}

		public void setChildren(List<Disc> children) {
			this.children = children;
		}

		public int totalWeight() {
			int weight = this.getWeight();
			if (this.getChildren() != null) {
				for (Disc child : this.getChildren()) {
					weight += child.totalWeight();
				}
			}
			return weight;
		}

		public Boolean hasChild(Disc disc) {
			Boolean hasChild = false;
			if (this.getChildren() != null) {
				for (Disc child : this.getChildren()) {
					if (disc.getName().equals(child.getName())) {
						hasChild = true;
					}
				}
			}
			return hasChild;
		}

		@Override
		public String toString() {
			String returnString = Integer.toString(this.getWeight());
			if (this.getChildren() != null) {
				for (Disc child : this.getChildren()) {
					returnString += " + ";
					returnString += child.toString();
				}
			}
			return returnString;
		}

	}

	public void createListOfNodes(List<String> inputs) {
		for (String line : inputs) {
			String name = line.substring(0, line.indexOf(" "));
			String weight = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
			Disc disc = new Disc(name, Integer.parseInt(weight));
			map.put(name, disc);
		}
	}

	public void addChildrenToAllNodes(List<String> inputs) {
		// Add children to each node
		for (String s : inputs) {
			if (s.contains("->")) {
				String name = s.substring(0, s.indexOf(" "));
				Disc parent = map.get(name);
				String supportingDiscs = s.substring(s.indexOf("->") + 3);
				String[] supportingDiscsArray = supportingDiscs.split(", ");
				ArrayList<Disc> children = new ArrayList<>();
				for (String supportingDisc : supportingDiscsArray) {
					Disc child = map.get(supportingDisc);
					children.add(child);
				}
				parent.setChildren(children);
			}
		}
	}
}
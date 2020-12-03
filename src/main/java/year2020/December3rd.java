package year2020;

import java.io.IOException;
import java.util.List;

import common.InputReader;

public class December3rd {
	
	public int countTreesPart2(List<String> inputs, int incrementRight, int incrementDown) {
		int treeCounter = 0;
		int positionToCheck = 0;

		for (int i = 0; i < inputs.size(); i+=incrementDown) {
			String input = inputs.get(i);
			char coordinates = input.charAt(positionToCheck % (input.length()));
			System.out.println("Input:" + input + ", " + coordinates + "(" + positionToCheck + "," + i + "," + input.length() + ")");
			if (coordinates == '#') {
				treeCounter++;
			}
			positionToCheck += incrementRight;
		}

		return treeCounter;

	}

	public static void main(String[] args) throws IOException {
		December3rd obj = new December3rd();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December1st.class, "input3.txt");

		int count31 =  obj.countTreesPart2(inputs, 3, 1);
		int count11 =  obj.countTreesPart2(inputs, 1, 1);
		int count51 =  obj.countTreesPart2(inputs, 5, 1);
		int count71 =  obj.countTreesPart2(inputs, 7, 1);
		int count12 =  obj.countTreesPart2(inputs, 1, 2);
		System.out.println("Numbers of Trees: " + count31);
		System.out.println("Numbers of Trees: " + count11);
		System.out.println("Numbers of Trees: " + count51);
		System.out.println("Numbers of Trees: " + count71);
		System.out.println("Numbers of Trees: " + count12);
		System.out.println("Product: " + (count31 * count11 * count51 * count71 * count12));
	}
}

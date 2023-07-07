package year2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.InputReader;

public class December2nd {

	public static final String SPLIT_CHAR = "\t";

	public int firstStar(List<String> inputs) {
		List<Integer> differences = new ArrayList<>();

		for (String input : inputs) {
			List<Integer> integers = new ArrayList<>();
			String[] row = input.split(SPLIT_CHAR);

			for (int i = 0; i < row.length; i++) {
				integers.add(Integer.valueOf(row[i].trim()));
			}

			int min = Collections.min(integers);
			int max = Collections.max(integers);
			differences.add(max - min);
		}

		return differences.stream().mapToInt(i -> i.intValue()).sum();
	}

	public int secondStar(List<String> inputs) {
		List<Integer> differences = new ArrayList<>();

		for (String input : inputs) {
			List<Integer> integers = new ArrayList<>();
			String[] row = input.split(SPLIT_CHAR);

			for (int i = 0; i < row.length; i++) {
				integers.add(Integer.valueOf(row[i].trim()));
			}

			int quotient = getQuotientOfMinAndMax(integers);
			differences.add(quotient);
		}

		return differences.stream().mapToInt(i -> i.intValue()).sum();
	}

	private int getQuotientOfMinAndMax(List<Integer> integers) {
		for (int i = 0; i < integers.size() - 1; i++) {
			for (int j = i + 1; j < integers.size(); j++) {
				int ii = integers.get(i);
				int jj = integers.get(j);

				if (ii != 0 && jj != 0) {
					if ((ii % jj) == 0) {
						return ii / jj;
					} else if ((jj % ii) == 0) {
						return jj / ii;
					}
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		December2nd obj = new December2nd();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December2nd.class, "input2.txt");

		System.out.println("Count 1: " + obj.firstStar(inputs));
		System.out.println("Count 2: " + obj.secondStar(inputs));
	}

}

package year2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.InputReader;

public class December7th {

	public int part1(List<String> inputs) {	
		return 0;
	}

	public int part2(List<String> inputs) {
		return 0;
	}

	public static void main(String[] args) throws IOException {
		December7th obj = new December7th();
		InputReader reader = new InputReader();
		List<String> inputs = reader.readCustomAnswers(December7th.class, "input6.txt");

		System.out.println("Part 1:" + obj.part1(inputs));
		System.out.println("Part 2:" + obj.part2(inputs));
	}
}

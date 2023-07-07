package year2022;

import java.io.IOException;
import java.util.List;

import common.InputReader;

public class Dec {

	public int getAnswerPart1(List<String> inputs) {
		return 1;
	}
	
	public int getAnswerPart2(List<String> inputs) {
		return 1;
	}

	public static void main(String[] args) throws IOException {
		Dec obj = new Dec();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(Dec.class, "input5.txt");
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}
}

package year2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.InputReader;

public class December6th {

	public Integer getAnswerPart1(List<String> inputs) {

		return 1;

	}

	public Integer getAnswerPart2(List<String> inputs) {
		return 1;
	}

	public static void main(String[] args) throws IOException {
		December6th obj = new December6th();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December6th.class, "input5.txt");
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}
}

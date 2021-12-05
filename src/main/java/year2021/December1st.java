package year2021;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import common.InputReader;

public class December1st {

	public Integer getAnswerPart1(List<String> inputs) {
		List<Integer> inputsAsInt = inputs.stream().map(input -> Integer.valueOf(input)).collect(Collectors.toList());

		int increment = 0;
		
		for (int i = 1; i < inputsAsInt.size(); i++) {
			int previousInt = inputsAsInt.get(i-1);
			int currentInt = inputsAsInt.get(i);
			
			if (currentInt > previousInt) {
				increment++;
			}			
		}
		
		return increment;
	}

	public Integer getAnswerPart2(List<String> inputs) {
		List<Integer> inputsAsInt = inputs.stream().map(input -> Integer.valueOf(input)).collect(Collectors.toList());

		int increment = 0;
		int previousSum = -1;
		
		for (int i = 2; i < inputsAsInt.size(); i++) {
			int firstInt = inputsAsInt.get(i-2);
			int secondInt = inputsAsInt.get(i-1);
			int thirdInt = inputsAsInt.get(i);
			
			int sum = firstInt + secondInt + thirdInt;
			
			if (previousSum != -1 && sum > previousSum) {
				increment++;
			}
			
			previousSum = sum;
		}

		return increment;
	}

	public static void main(String[] args) throws IOException {
		December1st obj = new December1st();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December1st.class, "input1.txt");

		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}

}

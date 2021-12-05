package year2021;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import common.InputReader;

public class December2nd {
	
	int position = 0;
	int depth = 0;
	int aim = 0;

	public Integer getAnswerPart1(List<String> inputs) {
		List<Direction> inputsAsInt = inputs.stream()
				.map(input -> getDirectionFromInput(input))
				.collect(Collectors.toList());
		
		position = 0;
		depth = 0;
		aim = 0;
		
		for (int i = 0; i < inputsAsInt.size(); i++) {
			Direction direction = inputsAsInt.get(i);
			
			if (direction.direction.equals("forward")) {
				position += direction.units;
			}
			
			if (direction.direction.equals("up")) {
				depth -= direction.units;
			}
			
			if (direction.direction.equals("down")) {
				depth += direction.units;
			}
		}
		
		return position * depth;
	}

	public Integer getAnswerPart2(List<String> inputs) {
		List<Direction> inputsAsInt = inputs.stream()
				.map(input -> getDirectionFromInput(input))
				.collect(Collectors.toList());
		position = 0;
		depth = 0;
		aim = 0;
		
		for (int i = 0; i < inputsAsInt.size(); i++) {
			Direction direction = inputsAsInt.get(i);
			
			if (direction.direction.equals("forward")) {
				position += direction.units;
				depth += (aim * direction.units);
			}
			
			if (direction.direction.equals("up")) {
				aim -= direction.units;
			}
			
			if (direction.direction.equals("down")) {
				aim += direction.units;
			}
		}
		
		return position * depth;
	}

	public static void main(String[] args) throws IOException {
		December2nd obj = new December2nd();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December2nd.class, "input2.txt");

		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}
	
	private Direction getDirectionFromInput(String input) {
		String[] directions = input.split(" ");
		return new Direction(directions[0], Integer.valueOf(directions[1]));
	}
	
	private class Direction {
		
		public String direction;
		public int units;
		
		public Direction(String direction, int units) {
			this.direction = direction;
			this.units = units;
		}
	}
}

package year2019;

import java.io.IOException;

import common.InputReader;

public class December2nd {
	
	/**
	 * December 2nd, 2019
	 */
	public String opcodesPart1(String[] opcodes) {
		int opCodeCounter = 0;

		while (Integer.valueOf(opcodes[opCodeCounter]) != 99) {
			if (Integer.valueOf(opcodes[opCodeCounter]) == 1) { // Addition
				Integer position1 = Integer.valueOf(opcodes[opCodeCounter + 1]);
				Integer position2 = Integer.valueOf(opcodes[opCodeCounter + 2]);
				Integer addition = Integer.valueOf(opcodes[position1]) + Integer.valueOf(opcodes[position2]);
				Integer position = Integer.valueOf(opcodes[opCodeCounter + 3]);
				opcodes[position] = addition.toString();
				opCodeCounter += 4;
			}

			if (Integer.valueOf(opcodes[opCodeCounter]) == 2) { // Multiplication
				Integer position1 = Integer.valueOf(opcodes[opCodeCounter + 1]);
				Integer position2 = Integer.valueOf(opcodes[opCodeCounter + 2]);
				Integer multiplication = Integer.valueOf(opcodes[position1]) * Integer.valueOf(opcodes[position2]);
				Integer position = Integer.valueOf(opcodes[opCodeCounter + 3]);
				opcodes[position] = multiplication.toString();
				opCodeCounter += 4;
			}
		}

		return opcodes[0];
	}

	/**
	 * December 2nd, 2019, Part 2
	 */
	public String opcodesPart2() {
		String finalProgram = "";
		for (Integer i = 0; i < 100; i++) {
			for (Integer j = 0; j < 100; j++) {
				String[] opcodes = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,5,23,1,23,9,27,2,27,6,31,1,31,6,35,2,35,9,39,1,6,39,43,2,10,43,47,1,47,9,51,1,51,6,55,1,55,6,59,2,59,10,63,1,6,63,67,2,6,67,71,1,71,5,75,2,13,75,79,1,10,79,83,1,5,83,87,2,87,10,91,1,5,91,95,2,95,6,99,1,99,6,103,2,103,6,107,2,107,9,111,1,111,5,115,1,115,6,119,2,6,119,123,1,5,123,127,1,127,13,131,1,2,131,135,1,135,10,0,99,2,14,0,0".split(",");				
				opcodes[1] = i.toString();
				opcodes[2] = j.toString();

				String result = opcodesPart1(opcodes);
				System.out.println("Result :" + result);
				if (result.equals("19690720")) {
					return printOpcodes(opcodes);
				}
			}
		}
		
		return finalProgram;
	}

	private String printOpcodes(String[] opcodes) {
		String opcodeProgram2 = "";
		for (int i = 0; i < opcodes.length; i++) {
			opcodeProgram2 = opcodeProgram2 + "," + opcodes[i];
		}

		return opcodeProgram2.substring(1);
	}

	public static void main(String[] args) throws IOException {
		December2nd obj = new December2nd();
		InputReader reader = new InputReader();
		String[] inputs = reader.readLine(December2nd.class, "input2.txt");

		System.out.println("Answer Part 1: " + obj.opcodesPart1(inputs));
		System.out.println("Answer Part 2: " + obj.opcodesPart2());
	}
}

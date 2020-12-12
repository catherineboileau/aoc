package year2019;

import java.io.IOException;
import java.util.Scanner;

import common.InputReader;

public class December5th {

	public Scanner inputReader = new Scanner(System.in);
	private String[] opcodes;
	private int parameterMode;

	public December5th(String[] opcodes) {
		this.opcodes = opcodes;
		this.parameterMode = -1;
	}
	
	public December5th(String[] opcodes, int parameterMode) {
		this.opcodes = opcodes;
		this.parameterMode = parameterMode;
	}

	/**
	 * December 5th, 2019, Part 1
	 */
	public String part1() {
		int opCodeCounter = 0;
		String instruction = opcodes[opCodeCounter];

		while (!instruction.equals("99")) {
			System.out.println("Iteration: " + opCodeCounter);
			opCodeCounter = addition(instruction, opCodeCounter);
			opCodeCounter = multiplication(instruction, opCodeCounter);
			opCodeCounter = getInput(instruction, opCodeCounter);
			opCodeCounter = getOutput(instruction, opCodeCounter);

			instruction = opcodes[opCodeCounter];
		}

		printOpcodes();
		return opcodes[0];
	}

	/**
	 * December 5th, 2019, Part 2
	 */
	public String part2() {
		int opCodeCounter = 0;
		String instruction = opcodes[opCodeCounter];

		while (!instruction.equals("99")) {
			System.out.println("Iteration: " + opCodeCounter);
			opCodeCounter = addition(instruction, opCodeCounter);
			opCodeCounter = multiplication(instruction, opCodeCounter);
			opCodeCounter = getInput(instruction, opCodeCounter);
			opCodeCounter = getOutput(instruction, opCodeCounter);
			opCodeCounter = jumpIfTrue(instruction, opCodeCounter);
			opCodeCounter = jumpIfFalse(instruction, opCodeCounter);
			opCodeCounter = lessThan(instruction, opCodeCounter);
			opCodeCounter = isEquals(instruction, opCodeCounter);

			instruction = opcodes[opCodeCounter];
		}

		printOpcodes();
		return opcodes[0];
	}
	
	private int addition(String instruction, int opCodeCounter) {
		if (instruction.endsWith("01") || instruction.equals("1")) { // Addition
			int term1 = getTerm1(instruction, opCodeCounter + 1);
			int term2 = getTerm2(instruction, opCodeCounter + 2);
			int position = Integer.valueOf(opcodes[opCodeCounter + 3]);

			Integer addition = term1 + term2;
			opcodes[position] = addition.toString();
			opCodeCounter += 4;
		}
		
		return opCodeCounter;
	}
	
	private int multiplication(String instruction, int opCodeCounter) {
		if (instruction.endsWith("02") || instruction.equals("2")) { // Multiplication
			int term1 = getTerm1(instruction, opCodeCounter + 1);
			int term2 = getTerm2(instruction, opCodeCounter + 2);
			int position = Integer.valueOf(opcodes[opCodeCounter + 3]);

			Integer multiplication = term1 * term2;
			opcodes[position] = multiplication.toString();
			opCodeCounter += 4;
		}
		
		return opCodeCounter;
	}
	
	private int getInput(String instruction, int opCodeCounter) {
		if (instruction.endsWith("03") || instruction.equals("3")) { // Input
			System.out.println("Enter value: ");
			Integer input = inputReader.nextInt();
			Integer position = Integer.valueOf(opcodes[opCodeCounter + 1]);
			opcodes[position] = input.toString();
			opCodeCounter += 2;
		}
		
		return opCodeCounter;
	}
	
	private int getOutput(String instruction, int opCodeCounter) {
		if (instruction.endsWith("04") || instruction.equals("4")) { // Output
			Integer valueToOutput = getTerm1(instruction, opCodeCounter + 1);
			System.out.println("Code: " + valueToOutput);
			opCodeCounter += 2;
		}
		
		return opCodeCounter;
	}
	
	private int jumpIfTrue(String instruction, int opCodeCounter) {
		if (instruction.endsWith("5")) { // jump-if-true
			Integer term1 = getTerm1(instruction, opCodeCounter + 1);
			Integer term2 = getTerm2(instruction, opCodeCounter + 2);
			if (term1 != 0) {
				opCodeCounter = term2;
			}
		}	
		
		return opCodeCounter;
	}
	
	private int jumpIfFalse(String instruction, int opCodeCounter) {
		if (instruction.endsWith("6")) { // jump-if-false
			Integer term1 = getTerm1(instruction, opCodeCounter + 1);
			Integer term2 = getTerm2(instruction, opCodeCounter + 2);
			if (term1 == 0) {
				opCodeCounter = term2;
			}
		}
		
		return opCodeCounter;
	}
	
	private int lessThan(String instruction, int opCodeCounter) {
		if (instruction.endsWith("7")) { // less-than
			Integer term1 = getTerm1(instruction, opCodeCounter + 1);
			Integer term2 = getTerm2(instruction, opCodeCounter + 1);
			Integer store = getTerm3(instruction, opCodeCounter + 1);
			if (term1 < term2) {
				opcodes[store] = "1";
			} else {
				opcodes[store] = "0";
			}
			opCodeCounter += 3;
		}
		
		return opCodeCounter;
	}
	
	private int isEquals(String instruction, int opCodeCounter) {
		if (instruction.endsWith("8")) { // equals
			Integer term1 = getTerm1(instruction, opCodeCounter + 1);
			Integer term2 = getTerm2(instruction, opCodeCounter + 1);
			Integer store = getTerm3(instruction, opCodeCounter + 1);
			if (term1 == term2) {
				opcodes[store] = "1";
			} else {
				opcodes[store] = "0";
			}
			opCodeCounter += 3;
		}	
		
		return opCodeCounter;
	}

	private int getTerm1(String instruction, int position) {
		return getTerm(instruction, position, 1);
	}

	private int getTerm2(String instruction, int position) {
		return getTerm(instruction, position, 2);
	}

	private int getTerm3(String instruction, int position) {
		return getTerm(instruction, position, 3);
	}

	private int getTerm(String instruction, int position, int term) {
		int value = 0;
		char parametreModeAsChar = '0';
		char[] instructions = instruction.toCharArray();
		
		if (this.parameterMode == -1) {
			int parameterModePosition = instructions.length - (term + 2);
			try {
				parametreModeAsChar = instructions[parameterModePosition];
			} catch (ArrayIndexOutOfBoundsException exception) {
				parametreModeAsChar = '0';
			}
		} else { //if parameterMode is defined globally
			if (this.parameterMode == 0) { 
				parametreModeAsChar = '0';
			} else {
				parametreModeAsChar = '1';
			}
		}

		if (parametreModeAsChar == '0') { // parameter mode memory
			int position1 = Integer.valueOf(opcodes[position]);
			value = Integer.valueOf(opcodes[position1]);
		} else if (parametreModeAsChar == '1') { // parameter mode immediate
			value = Integer.valueOf(opcodes[position]);
		}

		return value;
	}

	private String printOpcodes() {
		String opcodeProgram2 = "";
		for (int i = 0; i < opcodes.length; i++) {
			opcodeProgram2 = opcodeProgram2 + "," + opcodes[i];
		}

		String resultat = opcodeProgram2.substring(1);
		System.out.println("Program: " + resultat);
		return resultat;
	}

	public static void main(String[] args) throws IOException {
		InputReader reader = new InputReader();
		
//		String[] inputs = reader.readLine(December5th.class, "input5.txt");
//		December5th obj = new December5th(inputs, -1);
//		System.out.println("Answer Part 1: " + obj.part1());
		
		String[] inputs2 = reader.readLine(December5th.class, "input5.txt");
		December5th obj2 = new December5th(inputs2, -1);
		System.out.println("Answer Part 2: " + obj2.part2());
	}
}

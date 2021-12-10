package year2019;

import java.util.Scanner;

public class OpcodeProgram {

	private static final int ADDITION_LEAP_POINTER = 4;
	private static final int MULTIPLICATION_LEAP_POINTER = 4;
	private static final String OPCODE_ADDITION = "1";
	private static final String OPCODE_ADDITION_WITH_MODE = "01";
	private static final String OPCODE_MULTIPLICATION = "2";
	private static final String OPCODE_MULTIPLICATION_WITH_MODE = "02";
	private static final String OPCODE_INPUT = "3";
	private static final String OPCODE_INPUT_WITH_MODE = "03";
	private static final String OPCODE_OUTPUT = "4";
	private static final String OPCODE_OUTPUT_WITH_MODE = "04";
	private static final String OPCODE_JUMP_IF_TRUE = "5";
	private static final String OPCODE_JUMP_IF_TRUE_WITH_MODE = "05";
	private static final String OPCODE_JUMP_IF_FALSE = "6";
	private static final String OPCODE_JUMP_IF_FALSE_WITH_MODE = "06";
	private static final String OPCODE_LESS_THAN = "7";
	private static final String OPCODE_LESS_THAN_WITH_MODE = "07";
	private static final String OPCODE_EQUALS = "8";
	private static final String OPCODE_EQUALS_WITH_MODE = "08";
	private static final String END_OF_CODE = "99";
	private static final int INSTRUCTION_POINTER_STARTING_POINT = 0;

	public Scanner inputReader = new Scanner(System.in);
	public String[] memory; // first position in memory is called 'address 0'
	public int instructionPointer = INSTRUCTION_POINTER_STARTING_POINT;
	public int parameterMode = -1;

	public OpcodeProgram(String[] opcodes) {
		this.memory = opcodes;
	}

	public void executeProgram() {
		while (isNotEndOfCode()) {
			String nextInstruction = memory[instructionPointer];
			if (isAdditionInstruction()) {
				addition(nextInstruction);
			}

			if (isMultiplicationInstruction()) {
				multiplication(nextInstruction);
			}
			
//			if (isInputInstruction()) {
//				input(nextInstruction);
//			}
//			
//			if (isOutputInstruction()) {
//				output(nextInstruction);
//			}
//			
//			if (isJumpIfTrueInstruction()) {
//				jumpIfTrue(nextInstruction);
//			}
//			
//			if (isJumpIfFalseInstruction()) {
//				jumpIfFalse(nextInstruction);
//			}
//			
//			if (isLessThanInstruction()) {
//				lessThan(nextInstruction);
//			}
//			
//			if (isEqualsInstruction()) {
//				equals(nextInstruction);
//			}
		}
	}

	public boolean isNotEndOfCode() {
		String instruction = memory[instructionPointer];
		return !instruction.equals(END_OF_CODE);
	}

	public boolean isAdditionInstruction() {
		String instruction = memory[instructionPointer];
		return instruction.equals(OPCODE_ADDITION) || instruction.endsWith(OPCODE_ADDITION_WITH_MODE);
	}

	public boolean isMultiplicationInstruction() {
		String instruction = memory[instructionPointer];
		return instruction.equals(OPCODE_MULTIPLICATION) || instruction.endsWith(OPCODE_MULTIPLICATION_WITH_MODE);
	}

	public boolean isInputInstruction() {
		String instruction = memory[instructionPointer];
		return instruction.equals(OPCODE_INPUT) || instruction.endsWith(OPCODE_INPUT_WITH_MODE);
	}

	public boolean isOutputInstruction() {
		String instruction = memory[instructionPointer];
		return instruction.equals(OPCODE_OUTPUT) || instruction.endsWith(OPCODE_OUTPUT_WITH_MODE);
	}

	public boolean isJumpIfTrueInstruction() {
		String instruction = memory[instructionPointer];
		return instruction.equals(OPCODE_JUMP_IF_TRUE) || instruction.endsWith(OPCODE_JUMP_IF_TRUE_WITH_MODE);
	}

	public boolean isJumpIfFalseInstruction() {
		String instruction = memory[instructionPointer];
		return instruction.equals(OPCODE_JUMP_IF_FALSE) || instruction.endsWith(OPCODE_JUMP_IF_FALSE_WITH_MODE);
	}

	public boolean isLessThanInstruction() {
		String instruction = memory[instructionPointer];
		return instruction.equals(OPCODE_LESS_THAN) || instruction.endsWith(OPCODE_LESS_THAN_WITH_MODE);
	}

	public boolean isEqualsInstruction() {
		String instruction = memory[instructionPointer];
		return instruction.equals(OPCODE_EQUALS) || instruction.endsWith(OPCODE_EQUALS_WITH_MODE);
	}

	public void addition(String instruction) {

		int term1 = getTerm1(instruction, instructionPointer + 1);
		int term2 = getTerm2(instruction, instructionPointer + 2);
		int position = Integer.valueOf(memory[instructionPointer + 3]);

		Integer multiplication = term1 * term2;
		memory[position] = multiplication.toString();
		instructionPointer += ADDITION_LEAP_POINTER;
	}

	public void multiplication(String instruction) {
		int term1 = getTerm1(instruction, instructionPointer + 1);
		int term2 = getTerm2(instruction, instructionPointer + 2);
		int position = Integer.valueOf(memory[instructionPointer + 3]);

		Integer multiplication = term1 * term2;
		memory[position] = multiplication.toString();
		instructionPointer += MULTIPLICATION_LEAP_POINTER;
	}

	public void input(String instruction) {
		if (instruction.endsWith("03") || instruction.equals("3")) { // Input
			System.out.println("Enter value: ");
			Integer input = inputReader.nextInt();
			Integer position = Integer.valueOf(memory[instructionPointer + 1]);
			memory[position] = input.toString();
			instructionPointer += 2;
		}
	}

	public void output(String instruction) {
		Integer valueToOutput = getTerm1(instruction, instructionPointer + 1);
		System.out.println("Code: " + valueToOutput);
		instructionPointer += 2;
	}

	public void jumpIfTrue(String instruction) {
		Integer term1 = getTerm1(instruction, instructionPointer + 1);
		Integer term2 = getTerm2(instruction, instructionPointer + 2);
		if (term1 != 0) {
			instructionPointer = term2;
		}
	}

	public void jumpIfFalse(String instruction) {
			Integer term1 = getTerm1(instruction, instructionPointer + 1);
			Integer term2 = getTerm2(instruction, instructionPointer + 2);
			if (term1 == 0) {
				instructionPointer = term2;
			}
	}

	public void lessThan(String instruction) {
			Integer term1 = getTerm1(instruction, instructionPointer + 1);
			Integer term2 = getTerm2(instruction, instructionPointer + 1);
			Integer store = getTerm3(instruction, instructionPointer + 1);
			if (term1 < term2) {
				memory[store] = "1";
			} else {
				memory[store] = "0";
			}
			instructionPointer += 3;
	}

	public void equals(String instruction) {
			Integer term1 = getTerm1(instruction, instructionPointer + 1);
			Integer term2 = getTerm2(instruction, instructionPointer + 1);
			Integer store = getTerm3(instruction, instructionPointer + 1);
			if (term1 == term2) {
				memory[store] = "1";
			} else {
				memory[store] = "0";
			}
			instructionPointer += 3;
	}

	public String getSolution() {
		return memory[INSTRUCTION_POINTER_STARTING_POINT];
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
		} else { // if parameterMode is defined globally
			if (this.parameterMode == 0) {
				parametreModeAsChar = '0';
			} else {
				parametreModeAsChar = '1';
			}
		}

		if (parametreModeAsChar == '0') { // parameter mode memory
			int position1 = Integer.valueOf(memory[position]);
			value = Integer.valueOf(memory[position1]);
		} else if (parametreModeAsChar == '1') { // parameter mode immediate
			value = Integer.valueOf(memory[position]);
		}

		return value;
	}
}

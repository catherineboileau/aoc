package year2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.InputReader;

public class December8th {

	public Integer getAnswerPart1(List<String> inputs) {
		List<Line> lines = extractFromFile(inputs);

		return getCountOfEasyDigits(lines);
	}

	public int getAnswerPart2(List<String> inputs) {
		List<Line> lines = extractFromFile(inputs);
		int sumOfLines = 0;

		for (Line line : lines) {

			String[] patterns = getDeductorForALine(line);
			deductDigitsSixLong(line, patterns);
			deductDigitsFiveLong(line, patterns);
			List<Set<Character>> patternsAsSet = getSetOfCharacters(patterns);

			String fourDigitDisplayed = "";
			for (String digit : line.fourDigitValues) {
				Set<Character> digitAsSet = stringToCharacterSet(digit);
				if (patternsAsSet.contains(digitAsSet)) {
					fourDigitDisplayed = fourDigitDisplayed + "" + patternsAsSet.indexOf(digitAsSet);
				}
			}

			int fourDigitAsInt = Integer.valueOf(fourDigitDisplayed);
			sumOfLines += fourDigitAsInt;
		}

		return sumOfLines;
	}

	private int deductDigitsFiveLong(Line line, String[] patterns) {

			for (String digit : line.uniqueSignalPatterns) {

				if (digit.length() == 5) { // Digit is 2, 3, or 5
					if (December8th.containsAllChars(digit, patterns[1])) { // Then it is a 3
						patterns[3] = digit;
					} else {
						Set<Character> digitAsChar = stringToCharacterSet(digit);
						Set<Character> sixAsChar = stringToCharacterSet(patterns[6]);
						digitAsChar.retainAll(sixAsChar);

						if (digitAsChar.size() == 5) {
							patterns[5] = digit;
						} else {
							patterns[2] = digit;
						}

					}
				}
			}
		
			
		return 0;
	}

	private int deductDigitsSixLong(Line line, String[] patterns) {
		for (String digit : line.uniqueSignalPatterns) {
			if (digit.length() == 6) { // Digit is 6 or 9, 0
				if (December8th.containsAllChars(digit, patterns[7])) { // Then is is a 9 or 0
					if (December8th.containsAllChars(digit, patterns[4])) {
						patterns[9] = digit;
					} else {
						patterns[0] = digit;
					}
				} else {
					patterns[6] = digit;
				}
			}
		}

		return 0;
	}

	private int getCountOfEasyDigits(List<Line> lines) {
		int countOfOne = 0;

		int countOfFour = 0;
		int countOfSeven = 0;
		int countOfEight = 0;

		for (Line line : lines) {
			for (String digit : line.fourDigitValues) {
				if (digit.length() == 2) {
					countOfOne += 1;
				}

				if (digit.length() == 3) {
					countOfSeven += 1;
				}

				if (digit.length() == 4) {
					countOfFour += 1;
				}

				if (digit.length() == 5) { // Digit is 2, 3, or 5

				}

				if (digit.length() == 6) { // Digit is 6 or 9, 0

				}

				if (digit.length() == 7) {
					countOfEight += 1;
				}
			}
		}
		return countOfOne + countOfFour + countOfSeven + countOfEight;
	}

	private String[] getDeductorForALine(Line line) {
		String[] patterns = { "", "", "", "", "", "", "", "", "", "" };

		for (String item : line.uniqueSignalPatterns) {

			if (item.length() == 2) {
				patterns[1] = item;
			}

			if (item.length() == 3) {
				patterns[7] = item;
			}

			if (item.length() == 4) {
				patterns[4] = item;
			}

			if (item.length() == 7) {
				patterns[8] = item;
			}
		}

		return patterns;
	}

	public List<Set<Character>> getSetOfCharacters(String[] patterns) {
		List<Set<Character>> sets = new ArrayList<Set<Character>>();
		for (int i = 0; i < 10; i++) {
			Set<Character> set = stringToCharacterSet(patterns[i]);
			sets.add(set);
		}

		return sets;
	}

	public static void main(String[] args) throws IOException {
		December8th obj = new December8th();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December8th.class, "input8.txt");
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}

	public List<Line> extractFromFile(List<String> inputs) {
		List<Line> lines = new ArrayList<>();

		for (String input : inputs) {
			String signal = input.substring(0, input.indexOf('|'));
			String digit = input.substring(input.indexOf('|') + 2);
			String[] signals = signal.split(" ");
			String[] digits = digit.split(" ");

			lines.add(new Line(Arrays.asList(signals), Arrays.asList(digits)));
		}

		return lines;
	}

	public static Set<Character> stringToCharacterSet(String s) {
		Set<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			set.add(c);
		}
		return set;
	}

	public static boolean containsAllChars(String container, String containee) {
		return stringToCharacterSet(container).containsAll(stringToCharacterSet(containee));
	}

	public static boolean isSameLetters(String container, String containee) {
		return stringToCharacterSet(container).equals(stringToCharacterSet(containee));
	}

	public class Line {
		List<String> uniqueSignalPatterns;
		List<String> fourDigitValues;

		public Line(List<String> uniqueSignalPatterns, List<String> fourDigitValues) {
			this.uniqueSignalPatterns = uniqueSignalPatterns;
			this.fourDigitValues = fourDigitValues;
		}

		public String toString() {
			return "Line: " + uniqueSignalPatterns.toString() + "," + fourDigitValues.toString();
		}
	}
}

package year2021;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import common.InputReader;

public class December10th {

	public Long getAnswerPart1(List<String> inputs) {
		long sum = 0;
		
		for (String input : inputs) {
			Line line = new Line(input);
			sum += line.getInvalidPointsForLine();
		}

		return sum;
	}

	public BigInteger getAnswerPart2(List<String> inputs) {
		long sum = 0;
		List<Line> incompletes = new ArrayList<>();
		for (String input : inputs) {
			Line line = new Line(input);
			if (!line.isInvalidLine()) {
				incompletes.add(line);
			}
		}
		
		List<BigInteger> scores = new ArrayList<>();
		for (Line incomplete : incompletes) {
			scores.add(incomplete.completeLine());
		}
		
		Collections.sort(scores);
		BigInteger middleScore = scores.get(scores.size() / 2);

		return middleScore;
	}

	public static void main(String[] args) throws IOException {
		December10th obj = new December10th();
		InputReader reader = new InputReader();
		//List<String> inputs = reader.read(December10th.class, "testInput.txt");
		List<String> inputs = reader.read(December10th.class, "input10.txt");
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}

	public class Line {
		public String line;

		public Line(String line) {
			this.line = line;
		}
		
		public BigInteger completeLine() {
			Stack<Character> stack = new Stack<>();
			String completion = "";
			
			for (int i = 0; i < line.length(); i++) {
				Character nextChar = line.charAt(i);

				if (isOpeningChar(nextChar)) {
					stack.push(nextChar);
				}

				if (isClosingChar(nextChar)) {
					Character matchingOpeningChar = stack.pop();
					if (!isMatchingChar(matchingOpeningChar, nextChar)) {
						throw new IllegalArgumentException("Corrupted line in incompletes");
					}
				}
			}
			
			while (!stack.isEmpty()) {
				Character nextChar = stack.pop();
				completion = completion + getMatchingChar(nextChar);
			}
			
			return getScoreOfCompletion(completion);
		}
		
		public BigInteger getScoreOfCompletion(String completion) {
			BigInteger score = BigInteger.ZERO;
			for(int i=0; i<completion.length();i++) {
				char currentChar = completion.charAt(i);
				score = (score.multiply(BigInteger.valueOf(5L))).add(BigInteger.valueOf(getPointForSecondPart(currentChar)));
			}
			
			System.out.println("Score for line "+ completion+": "+score);
			return score;
		}

		public int getInvalidPointsForLine() {
			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < line.length(); i++) {
				Character nextChar = line.charAt(i);

				if (isOpeningChar(nextChar)) {
					stack.push(nextChar);
				}

				if (isClosingChar(nextChar)) {
					Character matchingOpeningChar = stack.pop();
					if (!isMatchingChar(matchingOpeningChar, nextChar)) {
						System.out.println("Corrupted char =" + nextChar);
						return getPointFor(nextChar);
					}
				}
			}

			return 0;
		}
		
		public boolean isInvalidLine() {
			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < line.length(); i++) {
				Character nextChar = line.charAt(i);

				if (isOpeningChar(nextChar)) {
					stack.push(nextChar);
				}

				if (isClosingChar(nextChar)) {
					Character matchingOpeningChar = stack.pop();
					if (!isMatchingChar(matchingOpeningChar, nextChar)) {
						return true;
					}
				}
			}

			return false;
			
		}

		public int getPointFor(char charToPoint) {
			if (charToPoint == ')') {
				return 3;
			}
			if (charToPoint == ']') {
				return 57;
			}
			if (charToPoint == '}') {
				return 1197;
			}
			if (charToPoint == '>') {
				return 25137;
			}

			return 0;
		}

		public int getPointForSecondPart(char charToPoint) {
			if (charToPoint == ')') {
				return 1;
			}
			if (charToPoint == ']') {
				return 2;
			}
			if (charToPoint == '}') {
				return 3;
			}
			if (charToPoint == '>') {
				return 4;
			}

			return 0;
		}

		public boolean isOpeningChar(char openingChar) {
			return (openingChar == '(' || openingChar == '<' || openingChar == '[' || openingChar == '{');
		}

		public boolean isClosingChar(char closingChar) {
			return (closingChar == ')' || closingChar == '>' || closingChar == ']' || closingChar == '}');
		}

		public boolean isMatchingChar(char openingChar, char closingChar) {
			if (openingChar == '(' && closingChar == ')') {
				return true;
			}
			if (openingChar == '<' && closingChar == '>') {
				return true;
			}
			if (openingChar == '[' && closingChar == ']') {
				return true;
			}
			if (openingChar == '{' && closingChar == '}') {
				return true;
			}

			return false;
		}

		public String getMatchingChar(char openingChar) {
			if (openingChar == '(') {
				return ")";
			}
			if (openingChar == '<') {
				return ">";
			}
			if (openingChar == '[') {
				return "]";
			}
			if (openingChar == '{') {
				return "}";
			}

			return "";
		}
		
		public String toString() {
			return this.line;
		}
	}

}

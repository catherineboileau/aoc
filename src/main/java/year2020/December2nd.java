package year2020;

import java.io.IOException;
import java.util.List;

import common.InputReader;

public class December2nd {

	public boolean verifyPassword(String password) {
		String[] split = password.split(" ");
		Integer minimumOccurences = Integer.valueOf(split[0].substring(0, split[0].indexOf("-")));
		Integer maximumOccurences = Integer.valueOf(split[0].substring(split[0].indexOf("-") + 1));
		char letter = split[1].charAt(0);
		int numberOfLettersInPassword = this.getNumbersOfLetters(split[2], letter);
		
		if (numberOfLettersInPassword >= minimumOccurences && numberOfLettersInPassword <= maximumOccurences) {
			System.out.println(password + "," + numberOfLettersInPassword + ", true");
			return true;
		}

		System.out.println(password + "," + numberOfLettersInPassword + ", false");
		return false;
	}

	public boolean verifyPasswordPart2(String password) {
		String[] split = password.split(" ");
		Integer minimumOccurences = Integer.valueOf(split[0].substring(0, split[0].indexOf("-")));
		Integer maximumOccurences = Integer.valueOf(split[0].substring(split[0].indexOf("-") + 1));
		char letter = split[1].charAt(0);
		
		if (split[2].charAt(minimumOccurences - 1) == letter || split[2].charAt(maximumOccurences - 1) == letter) {
			if (split[2].charAt(minimumOccurences - 1) == letter && split[2].charAt(maximumOccurences - 1) == letter) {
				System.out.println(password + ", false");
				return false;
			}

			System.out.println(password + ", true");
			return true;	
		}

		System.out.println(password + ", false");
		return false;
	}

	private int getNumbersOfLetters(String password, char letter) {
		int count = 0;

		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == letter) {
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) throws IOException {
		December2nd obj = new December2nd();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December1st.class, "input2.txt");
		int counter1 = 0;
		int counter2 = 0;

		for (int i = 0; i < inputs.size(); i++) {
			if (obj.verifyPassword(inputs.get(i))) {
				counter1++;
			}
		}
		
		for (int i = 0; i < inputs.size(); i++) {
			if (obj.verifyPasswordPart2(inputs.get(i))) {
				counter2++;
			}
		}
		
		System.out.println("Numbers of valid passwords: " + counter1);
		System.out.println("Numbers of valid passwords, part 2: " + counter2);
	}
}

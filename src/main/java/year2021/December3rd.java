package year2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import common.InputReader;

public class December3rd {

	private static int SIZE = 12;

	public Integer getAnswerPart1(List<String> inputs) {
		Rate rate = getRates(inputs, SIZE, true);
		int decimalGamma = parseFromBinaryString(rate.gamma);
		int decimalEpsilon = parseFromBinaryString(rate.epsilon);

		return decimalGamma * decimalEpsilon;
	}

	private int parseFromBinaryString(String binaryString) {
		return Integer.parseInt(binaryString, 2);
	}

	private Rate getRates(List<String> inputs, int index, boolean shouldKeepOneInEquality) {
		int[] countOfZero = new int[SIZE];
		int[] countOfOne = new int[SIZE];

		for (String input : inputs) {
			for (int i = 0; i < index; i++) {
				if (input.charAt(i) == '0') {
					countOfZero[i] += 1;
				} else {
					countOfOne[i] += 1;
				}
			}
		}

		String gammaRate2 = "";
		String epsilonRate2 = "";

		for (int i = 0; i < index; i++) {
			if (countOfZero[i] > countOfOne[i]) {
				gammaRate2 = gammaRate2 + "0";
				epsilonRate2 = epsilonRate2 + "1";
			} else {
				if (countOfZero[i] == countOfOne[i]) {
					if (shouldKeepOneInEquality) {
						gammaRate2 = gammaRate2 + "1";
						epsilonRate2 = epsilonRate2 + "0";
					} else {
						gammaRate2 = gammaRate2 + "0";
						epsilonRate2 = epsilonRate2 + "1";
					}
				} else {
					gammaRate2 = gammaRate2 + "1";
					epsilonRate2 = epsilonRate2 + "0";
				}
			}
		}

		return new Rate(gammaRate2, epsilonRate2);

	}

	public Integer getAnswerPart2(List<String> inputs) {
		List<String> oxygenInputs = new ArrayList<>(inputs);
		List<String> dioxideInputs = new ArrayList<>(inputs);
		int oxygen = 0;
		int dioxide = 0;

		for (int i = 0; i < SIZE; i++) {
			Rate rate = getRates2(oxygenInputs, i, true);
			String indexGamma = rate.gamma;
			Rate rate2 = getRates2(dioxideInputs, i, true);
			String indexEpsilon = rate2.epsilon;

			final int index = i;
			oxygenInputs = oxygenInputs.stream().filter(s -> isEqualToRate2(s, indexGamma, index))
					.collect(Collectors.toList());
			dioxideInputs = dioxideInputs.stream().filter(s -> isEqualToRate2(s, indexEpsilon, index))
					.collect(Collectors.toList());

			if (oxygenInputs.size() == 1) {
				oxygen = parseFromBinaryString(oxygenInputs.get(0));
			}

			if (dioxideInputs.size() == 1) {
				dioxide = parseFromBinaryString(dioxideInputs.get(0));
			}
		}

		return oxygen * dioxide;
	}

	private Rate getRates2(List<String> inputs, int index, boolean shouldKeepOneInEquality) {
		int countOfZero = 0;
		int countOfOne = 0;

		for (String input : inputs) {
			if (input.charAt(index) == '0') {
				countOfZero += 1;
			} else {
				countOfOne += 1;
			}
		}

		String gammaRate2 = "";
		String epsilonRate2 = "";

		if (countOfZero > countOfOne) {
			gammaRate2 = gammaRate2 + "0";
			epsilonRate2 = epsilonRate2 + "1";
		} else {
			if (countOfZero == countOfOne) {
				if (shouldKeepOneInEquality) {
					gammaRate2 = gammaRate2 + "1";
					epsilonRate2 = epsilonRate2 + "0";
				} else {
					gammaRate2 = gammaRate2 + "0";
					epsilonRate2 = epsilonRate2 + "1";
				}
			} else {
				gammaRate2 = gammaRate2 + "1";
				epsilonRate2 = epsilonRate2 + "0";
			}
		}

		return new Rate(gammaRate2, epsilonRate2);

	}

	private boolean isEqualToRate2(String input, String rate, int index) {
		return input.charAt(index) == rate.charAt(0);
	}


	public static void main(String[] args) throws IOException {
		December3rd obj = new December3rd();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December3rd.class, "input3.txt");

		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}

	public class Rate {
		public String gamma;
		public String epsilon;

		public Rate(String gamma, String epsilon) {
			this.gamma = gamma;
			this.epsilon = epsilon;
		}
	}
}

package year2021;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import common.InputReader;

public class December7th {

	public Integer getAnswerPart1(List<String> inputs) {
		List<Integer> integers = getListAsInt(inputs);
		List<Integer> fuelForEachPosition = new ArrayList<>();

		for (int i = 0; i < integers.size(); i++) {
			int fuelNecessaryForPositionI = 0;
			for (int j = 0; j < integers.size(); j++) {
				int crabPosition = integers.get(j);
				int fuelNecessaryForThatCrab = Math.abs(crabPosition - i);
				fuelNecessaryForPositionI += fuelNecessaryForThatCrab;
			}

			fuelForEachPosition.add(fuelNecessaryForPositionI);
			// System.out.println("Fuel for position "+i +":"+fuelNecessaryForPositionI);
		}
		Collections.sort(fuelForEachPosition);

		return fuelForEachPosition.get(0);
	}

	public BigInteger getAnswerPart2(List<String> inputs) {
		List<Integer> integers = getListAsInt(inputs);
		List<BigInteger> fuelForEachPosition = new ArrayList<>();

		for (int i = 0; i < integers.size(); i++) {
			BigInteger fuelNecessaryForPositionI = BigInteger.ZERO;
			for (int j = 0; j < integers.size(); j++) {
				int crabPosition = integers.get(j);
				int fuelForThatCrab = Math.abs(crabPosition - i);
				BigInteger fuelNecessaryForThatCrab = factorial(BigInteger.valueOf(fuelForThatCrab));
				//System.out.println("Fuel for crab! " + i + ":" + fuelNecessaryForThatCrab);
				fuelNecessaryForPositionI = fuelNecessaryForPositionI.add(fuelNecessaryForThatCrab);
			}

			fuelForEachPosition.add(fuelNecessaryForPositionI);
			//System.out.println("Fuel for position " + i + ":" + fuelNecessaryForPositionI);
		}
		Collections.sort(fuelForEachPosition);

		return fuelForEachPosition.get(0);
	}

	public BigInteger factorial(BigInteger n) {
		if (n.intValue() <= 2) {
			if (n.intValue() <= 1) {
				return n;
			} else if (n.intValue() == 2) {
				return n.add(BigInteger.ONE);
			}
		}
		return n.add(factorial(n.subtract(BigInteger.ONE)));
	}

	public static void main(String[] args) throws IOException {
		December7th obj = new December7th();
		InputReader reader = new InputReader();
		String[] inputs = reader.readLine(December7th.class, "input7.txt");
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(Arrays.asList(inputs)));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(Arrays.asList(inputs)));
	}

	public List<Integer> getListAsInt(List<String> inputs) {
		return inputs.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
	}
}

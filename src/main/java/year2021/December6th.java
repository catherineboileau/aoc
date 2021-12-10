package year2021;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.InputReader;

public class December6th {

	private static final int ITERATIONS_1 = 80;
	private static final int ITERATIONS_2 = 256;

	public Integer getAnswerPart1(List<String> inputs) {
		List<LanternFish> lanternfishes = new ArrayList<>();
		for (String input : inputs) {
			lanternfishes.add(new LanternFish(Integer.valueOf(input)));
		}

		for (int i = 0; i < ITERATIONS_1; i++) {
			List<LanternFish> spawns = new ArrayList<>();
			for (LanternFish fish : lanternfishes) {
				fish.liveAnotherDay();
				if (fish.isReadyForSpawn()) {
					spawns.add(new LanternFish());
					fish.resetCountDownUntilSpawn();
				}
			}

			lanternfishes.addAll(spawns);
		}
		return lanternfishes.size();

	}

	public String getAnswerPart2(List<String> inputs) {
		BigInteger[] lanternfishes = { BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO };
		
		for (String input : inputs) {
			
			BigInteger age = BigInteger.valueOf(Integer.valueOf(input));
			int valueOfAge = age.intValue();

			lanternfishes[valueOfAge] = lanternfishes[valueOfAge].add(BigInteger.ONE);
		}

		for (int i = 0; i < ITERATIONS_2; i++) {
			BigInteger tempCountOfZero = lanternfishes[0];
			for (int j = 0; j < 8; j++) {
				if (j == 0) {
					
				}
				lanternfishes[j] = lanternfishes[j+1];				
			}
			
			lanternfishes[6] = lanternfishes[6].add(tempCountOfZero);
			lanternfishes[8] = tempCountOfZero;
		}
		
		BigInteger somme = BigInteger.ZERO;
		
		for (int i = 0; i < 9; i++) {
			somme = somme.add(lanternfishes[i]);
		}

		return somme.toString();
	}
	
	public String arrayToString(BigInteger[] array) {
		String string = "";
		for (int i = 0; i < 9; i++) {
			string = string + "" + array[i].toString()+",";
		}
		
		return string;
	}

	public static void main(String[] args) throws IOException {
		December6th obj = new December6th();
		InputReader reader = new InputReader();
		String[] inputs = reader.readLine(December6th.class, "input6.txt");
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(Arrays.asList(inputs)));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(Arrays.asList(inputs)));
	}

	public class LanternFish {

		private int countdownUntilSpawn;

		public LanternFish() {
			this.countdownUntilSpawn = 8;
		}

		public LanternFish(int life) {
			this.countdownUntilSpawn = life;
		}

		public boolean isReadyForSpawn() {
			return this.countdownUntilSpawn == -1;
		}

		public void liveAnotherDay() {
			this.countdownUntilSpawn -= 1;
		}

		public void resetCountDownUntilSpawn() {
			this.countdownUntilSpawn = 6;
		}

		public String toString() {
			return "" + countdownUntilSpawn;
		}
	}
}

package year2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import common.InputReader;

public class December4th {

	private int finalDraw = -1;
	private Card winningCard;

	public Integer getAnswerPart1(String[] numbers, List<String> inputs) {
		List<Card> cards = initCards(inputs);
		System.out.println("Number of cards: " + cards.size());
		List<Integer> draws = Arrays.asList(numbers).stream().map(number -> Integer.valueOf(number))
				.collect(Collectors.toList());

		return getBingoLine(cards, draws);

	}

	public Integer getAnswerPart2(String[] numbers, List<String> inputs) {
		List<Card> cards = initCards(inputs);
		System.out.println("Number of cards: " + cards.size());
		List<Integer> draws = Arrays.asList(numbers).stream().map(number -> Integer.valueOf(number))
				.collect(Collectors.toList());

		while (cards.size() > 1) {
			getBingoLine(cards, draws);
			cards.remove(this.winningCard);
		}
		
		getBingoLine(cards, draws);
		
		Card loosingCard = cards.get(0);
		return loosingCard.getSumOfUnmarked() * finalDraw;
	}

	public List<Card> initCards(List<String> inputs) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < inputs.size(); i = i + 5) {
			Card card = new Card();
			card.initRow(inputs.get(i), i % 5);
			card.initRow(inputs.get(i + 1), (i + 1) % 5);
			card.initRow(inputs.get(i + 2), (i + 2) % 5);
			card.initRow(inputs.get(i + 3), (i + 3) % 5);
			card.initRow(inputs.get(i + 4), (i + 4) % 5);
			cards.add(card);
		}

		return cards;
	}

	public int getBingoLine(List<Card> cards, List<Integer> draws) {
		for (Integer draw : draws) {
			for (Card card : cards) {
				card.markNumberToDraw(draw);
				if (card.isWinningCardWithRow() || card.isWinningCardWithColumn()) {
					int sum = this.winningCard.getSumOfUnmarked();
					this.finalDraw = draw;
					return sum * draw;
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		December4th obj = new December4th();
		InputReader reader = new InputReader();
		String[] numbers = reader.readLine(December4th.class, "input4.txt");
		List<String> inputs = reader.readBingoNumber(December4th.class, "input4.txt");
		;
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(numbers, inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(numbers, inputs));
	}

	public class Card {
		Number[][] card = new Number[5][5];

		public void initRow(String row, int rowNumber) {
			String[] rowSplitted = row.replace("  ", " ").trim().split(" ");

			card[rowNumber][0] = new Number(Integer.valueOf(rowSplitted[0]), false);
			card[rowNumber][1] = new Number(Integer.valueOf(rowSplitted[1]), false);
			card[rowNumber][2] = new Number(Integer.valueOf(rowSplitted[2]), false);
			card[rowNumber][3] = new Number(Integer.valueOf(rowSplitted[3]), false);
			card[rowNumber][4] = new Number(Integer.valueOf(rowSplitted[4]), false);
		}

		public int getSumOfUnmarked() {
			int sum = 0;
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (!card[i][j].draw) {
						sum += card[i][j].number;
					}
				}
			}
			
			return sum;
		}

		public void markNumberToDraw(int number) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (card[i][j].number == number) {
						card[i][j].draw = true;
					}
				}
			}
		}

		public boolean isWinningCardWithRow() {
			for (int i = 0; i < 5; i++) {
				if (card[i][0].draw && card[i][1].draw && card[i][2].draw && card[i][3].draw && card[i][4].draw) {					
					December4th.this.winningCard = this;
					return true;
				}
			}

			return false;
		}

		public boolean isWinningCardWithColumn() {
			for (int i = 0; i < 5; i++) {
				if (card[0][i].draw && card[1][i].draw && card[2][i].draw && card[3][i].draw && card[4][i].draw) {
					December4th.this.winningCard = this;
					return true;
				}
			}

			return false;
		}

		public String toString() {
			String s = "";
			for (int i = 0; i < 5; i++) {
				s = s + "\nRow " + i + ": " + card[i][0].number + " " + card[i][1].number + " " + card[i][2].number
						+ " " + card[i][3].number + " " + card[i][4].number + "\n";
			}

			return s;
		}
	}

	public class Number {
		public int number;
		public boolean draw;

		public Number(int number, boolean draw) {
			this.number = number;
			this.draw = draw;
		}

		public String toString() {
			return " " + number + "" + draw;
		}
	}
}

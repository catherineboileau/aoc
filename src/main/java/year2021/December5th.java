package year2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.InputReader;

public class December5th {

	public Integer getAnswerPart1(List<String> inputs) {

		List<Line> lines = new ArrayList<>();
		OceanFloor floor = new OceanFloor();

		for (String input : inputs) {
			String[] split = input.split(" -> ");
			String[] firstPair = split[0].split(",");
			String[] secondPair = split[1].split(",");

			lines.add(new Line(Integer.valueOf(firstPair[0]), Integer.valueOf(firstPair[1]),
					Integer.valueOf(secondPair[0]), Integer.valueOf(secondPair[1])));
		}

		for (Line line : lines) {
			if (line.isLine()) {
				floor.markLine(line);
			}
		}

		return floor.countIntersection();

	}

	public Integer getAnswerPart2(List<String> inputs) {
		List<Line> lines = new ArrayList<>();
		OceanFloor floor = new OceanFloor();

		for (String input : inputs) {
			String[] split = input.split(" -> ");
			String[] firstPair = split[0].split(",");
			String[] secondPair = split[1].split(",");

			lines.add(new Line(Integer.valueOf(firstPair[0]), Integer.valueOf(firstPair[1]),
					Integer.valueOf(secondPair[0]), Integer.valueOf(secondPair[1])));
		}

		for (Line line : lines) {
			if (line.isLine()) {
				floor.markLine(line);
			} else {
				if (line.isDiagonale()) {
					floor.markDiag(line);
				}
			}
		}

		return floor.countIntersection();
	}

	public static void main(String[] args) throws IOException {
		December5th obj = new December5th();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December5th.class, "input5.txt");
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}

	public class OceanFloor {

		public static final int SIZE = 1000;

		private int[][] floor = new int[SIZE][SIZE];

		public OceanFloor() {
			init();
		}

		public void init() {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					floor[i][j] = 0;
				}
			}
		}

		public void markLine(Line l) {
			if (l.x1 == l.x2) {
				for (int i = Math.min(l.y1, l.y2); i <= Math.max(l.y1, l.y2); i++) {
					floor[i][l.x1] += 1;
				}
			}

			if (l.y1 == l.y2) {
				for (int i = Math.min(l.x1, l.x2); i <= Math.max(l.x1, l.x2); i++) {
					floor[l.y1][i] += 1;
				}
			}
		}

		public void markDiag(Line l) {
			for (int i = 0; i < l.numberOfPointsInDiag() + 1; i++) {

				int x = -1;
				int y = -1;

				if (l.x1 < l.x2) { // on additionne les x
					if (l.y1 < l.y2) { // on additionne les y
						y = l.x1 + i;
						x = l.y1 + i;
					} else { // on soustrais les y
						y = l.x1 + i;
						x = l.y1 - i;
					}
				} else { // on soustrait les x
					if (l.y1 < l.y2) { // on additionne les y
						y = l.x1 - i;
						x = l.y1 + i;
					} else { // on soustrais les y
						y = l.x1 - i;
						x = l.y1 - i;
					}
				}
				
				this.floor[x][y] += 1;
			}
		}

		public int countIntersection() {
			int count = 0;
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (floor[i][j] > 1) {
						count += 1;
					}
				}
			}

			return count;
		}

		public String toString() {
			String s = "";
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					int count = floor[i][j];
					if (count == 0) {
						s = s + " .";
					} else {
						s = s + " " + floor[i][j];
					}
				}
				s = s + "\n";
			}

			return s;
		}
	}

	public class Line {
		public int x1;
		public int y1;
		public int x2;
		public int y2;

		public Line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public int numberOfPointsInDiag() {
			return Math.abs(x1 - x2);
		}

		public boolean isDiagonale() {
			int dx = Math.abs(x1 - x2);
			int dy = Math.abs(y1 - y2);

			return dx == dy;
		}

		public boolean isLine() {
			return (x1 == x2 || y1 == y2);
		}

		public String toString() {
			return "(" + x1 + "," + y1 + ")->(" + x2 + "," + y2 + ")";
		}
	}
}

package year2017;

import java.io.IOException;

public class December3rd {

	public static final int INPUT = 347991;

	public int firstStar(int input) {
		int lenghtGrid = getNearestIntSquareRoot(input);
		int diff = getWidthOfGrid(lenghtGrid) - input;
		int xPosition = lenghtGrid - diff - 1;
		int yPosition = lenghtGrid;
		
		int xPositionOne = Math.ceilDiv(yPosition, 2);		
		int absoluteX = Math.abs(xPosition - xPositionOne);
		int absoluteY = Math.abs(yPosition - xPositionOne);		
		
		int manhattanDistance =  absoluteX + absoluteY;
		
		return manhattanDistance;
	}

	public int secondStar() {
		return 1;
	}
	
	private int getNearestIntSquareRoot(int input) {
		return (int) Math.ceil(Math.sqrt(input));
	}
	
	private int getWidthOfGrid(int lenght) {
		return (int) Math.pow(lenght, 2);
	}

	public static void main(String[] args) throws IOException {
		December3rd obj = new December3rd();

		//System.out.println("Count 1(test1): " + obj.firstStar(1));
		//System.out.println("Count 1(test2): " + obj.firstStar(12));
		//System.out.println("Count 1(test3): " + obj.firstStar(23));
		System.out.println("Count 1: " + obj.firstStar(347991));
		System.out.println("Count 1: " + obj.firstStar(289326));

		System.out.println("Count 1: " + obj.firstStar(325489));

		//System.out.println("Count 1: " + obj.firstStar(1024));
		//System.out.println("Count 2: " + obj.secondStar());
	}

}

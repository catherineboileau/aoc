package year2019;

import java.io.IOException;

public class December4th {
	
	public static final Integer beginningRange = 357253;
	public static final Integer endRange = 892942;
	
	/**
	 * December 4th, Part 1
	 */
	public int getNumbersOfPasswordMatchingCriteria() {
		int count = 0;
		
		for(int i=beginningRange; i<= endRange; i++) {
			String integerAsString = Integer.valueOf(i).toString();
			if (containsDoubleDigitsButOnlyDouble(integerAsString) && !doesDigitDecrease(integerAsString)) {
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * December 4th, Part 2
	 */
	public int getNumbersOfPasswordMatchingCriteriaPart2() {
		int count = 0;
		
		for(int i=beginningRange; i<= endRange; i++) {
			String integerAsString = Integer.valueOf(i).toString();
			if (containsDoubleDigits(integerAsString) && !doesDigitDecrease(integerAsString)) {
				count++;
			}
		}
		
		return count;
	}
	
	private boolean containsDoubleDigits(String digit) {
		if (digit.contains("00") && !digit.contains("000")) {
			return true;
		}
		
		if (digit.contains("11") && !digit.contains("111")) {
			return true;
		}

		if (digit.contains("22") && !digit.contains("222")) {
			return true;
		}

		if (digit.contains("33") && !digit.contains("333")) {
			return true;
		}

		if (digit.contains("44") && !digit.contains("444")) {
			return true;
		}

		if (digit.contains("55") && !digit.contains("555")) {
			return true;
		}

		if (digit.contains("66") && !digit.contains("666")) {
			return true;
		}

		if (digit.contains("77") && !digit.contains("777")) {
			return true;
		}

		if (digit.contains("88") && !digit.contains("888")) {
			return true;
		}

		if (digit.contains("99") && !digit.contains("999")) {
			return true;
		}
		
		return false;
	}
	
	private boolean containsDoubleDigitsButOnlyDouble(String digit) {
		if (digit.contains("00")) {
			return true;
		}
		
		if (digit.contains("11")) {
			return true;
		}

		if (digit.contains("22")) {
			return true;
		}

		if (digit.contains("33")) {
			return true;
		}

		if (digit.contains("44")) {
			return true;
		}

		if (digit.contains("55")) {
			return true;
		}

		if (digit.contains("66")) {
			return true;
		}

		if (digit.contains("77")) {
			return true;
		}

		if (digit.contains("88")) {
			return true;
		}

		if (digit.contains("99")) {
			return true;
		}
		
		return false;
	}
	
	private boolean doesDigitDecrease(String digit) {
		int digit1 = Integer.valueOf(digit.substring(0,1));
		int digit2 = Integer.valueOf(digit.substring(1,2));
		int digit3 = Integer.valueOf(digit.substring(2,3));
		int digit4 = Integer.valueOf(digit.substring(3,4));
		int digit5 = Integer.valueOf(digit.substring(4,5));
		int digit6 = Integer.valueOf(digit.substring(5,6));
		
		if ((digit1 <= digit2) && (digit2 <= digit3) && (digit3 <= digit4) && (digit4 <= digit5) && (digit5 <= digit6)) {
			return false;
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		December4th obj = new December4th();
		System.out.println("Numbers of Password: " + obj.getNumbersOfPasswordMatchingCriteria());
		System.out.println("Numbers of Password (2): " + obj.getNumbersOfPasswordMatchingCriteriaPart2());
	}
}

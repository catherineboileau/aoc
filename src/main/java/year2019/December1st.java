package year2019;

import java.io.IOException;
import java.util.List;

import common.InputReader;

public class December1st {

	/**
	 * December 1st, 2019
	 */
	public Long computeFuelRequirement(List<String> inputs) {
		Long fuelRequired = 0L;
		for (String input : inputs) {
			Long mass = Long.valueOf(input);
			Long fuel = Math.floorDiv(mass, 3) - 2L;
			fuelRequired = fuelRequired + fuel;
		}

		return fuelRequired;
	}

	/**
	 * December 1st, 2019, Part 2
	 */
	public Long computeFuelRequirementWithFuelForFuel(List<String> inputs) {
		Long fuelRequired = 0L;
		for (String input : inputs) {
			Long mass = Long.valueOf(input);
			Long fuel = computeRecursiveFuel(mass);
			fuelRequired = fuelRequired + fuel;
		}

		return fuelRequired;
	}

	private Long computeRecursiveFuel(Long mass) {
		Long fuelRequired = Math.floorDiv(mass, 3) - 2L;

		if (fuelRequired > 0) {
			return fuelRequired + computeRecursiveFuel(fuelRequired);
		}

		return 0L;
	}

	public static void main(String[] args) throws IOException {
		December1st obj = new December1st();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December1st.class, "input1.txt");
		System.out.println("Fuel required: " + obj.computeFuelRequirement(inputs));
		System.out.println("Fuel required (with Fuel for Fuel): " + obj.computeFuelRequirementWithFuelForFuel(inputs));
	}
}

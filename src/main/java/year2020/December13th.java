package year2020;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.InputReader;

public class December13th {

	private double departingTime = 0.0;
	private Integer maxBusId = 0;
	private Integer indexOfMaxBusId = 0;
	private String[] allBuses;
	private List<Integer> tMinus;
	private List<Integer> buses;

	public December13th(List<String> inputs) {
		this.allBuses = inputs.get(1).split(",");
		this.departingTime = Double.valueOf(inputs.get(0));
		this.buses = new ArrayList<>(allBuses.length);
		this.tMinus = new ArrayList<>(allBuses.length);

		for (int i = 0; i < allBuses.length; i++) {
			if (!allBuses[i].equals("x")) {
				buses.add(Integer.valueOf(allBuses[i]));
				tMinus.add(i);
			}
		}
	}

	public December13th(List<String> inputs, boolean part2) {
		String[] allValidBuses = inputs.get(1).split(",");
		this.buses = new ArrayList<>(allValidBuses.length);
		this.tMinus = new ArrayList<>(allValidBuses.length);

		for (int i = 0; i < allValidBuses.length; i++) {
			if (!allValidBuses[i].equals("x")) {
				buses.add(Integer.valueOf(allValidBuses[i]));
				tMinus.add(Integer.valueOf(i));
			}
		}
	}

	public int part1() {
		for (int j = 0; j < buses.size(); j++) {
			int busId = buses.get(j);
			int earliestDepartingTime = (int) Math.ceil(departingTime / busId) * busId;
			tMinus.set(j, earliestDepartingTime);
		}

		int min = Collections.min(tMinus);
		int busId = buses.get(tMinus.indexOf(min));
		int numberOfMins = min - ((int) departingTime);
		return busId * numberOfMins;
	}

	/** Way toooo long!! */
	public BigInteger part2() {
		this.maxBusId = Collections.max(buses);
		this.indexOfMaxBusId = buses.indexOf(maxBusId);
		BigInteger departingTimeOfMaxBus = BigInteger.valueOf(tMinus.get(indexOfMaxBusId));
		BigInteger maxBusIdBig = BigInteger.valueOf(maxBusId);
		BigInteger earliestVablue = new BigInteger("100000000000000");

		for (BigInteger i = earliestVablue; i.compareTo(BigInteger.ZERO) >= 0; i = i.add(maxBusIdBig)) {
			BigInteger timeZero = i.subtract(departingTimeOfMaxBus);
			if (isTimestampGood(timeZero)) {
				return timeZero;
			}
		}

		return BigInteger.ZERO;
	}

	public boolean isTimestampGood(BigInteger timeZero) {
		for (int j = 0; j < buses.size(); j++) {
			BigInteger busTrajet = BigInteger.valueOf(buses.get(j));
			BigInteger timeZeroForThisBus = timeZero.add(BigInteger.valueOf(tMinus.get(j)));
			int compare = timeZeroForThisBus.mod(busTrajet).compareTo(BigInteger.ZERO);
			if (compare != 0) {
				return false;
			}
		}

		return true;
	}
	
	/** I asked an algorithm master for help!! */
	public BigInteger part2B() {
		BigInteger i = BigInteger.ZERO;
		List<ServiceOffsetPair> services = new ArrayList<>();

		for (String s : allBuses) {
			if (!s.equals("x")) {
				ServiceOffsetPair pair = new ServiceOffsetPair(new BigInteger(s), i);
				services.add(pair);

				System.out.println("Service " + pair.id + ", offset " + pair.offset);
			}
			i = i.add(BigInteger.ONE);
		}

		BigInteger step = services.get(0).id;
		BigInteger timestamp = services.get(0).id;
		services.remove(0);

		for (ServiceOffsetPair pair : services) {
			while ((timestamp.add(pair.offset)).mod(pair.id) != BigInteger.ZERO) {
				timestamp = timestamp.add(step);
			}
			System.out.println("Timestamp " + timestamp + " is valid for service " + pair.id);
			step = step.multiply(pair.id);
		}

		return timestamp;
	}

	public class ServiceOffsetPair {
		public BigInteger id;
		public BigInteger offset;
	
		public ServiceOffsetPair(BigInteger id, BigInteger offset) {
			this.id = id;
			this.offset = offset;
		}
	}

	public static void main(String[] args) throws IOException {
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December13th.class, "input13.txt");

		December13th part1 = new December13th(inputs);
		System.out.println("Part 1:" + part1.part1());

		December13th part2 = new December13th(inputs);
		System.out.println("Part 2:" + part2.part2B());
	}
}

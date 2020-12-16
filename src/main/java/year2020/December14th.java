package year2020;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.InputReader;

public class December14th {
	
	public static final String BINARY_ZERO_LEN_32 = "000000000000000000000000000000000000";

	private List<String> inputs;
	private String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

	public December14th(List<String> inputs) {
		this.inputs = inputs;
	}
	
    public long part1() {
        HashMap<String, String> memory = new HashMap<>();
        for (String line : inputs) {
            if (line.startsWith("mask = ")) {
                mask = line.replace("mask = ", "");
            } else {
                String[] memoryValue = line.split(" = ");
                String memoryAddress = memoryValue[0].replace("mem[", "").replace("]", "");
                String binaryNumber = Integer.toBinaryString(Integer.parseInt(memoryValue[1]));
                String padded = BINARY_ZERO_LEN_32.substring(binaryNumber.length()) + binaryNumber;
                String bindryWithMaskApplies = applyMaskPart1(padded, mask);
                memory.put(memoryAddress, bindryWithMaskApplies);
            }
        }
        
        return memory.keySet()
        		.stream()
        		.mapToLong(key -> Long.parseLong(memory.get(key), 2))
        		.sum();
    }

    private static String applyMaskPart1(String padded, String mask) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= mask.length() - 1; i++) {
            if (mask.charAt(i) == 'X') {
                result.append(padded.charAt(i));
            } else {
                result.append(mask.charAt(i));
            }
        }
        return result.toString();
    }

    public long part2() {
        HashMap<String, String> memory = new HashMap<>();
        String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        for (String line : inputs) {
            if (line.startsWith("mask = ")) {
                mask = line.replace("mask = ", "");
            } else {
                String[] memoryValue = line.split(" = ");
                String memoryAddress = memoryValue[0].replace("mem[", "").replace("]", "");
                String binaryMemoryAddress = Integer.toBinaryString(Integer.parseInt(memoryAddress));
                String paddedMemoryAddress = BINARY_ZERO_LEN_32.substring(binaryMemoryAddress.length()) + binaryMemoryAddress;
                String memoryAddressesMaskApplied = applyMaskPart2(paddedMemoryAddress, mask);
                Set<String> memoryAddresses = getPossibleVariations(memoryAddressesMaskApplied);
                String binaryNumber = Integer.toBinaryString(Integer.parseInt(memoryValue[1]));
                String padded = BINARY_ZERO_LEN_32.substring(binaryNumber.length()) + binaryNumber;
                for (String address : memoryAddresses) {
                    memory.put(address, padded);
                }
            }
        }
        return memory.keySet().stream().mapToLong(key -> Long.parseLong(memory.get(key), 2)).sum();
    }

    private String applyMaskPart2(String padded, String mask) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= mask.length() - 1; i++) {
            if (mask.charAt(i) == 'X') {
                result.append('X');
            } else if (mask.charAt(i) == '1') {
                result.append(1);
            } else {
                result.append(padded.charAt(i));
            }
        }
        return result.toString();
    }

    private Set<String> getPossibleVariations(String binaryString) {
        Set<String> result = new HashSet<>();
        result.add(binaryString);
        while (true) {
            Set<String> stringToAddToResult = new HashSet<>();
            Set<String> stringToRemove = new HashSet<>();
            for (String stringToProcess : result) {
                if (stringToProcess.contains("X")) {
                    stringToRemove.add(stringToProcess);
                    stringToAddToResult.add(stringToProcess.replaceFirst("X", "1"));
                    stringToAddToResult.add(stringToProcess.replaceFirst("X", "0"));
                    break;
                }
            }
            result.addAll(stringToAddToResult);
            result.removeAll(stringToRemove);
            int numberOfResultsWithOutX = 0;
            for (String string : result) {
                if (!string.contains("X")) {
                    numberOfResultsWithOutX++;
                }
            }
            if (numberOfResultsWithOutX == result.size()) {
                break;
            }
        }
        return result;
    }

	public static void main(String[] args) throws IOException {
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December14th.class, "input14.txt");

		December14th part1 = new December14th(inputs);
		System.out.println("Part 1:" + part1.part1());

		December14th part2 = new December14th(inputs);
		System.out.println("Part 2:" + part2.part2());
	}
}

package year2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class December15th {

	private String[] inputs;
	private List<Integer> memory = new ArrayList<>();
	private Map<Integer, Position> memory2 = new HashMap<>();
	private Integer lastNumber = 0;

	public December15th(String[] inputs) {
		this.inputs = inputs;
		for (int i = 0; i<inputs.length; i++) {
			memory.add(Integer.valueOf(inputs[i]));
			memory2.put(Integer.valueOf(inputs[i]), new Position(i, -1));
		}
		lastNumber = Integer.valueOf(inputs[inputs.length-1]);
	}

    public long part1And2(int numberOfTurns) {
    	for (int i = inputs.length-1; i < numberOfTurns; i++) {    		
    		Position lastPosition = memory2.get(lastNumber);
    		if (lastPosition.positionBeforeLast == -1) {
    			lastNumber = 0;
    			Position position = memory2.get(lastNumber);
    			memory2.put(lastNumber, new Position(i + 1, position == null ? -1 : position.lastPosition));
    		} else {
    			int nextNumber = lastPosition.lastPosition - lastPosition.positionBeforeLast;
    			lastNumber = nextNumber;
    			Position position = memory2.get(lastNumber);
    			memory2.put(lastNumber, new Position(i + 1, position == null ? -1 : position.lastPosition));
    		}    		
    	}
    	
    	return lastNumber;
    }

    public class Position {
    	int lastPosition;
    	int positionBeforeLast;
    	
    	public Position(int last, int beforeLast) {
    		this.lastPosition = last;
    		this.positionBeforeLast = beforeLast;    				
    	}
    	
    	public String toString() {
    		return "Last: "+lastPosition+", Before: "+positionBeforeLast;
    	}
    }
    
	public static void main(String[] args) throws IOException {
		String[] inputs = "2,0,6,12,1,3".split(",");

		December15th part1 = new December15th(inputs);
		System.out.println("Part 1:" + part1.part1And2(2019));

		December15th part2 = new December15th(inputs);
		System.out.println("Part 2:" + part2.part1And2(29999999));
	}
}

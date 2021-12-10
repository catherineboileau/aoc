package year2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.InputReader;

public class December9th {

	public Integer getAnswerPart1(List<String> inputs) {
		OceanFloor floor = new OceanFloor(inputs.size(), inputs.get(0).length());
		floor.initFloor(inputs);		
		floor.markLowestPoints();
		return floor.getRisk();
	}

	public int getAnswerPart2(List<String> inputs) {
		OceanFloor floor = new OceanFloor(inputs.size(), inputs.get(0).length());
		floor.initFloor(inputs);		
		floor.markLowestPoints();
		
		List<OceanPoint> lowestPoints = floor.getLowest();
		List<Basin> basins = new ArrayList<>();
		
		for (OceanPoint lowest: lowestPoints) {
			List<OceanPoint> basin = floor.getBasin(lowest);
			basins.add(new Basin(basin));
		}
		
		Collections.sort(basins);
		
		int largestBasinSize = basins.get(basins.size() -1).getSize();
		int secondLargestBasinSize = basins.get(basins.size() -2).getSize();
		int thirdLargestBasinSize = basins.get(basins.size() -3).getSize();
		
		return largestBasinSize * secondLargestBasinSize * thirdLargestBasinSize;
	}

	public static void main(String[] args) throws IOException {
		December9th obj = new December9th();
		InputReader reader = new InputReader();
		List<String> inputs = reader.read(December9th.class, "input9.txt");
		System.out.println("Answer Part 1: " + obj.getAnswerPart1(inputs));
		System.out.println("Answer Part 2: " + obj.getAnswerPart2(inputs));
	}

	public class OceanFloor {
		private OceanPoint[][] floor;
		private List<OceanPoint> markedAsLowest;
		private int numberOfItemsInRow = 0;
		private int numberOfRows = 0;

		public OceanFloor(int numberOfItemsInRow, int numberOfRows) {
			this.numberOfItemsInRow = numberOfItemsInRow;
			this.numberOfRows = numberOfRows;
			floor = new OceanPoint[numberOfItemsInRow][numberOfRows];
			markedAsLowest = new ArrayList<>();
		}
		
		public List<OceanPoint> getLowest() {
			return this.markedAsLowest;
		}
		
		public int getRisk() {
			int risk = 0;
			
			for(OceanPoint point : markedAsLowest) {
				risk = risk + point.value + 1;
			}
			
			return risk;
		}

		public void initFloor(List<String> inputs) {
			for (int i = 0; i < inputs.size(); i++) {
				String row = inputs.get(i);
				for (int j = 0; j < row.length(); j++) {
					int value = Integer.valueOf(row.substring(j, j + 1));
					this.floor[i][j] = new OceanPoint(value, i, j);
				}
			}
		}	
		
		public void markLowestPoints() {
			for(int i=0;i<numberOfItemsInRow;i++) {
				for (int j=0;j<numberOfRows;j++) {
					markOnePoint(i, j);					
				}
			}
		}
		
		public void markOnePoint(int i, int j) {
			List<OceanPoint> neighbors = new ArrayList<>();
		
			OceanPoint current = floor[i][j];
			OceanPoint north = null;
			OceanPoint south = null;
			OceanPoint east = null;
			OceanPoint west = null;
			try { north = floor[i][j-1]; } catch (ArrayIndexOutOfBoundsException e) {}
			try { south = floor[i][j+1];} catch (ArrayIndexOutOfBoundsException e) {}
			try { east = floor[i-1][j];} catch (ArrayIndexOutOfBoundsException e) {}
			try { west = floor[i+1][j];} catch (ArrayIndexOutOfBoundsException e) {}
			
			if (north != null) {neighbors.add(north); }
			if (south != null) {neighbors.add(south); }
			if (east != null) {neighbors.add(east); }
			if (west != null) {neighbors.add(west); }
			neighbors.add(current);
			
			Collections.sort(neighbors);
			
			if (current.equals(neighbors.get(0))) {
				this.markedAsLowest.add(current);
				current.setLowest();
			}
		}
		
		public List<OceanPoint> getBasin(OceanPoint current) {
			List<OceanPoint> neighbors = new ArrayList<>();
			int i = current.i;
			int j = current.j;
		
			OceanPoint north = null;
			OceanPoint south = null;
			OceanPoint east = null;
			OceanPoint west = null;
			try { north = floor[i][j-1]; } catch (ArrayIndexOutOfBoundsException e) {}
			try { south = floor[i][j+1];} catch (ArrayIndexOutOfBoundsException e) {}
			try { east = floor[i-1][j];} catch (ArrayIndexOutOfBoundsException e) {}
			try { west = floor[i+1][j];} catch (ArrayIndexOutOfBoundsException e) {}
			
			if (north != null && !north.isVisited && north.isNotNine()) {
				north.setVisited();
				neighbors.add(north);
				neighbors.addAll(getBasin(north));
			}
			if (south != null && !south.isVisited && south.isNotNine()) {
				south.setVisited();
				neighbors.add(south);
				neighbors.addAll(getBasin(south));
			}
			if (east != null && !east.isVisited && east.isNotNine()) {
				east.setVisited();
				neighbors.add(east);
				neighbors.addAll(getBasin(east));
				
			}
			if (west != null && !west.isVisited && west.isNotNine()) {
				west.setVisited();
				neighbors.add(west);
				neighbors.addAll(getBasin(west));
			}
			
			return neighbors;			
		}
		
		public String toString() {
			String s = "";
			for(int i=0;i<numberOfItemsInRow;i++) {
				for (int j=0;j<numberOfRows;j++) {
					s = s + "" + floor[i][j].value;
				}
				s = s + "\n";
			}
			
			return s;
		}

	}
	
	public class Basin implements Comparable<Basin> {
		public List<OceanPoint> basin;
		
		public Basin(List<OceanPoint> basin) {
			this.basin = basin;
		}
		
		public int getSize() {
			return basin.size();
		}

		@Override
		public int compareTo(Basin o) {
			return Integer.compare(this.basin.size(), o.basin.size());
		}
		
		
	}

	public class OceanPoint implements Comparable<OceanPoint> {
		private int value;
		private int i;
		private int j;
		private boolean isLowest = false;
		private boolean isVisited = false;

		public OceanPoint(int value) {
			this.value = value;
		}

		public OceanPoint(int value, int i, int j) {
			this.value = value;
			this.i = i;
			this.j = j;
		}
		
		public void setLowest() {
			isLowest = true;
		}
		
		public void setVisited() {
			this.isVisited = true;
		}
		
		public boolean isNotNine() {
			return value != 9;
		}

		@Override
		public int compareTo(OceanPoint o) {			
			return Integer.compare(this.value, o.value);
		}
		
		public String toString() {
			return "("+value+","+isLowest+")";
		}
	}

}

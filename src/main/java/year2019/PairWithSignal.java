package year2019;

import org.javatuples.Pair;

public class PairWithSignal {
	
	Pair<Integer, Integer> pair;
	Integer signal;
	
	public PairWithSignal(Pair<Integer, Integer> pair, Integer signal) {
		this.pair = pair;
		this.signal = signal;
		//System.out.println(this.toString());
	}
	
	public boolean isOrigin() {
		return pair.getValue0() == 0 && pair.getValue1() == 0;
	}
	
	public boolean equals(Object obj) {
		PairWithSignal that = (PairWithSignal) obj;
		//System.out.println("This: " + this.pair.toString() + ", That: " + that.pair.toString());
		return this.pair.getValue0().equals(that.pair.getValue0()) && this.pair.getValue1().equals(that.pair.getValue1());
	}
	
	public String toString() {
		return "<" + pair.getValue0() + ","+ pair.getValue1() + ":" + signal + ">";
	}
}

package de.ftes.uon.seng2200.pa3b;



/**
 * Wraps a distance achieved by an athlete in a competition.
 * 
 * A foul attempt is encoded as distance of {@code 0}, an attempt not yet made
 * is encoded as {@link Double#MIN_VALUE}.
 * 
 * @author Fredrik Teschke (3228760)
 */
public class Distance extends Event<Distance> implements Comparable<Distance> {
	private float distance;

	public Distance(String athleteName, String country, int numberOfAttempt) {
		super(athleteName, country, numberOfAttempt);
		this.distance = Float.MIN_VALUE;
	}

	public Distance(String athleteName, String country, int numberOfAttempt, float distance) {
		super(athleteName, country, numberOfAttempt);
		setData(distance);
	}
	
	@Override
	public void setData(float value) {
		this.hasBeenMade = true;
		this.distance = value;
	}
	
	@Override
	public boolean isFoul() {
		return distance == 0;
	}

	@Override
	/**
	 * Order a greater distance before a lower one. An attempt not yet made is ordered before a foul attempt.
	 * @return {@code -1} if {@code this.distance > o.distance}, 0 if they are equal and 1 otherwise.
	 */
	public int compareTo(Distance other) {
		return -Double.compare(distance, other.distance);
	}
	
	@Override
	public boolean lessThan(Event<Distance> other) {
		return compareTo((Distance) other) < 0;
	}

	@Override
	public String toString() {
		if (! hasBeenMade) {
			return "   U   ";
		} else if (isFoul()) {
			return "   X   ";
		} else {
			return String.format("%7.2f", distance);
		}
	}
}

package de.ftes.uon.seng2200.pa3b;



/**
 * Wraps a distance achieved by an athlete in a competition.
 * 
 * A foul attempt is encoded as distance of {@code 0}, an attempt not yet made
 * is encoded as {@link Double#MIN_VALUE}, which is the smallest positive value.
 * 
 * @author Fredrik Teschke (3228760)
 */
public class DistanceAttempt implements Comparable<DistanceAttempt>, LessThanComparable<DistanceAttempt> {
	private float distance;
	/**
	 * Counter to retain the order in which an athlete made his attempts.
	 */
	protected final int numberOfAttempt;

	protected boolean hasBeenMade;

	/**
	 * Attempt has not been made.
	 */
	public DistanceAttempt(int numberOfAttempt) {
		this(numberOfAttempt, Float.MIN_VALUE, false);
	}

	/**
	 * Attempt has been made.
	 */
	public DistanceAttempt(int numberOfAttempt, float distance) {
		this(numberOfAttempt, distance, true);
	}

	private DistanceAttempt(int numberOfAttempt, float distance, boolean hasBeenMade) {
		this.numberOfAttempt = numberOfAttempt;
		this.hasBeenMade = hasBeenMade;
		this.distance = distance;
	}
	
	private boolean isFoul() {
		return distance == 0;
	}

	@Override
	/**
	 * Order a greater distance before a lower one. An attempt not yet made is ordered before a foul attempt.
	 */
	public int compareTo(DistanceAttempt other) {
		return -Float.compare(distance, other.distance);
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
	
	public int getNumberOfAttempt() {
		return numberOfAttempt;
	}

	@Override
	public boolean lessThan(DistanceAttempt other) {
		return compareTo(other) < 0;
	}
}

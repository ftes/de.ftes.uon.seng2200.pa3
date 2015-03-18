package de.ftes.uon.seng2200.pa1;

/**
 * A distance attempt wraps a distance achieved by an athlete in a competition.
 * Apart from the {@link #distance} value, it holds the {@link #numberOfAttempt number of the attempt}
 * that the athlete achieved this distance on during the competition.
 * @author Fredrik Teschke
 */
public class DistanceAttempt implements Comparable {
	private final int numberOfAttempt;
	private final double distance;

	public DistanceAttempt(int numberOfAttempt, double distance) {
		this.numberOfAttempt = numberOfAttempt;
		this.distance = distance;
	}

	public int getNumberOfAttempt() {
		return numberOfAttempt;
	}

	public double getDistance() {
		return distance;
	}

	@Override
	/**
	 * Order a greater distance before a lower one.
	 * @return {@code -1} if {@code this.distance > o.distance}, 0 if they are equal and 1 otherwise.
	 */
	public int compareTo(Object o) {
		if (!(o instanceof DistanceAttempt)) {
			throw new ClassCastException("Cannot cast " + o.getClass().getName() + " to " + DistanceAttempt.class.getName());
		}

		DistanceAttempt other = (DistanceAttempt) o;
		return - Double.compare(distance, other.distance);
	}
	
	@Override
	public String toString() {
		return distance + " (attempt " + numberOfAttempt + ")";
	}
}

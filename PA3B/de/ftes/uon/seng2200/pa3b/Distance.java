package de.ftes.uon.seng2200.pa3b;




/**
 * Holds all the attempts made by an athlete during a
 * competition along with some basic data about that athlete.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class Distance extends Event<Distance> implements Comparable<Distance> {
	private int numberOfAttemptsReceived = 0;
	
	/**
	 * Redundant list structure (output also needs chronological order).
	 */
	private final ArrayList<DistanceAttempt> distances = new ArrayListImpl<>();

	/**
	 * at what index in input file athlete appears
	 */
	private final int index;

	public Distance(String athleteName, String country, int index,
			int numberOfAttempts) {
		super(athleteName, country);
		this.index = index;

		for (int i = 0; i < numberOfAttempts; i++) {
			distances.append(new DistanceAttempt(i));
		}
	}

	public SortedList<DistanceAttempt> getSortedDistances() {
		return new SortedListImpl<>(distances);
	}

	/**
	 * Get the {@code n}-th best {@link DistanceAttempt} of the athlete.
	 */
	public DistanceAttempt getNthBestAttempt(int n) {
		return getSortedDistances().get(n);
	}

	@Override
	/**
	 * Compare the attempts made by two athletes according to spec.
	 */
	public int compareTo(Distance other) {
		// One athlete is ahead of another if his/her best attempt is further
		// than the other athlete’s best attempt.
		// If these are equal, then their second-best attempts are compared, and
		// if these are equal then their third-best
		// attempts, etc.
		for (int i = 0; i < distances.size(); i++) {
			int comparison = getNthBestAttempt(i).compareTo(
					other.getNthBestAttempt(i));
			if (comparison != 0) {
				return comparison;
			}
		}

		// If the two athletes are still considered equal then it is the athlete
		// who made a particular distance earlier in the competition that takes
		// precedence.
		for (int i = 0; i < distances.size(); i++) {
			int comparison = Integer.compare(getNthBestAttempt(i)
					.getNumberOfAttempt(), other.getNthBestAttempt(i)
					.getNumberOfAttempt());
			if (comparison != 0) {
				return comparison;
			}
		}

		// if two athletes are still equal after all checking has been made then
		// the athlete whose name is entered
		// first in the data file takes precedence.
		return Integer.compare(index, other.index);
	}

	/**
	 * Add a distance to the list of attempts.
	 * 
	 * @param distance
	 */
	@Override
	public void setData(float distance) {
		int numberOfAttempt = numberOfAttemptsReceived++;
		DistanceAttempt distanceAttempt = new DistanceAttempt(numberOfAttempt, distance);
		distances.put(numberOfAttempt, distanceAttempt);
	}

	@Override
	/**
	 * Formatted according to the spec.
	 */
	public String toString() {
		String distancesString = "";
		for (DistanceAttempt d : distances) {
			distancesString += d;
		}
		return String.format("%-15.15s %s%s", athleteName, country,
				distancesString);
	}

	public String getAthleteName() {
		return athleteName;
	}

	@Override
	public boolean lessThan(Distance other) {
		return compareTo(other) < 0;
	}
}

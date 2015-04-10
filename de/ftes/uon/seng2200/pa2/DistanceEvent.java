package de.ftes.uon.seng2200.pa2;

import de.ftes.uon.seng2200.list.ArrayList;
import de.ftes.uon.seng2200.list.SortedList;
import de.ftes.uon.seng2200.list.impl.ArrayListImpl;
import de.ftes.uon.seng2200.list.impl.SortedListImpl;

/**
 * A distance event holds all the attempts made by an athlete during a
 * competition along with some basic data about that athlete.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class DistanceEvent implements Comparable<DistanceEvent> {
	/**
	 * Redundant list structure (output also needs chronological order).
	 */
	private final ArrayList<DistanceAttempt> distances = new ArrayListImpl<>();

	/**
	 * always 3-letter code
	 */
	private final String country;

	private final String athleteName;

	private final int index;

	public DistanceEvent(String athleteName, String country, int index,
			int numberOfAttempts) {
		this.athleteName = athleteName;
		this.country = country;
		this.index = index;

		for (int i = 0; i < numberOfAttempts; i++) {
			distances.append(new DistanceAttempt(index));
		}
	}

	protected SortedList<DistanceAttempt> getSortedDistances() {
		return new SortedListImpl<>(distances);
	}

	/**
	 * Get the {@code n}-th best {@link DistanceAttempt} of the athlete.
	 */
	public DistanceAttempt getNthBestDistance(int n) {
		return getSortedDistances().get(n);
	}

	@Override
	/**
	 * Compare the attempts made by two athletes according to spec.
	 */
	public int compareTo(DistanceEvent other) {
		// One athlete is ahead of another if his/her best attempt is further
		// than the other athlete’s best attempt.
		// If these are equal, then their second-best attempts are compared, and
		// if these are equal then their third-best
		// attempts, etc.
		for (int i = 0; i < distances.size(); i++) {
			int comparison = getNthBestDistance(i).compareTo(
					other.getNthBestDistance(i));
			if (comparison != 0) {
				return comparison;
			}
		}

		// If the two athletes are still considered equal then it is the athlete
		// who made a particular distance earlier in the competition that takes
		// precedence.
		for (int i = 0; i < distances.size(); i++) {
			int comparison = Integer.compare(getNthBestDistance(i)
					.getNumberOfAttempt(), other.getNthBestDistance(i)
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
	public void setDistance(int numberOfAttempt, double distance) {
		DistanceAttempt distanceAttempt = new DistanceAttempt(numberOfAttempt,
				distance);
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
}

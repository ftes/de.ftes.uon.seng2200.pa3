package de.ftes.uon.seng2200.pa1;

import de.ftes.uon.seng2200.list.List;
import de.ftes.uon.seng2200.list.impl.ListImpl;
import de.ftes.uon.seng2200.list.impl.SortedListImpl;

/**
 * A distance event holds all the attempts made by an athlete during a competition along
 * with some basic data about that athlete.
 * @author Fredrik Teschke
 *
 */
public class DistanceEvent implements Comparable<DistanceEvent> {
	public static final int NUMBER_OF_ATTEMPTS = 6;

	/**
	 * 6 attempts, {@code 0} for foul attempt
	 */
	private final SortedListImpl<DistanceAttempt> sortedDistances = new SortedListImpl<>();

	/**
	 * Redundant list structure (output also needs chronological order).
	 */
	private final List<DistanceAttempt> distances = new ListImpl<>();

	/**
	 * always 3-letter code
	 */
	private final String country;

	private final String athleteName;

	private final int index;

	public DistanceEvent(String athleteName, String country, int index) {
		this.athleteName = athleteName;
		this.country = country;
		this.index = index;
	}

	/**
	 * Get the {@code n}-th best {@link DistanceAttempt} of the athlete.
	 */
	public DistanceAttempt getNthBestDistance(int n) {
		return sortedDistances.get(n);
	}

	@Override
	/**
	 * Compare the attempts made by two athletes according to spec.
	 */
	public int compareTo(DistanceEvent o) {
		DistanceEvent other = (DistanceEvent) o;
		
		//One athlete is ahead of another if his/her best attempt is further than the other athlete’s best attempt.
		//If these are equal, then their second-best attempts are compared, and if these are equal then their third-best
		//attempts, etc.
		for (int i = 0; i < distances.size(); i++) {
			int comparison = getNthBestDistance(i).compareTo(
					other.getNthBestDistance(i));
			if (comparison != 0) {
				return comparison;
			}
		}

		//If the two athletes are still considered equal then it is the athlete who made
		///a particular distance earlier in the competition that takes precedence.
		for (int i = 0; i < distances.size(); i++) {
			int comparison = Integer.compare(getNthBestDistance(i)
					.getNumberOfAttempt(), other.getNthBestDistance(i)
					.getNumberOfAttempt());
			if (comparison != 0) {
				return comparison;
			}
		}

		//if two athletes are still equal after all checking has been made then the athlete whose name is entered
		//first in the data file takes precedence.
		return Integer.compare(index, other.index);
	}

	/**
	 * Add a distance to the list of attempts.
	 * @param distance
	 */
	public void addDistance(double distance) {
		DistanceAttempt distanceAttempt = new DistanceAttempt(distances.size(),
				distance);
		sortedDistances.insertInOrder(distanceAttempt);
		distances.append(distanceAttempt);
	}

	@Override
	/**
	 * Formatted according to the spec.
	 */
	public String toString() {
		String distancesString = "";
		for (int i = 0; i < distances.size(); i++) {
			double d = ((DistanceAttempt) distances.get(i)).getDistance();
			distancesString += String.format("%7.2f", d);
		}
		return String.format("%-15.15s %s%s", athleteName, country,
				distancesString);
	}

	public String getAthleteName() {
		return athleteName;
	}
}

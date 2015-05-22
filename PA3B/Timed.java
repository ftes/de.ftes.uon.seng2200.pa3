

/**
 * Holds the time taken by an athlete during a competition along with some basic
 * data about that athlete.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class Timed extends Event<Timed> implements Comparable<Timed> {
	/**
	 * A foul attempt is recorded as {@link Double#MAX_VALUE} and an attempt not
	 * yet made are recorded as {@link Float#MAX_VALUE}. This allows simpler
	 * comparison.
	 */
	private double time = Float.MAX_VALUE;
	
	private boolean hasBeenMade = false;
	private boolean isFoul = false;

	/**
	 * at what index in input file athlete appears
	 */
	private final int index;

	public Timed(String athleteName, String country, int index, int numberOfAttempts) {
		super(athleteName, country);
		this.index = index;
	}

	@Override
	/** 
	 * Compare the attempts made by two athletes according to spec.
	 */
	public int compareTo(Timed other) {
		// One athlete is ahead of another if his/her time is lower
		// than the other athlete’s.
		int comparison = Double.compare(time, other.time);
		if (comparison != 0)
			return comparison;

		// If two athletes are still equal after all checking has been made then
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
	public void setData(float time) {
		hasBeenMade = true;
		// transform 0 for foul to Float#MAX_VALUE for easier comparison
		this.time = time;
		
		if (time == 0) {
			isFoul = true;
			this.time = Double.MAX_VALUE;
		}
	}
	
	private String getTimeString() {
		if (! hasBeenMade) {
			return "   U   ";
		} else if (isFoul) {
			return "   X   ";
		} else {
			return String.format("%7.2f", time);
		}
	}

	@Override
	/**
	 * Formatted according to the spec.
	 */
	public String toString() {
		return String.format("%-15.15s %s%s", athleteName, country,
				getTimeString());
	}

	public String getAthleteName() {
		return athleteName;
	}

	@Override
	public boolean lessThan(Timed other) {
		return compareTo((Timed) other) < 0;
	}
}

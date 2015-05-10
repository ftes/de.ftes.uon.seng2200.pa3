package de.ftes.uon.seng2200.pa3b;


/**
 * An event is an attempt made by one athlete.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public abstract class Event<T> implements Comparable<T> {
	protected final String athleteName;
	protected final String country;

	protected boolean hasBeenMade;
	/**
	 * Counter to retain the order in which an athlete made his attempts.
	 */
	protected final int numberOfAttempt;

	public Event(String athleteName, String country, int numberOfAttempt) {
		this.athleteName = athleteName;
		this.country = country;
		this.numberOfAttempt = numberOfAttempt;
		this.hasBeenMade = false;
	}
	
	public abstract boolean isFoul();
	
	public abstract boolean lessThan(Event<T> other);
	
	public abstract void setData(float value);
	
	@Override
	public abstract String toString();
	
	public int getNumberOfAttempt() {
		return numberOfAttempt;
	}
}

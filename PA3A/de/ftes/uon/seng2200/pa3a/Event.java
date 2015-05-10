package de.ftes.uon.seng2200.pa3a;


/**
 * All attempts made by one athlete.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public abstract class Event<T> implements Comparable<T> {
	protected final String athleteName;
	protected final String country;

	public Event(String athleteName, String country) {
		this.athleteName = athleteName;
		this.country = country;
	}
	
	public abstract boolean lessThan(Event<T> other);
	
	public abstract void setData(float value);
	
	@Override
	public abstract String toString();
	
	public String getAthleteName() {
		return athleteName;
	}
	
	public String getCountry() {
		return country;
	}
}

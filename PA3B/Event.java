



/**
 * All attempts made by one athlete.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public abstract class Event<T> implements LessThanComparable<T> {
	protected final String athleteName;
	protected final String country;

	public Event(String athleteName, String country) {
		this.athleteName = athleteName;
		this.country = country;
	}
	
	@Override
	public abstract boolean lessThan(T other);
	
	/**
	 * Adds another data value to this event. Might not actually
	 * overwrite an old value, but instead append to an internal list of values (e.g. for {@link Distance}).
	 */
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

package de.ftes.uon.seng2200.pa3b;

/**
 * Necessary, because the SortedList does not operate only on {@link Event}s, but also on other types.
 * @author Fredrik Teschke (3228760)
 *
 */
public interface LessThanComparable<T> {
	/**
	 * Can delegate to {@link #compareTo(Object)}.
	 */
	boolean lessThan(T other);
}

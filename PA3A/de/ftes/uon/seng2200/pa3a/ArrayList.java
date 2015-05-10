package de.ftes.uon.seng2200.pa2;


/**
 * Array-style List that has no ordering.
 * 
 * @author Fredrik Teschke (3228760)
 */
public interface ArrayList<T> extends List<T> {
	/**
	 * Insert element at beginning of list.
	 */
	void prepend(T o);

	/**
	 * Insert element at end of list.
	 */
	void append(T o);

	/**
	 * Insert element {@toInsert} in front of element {@code before}.
	 * @return {@code false} if {@code before} was not found in list, and
	 *         {@code toInsert} could therefore not be inserted
	 */
	boolean insertBefore(T toInsert, T before);
	
	/**
	 * Replace the old element at position {@code i} with {@code toInsert}.
	 * @throws IndexOutOfBoundsException if no element is currently present at position {@code i}
	 */
	void put(int i, T toInsert) throws IndexOutOfBoundsException;
}

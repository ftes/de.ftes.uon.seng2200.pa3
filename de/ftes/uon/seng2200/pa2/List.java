package de.ftes.uon.seng2200.pa2;



/**
 * Basic list interface that contains common functionality of both array-style (unordered) and sorted lists.
 * @author Fredrik Teschke (3228760)
 *
 */
public interface List<T> extends Iterable<T> {
	/**
	 * Return and remove object from head of list.
	 */
	T pop() throws ListEmptyException;

	T get(int i) throws IndexOutOfBoundsException;

	/**
	 * @return the element at the i'th position counted from the end (index {@link #size() size} - 1 - i)
	 */
	T getReversed(int i) throws IndexOutOfBoundsException;

	/**
	 * @return the number of elements in the list
	 */
	int size();
}
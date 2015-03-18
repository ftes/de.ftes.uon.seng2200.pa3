package de.ftes.uon.seng2200.list;

/**
 * A list that sorts elements upon insertion using their {@link Comparable#compareTo} method.
 * All elements in this list will have negative {@link Comparable#compareTo compareTo} values
 * with regards to all elements to the right of them.
 * @author Fredrik Teschke
 *
 */
public interface SortedList extends List {	
	void insertInOrder(Comparable data);
}

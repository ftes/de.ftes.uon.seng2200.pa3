package de.ftes.uon.seng2200.list.impl;

import de.ftes.uon.seng2200.list.List;
import de.ftes.uon.seng2200.list.SortedList;

/**
 * A sorted list that defers all sorting functionality to its
 * {@link ComparableNode}s.
 * 
 * @author Fredrik Teschke
 *
 */
public class SortedListImpl<T extends Comparable<T>> extends ArrayListImpl<T>
		implements SortedList<T> {
	public SortedListImpl() {
		super();
	}

	public SortedListImpl(List<T> fromList) {
		for (T data : fromList) {
			insertInOrder(data);
		}
	}

	@Override
	public void insertInOrder(T data) {
		for (T t : this) {
			if (data.compareTo(t) < 0) {
				super.insertBefore(data, t);
				return;
			}
		}
		// not inserted? insert at end!
		append(data);
	}
}

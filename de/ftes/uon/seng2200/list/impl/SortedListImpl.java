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
public class SortedListImpl<T extends Comparable<T>> extends ListImpl<T>
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
		start.getNext().insertInOrder(data);
		size++;
		modCount++;
	}

	@Override
	public void prepend(T o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void append(T o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void put(int i, T toInsert) throws IndexOutOfBoundsException {
		throw new UnsupportedOperationException();
	}
}

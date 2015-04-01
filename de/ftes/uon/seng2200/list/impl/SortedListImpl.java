package de.ftes.uon.seng2200.list.impl;

import de.ftes.uon.seng2200.list.SortedList;

/**
 * A sorted list that defers all sorting functionality to its
 * {@link ComparableNode}s.
 * 
 * @author Fredrik Teschke
 *
 */
public class SortedListImpl<T extends Comparable<T>> extends
		BaseList<T, ComparableNode<T>> implements SortedList<T> {
	public SortedListImpl() {
		super(new ComparableStartNodeImpl<T>(), new ComparableEndNodeImpl<T>());
	}

	@Override
	public void insertInOrder(T data) {
		start.getNext().insertInOrder(data);
		size++;
	}
}

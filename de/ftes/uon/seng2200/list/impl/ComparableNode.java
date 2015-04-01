package de.ftes.uon.seng2200.list.impl;

import de.ftes.uon.seng2200.list.SortedList;

/**
 * A comparable node that can be used in a {@link SortedList}.
 * @author Fredrik Teschke
 *
 */
interface ComparableNode<T extends Comparable<T>> extends BaseNode<T, ComparableNode<T>> {
	void insertInOrder(T toInsert);
}

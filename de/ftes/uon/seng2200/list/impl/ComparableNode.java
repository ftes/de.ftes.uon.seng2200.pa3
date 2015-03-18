package de.ftes.uon.seng2200.list.impl;

/**
 * A comparable node that can be used in a {@link SortedList}.
 * @author Fredrik Teschke
 *
 */
interface ComparableNode extends Node {
	void insertInOrder(Comparable toInsert);
}

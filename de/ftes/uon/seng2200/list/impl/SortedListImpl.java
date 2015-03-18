package de.ftes.uon.seng2200.list.impl;

import de.ftes.uon.seng2200.list.SortedList;

/**
 * A sorted list that defers all sorting functionality to its {@link ComparableNode}s.
 * @author Fredrik Teschke
 *
 */
public class SortedListImpl extends ListImpl implements SortedList {	
	public void insertInOrder(Comparable data) {
		((ComparableNode) start.getNext()).insertInOrder(data);
	}
}

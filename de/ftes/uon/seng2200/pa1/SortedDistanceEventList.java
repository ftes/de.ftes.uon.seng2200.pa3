package de.ftes.uon.seng2200.pa1;

import de.ftes.uon.seng2200.list.impl.SortedListImpl;

/**
 * Adapter class, necessary because use of generics not permitted for assignment.
 * Doesn't extend {@link DistanceEventList} because abstract super implementation of {@link SortedListImpl}
 * exists and multi-inheritance not allowed in Java.
 */
public class SortedDistanceEventList extends SortedListImpl {
	@Override
	public DistanceEvent getReversed(int i) throws IndexOutOfBoundsException {
		return (DistanceEvent) super.getReversed(i);
	}
	
	@Override
	public DistanceEvent get(int i) throws IndexOutOfBoundsException {
		return (DistanceEvent) super.get(i);
	};
	
	@Override
	public DistanceEvent pop() {
		return (DistanceEvent) super.pop();
	}
}

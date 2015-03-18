package de.ftes.uon.seng2200.pa1;

import de.ftes.uon.seng2200.list.impl.ListImpl;

/**
 * The basic {@link ListImpl} adapted to hold elements of type {@link DistanceEvent}.
 * @author Fredrik Teschke
 *
 */
public class DistanceEventList extends ListImpl {
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

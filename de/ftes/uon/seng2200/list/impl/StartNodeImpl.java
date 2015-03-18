package de.ftes.uon.seng2200.list.impl;


/**
 * A sentinel node used as the first node of a list that has no {@link #getPrevious() previous} node.
 * @author Fredrik Teschke
 *
 */
class StartNodeImpl extends SentinelNodeImpl {
	@Override
	public ComparableNode getPrevious() {
		throw new RuntimeException("Start Node has no previous");
	}
	
	@Override
	public void setPrevious(Node previous) {
		throw new IndexOutOfBoundsException("Start Node has no previous");
	}
	
	@Override
	public Object getNthPrevious(int i) {
		throw new IndexOutOfBoundsException();
	}
}

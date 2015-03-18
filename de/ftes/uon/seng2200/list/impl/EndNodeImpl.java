package de.ftes.uon.seng2200.list.impl;

/**
 * A sentinel node for the end of the list that has no {@code next} node.
 * @author Fredrik Teschke
 *
 */
class EndNodeImpl extends SentinelNodeImpl {
	public EndNodeImpl(Node startNode) {
		setPrevious(startNode);
		startNode.setNext(this);
	}

	@Override
	public ComparableNode getNext() {
		throw new IndexOutOfBoundsException("End Node has no next");
	}
	
	@Override
	public void setNext(Node next) {
		throw new IndexOutOfBoundsException("End Node has no next");
	}
	
	@Override
	public Node find(Object o) {
		return null;
	}
	
	@Override
	/**
	 * If {@code toInsert} was not yet inserted, it has to go at the end of the list,
	 * so before this sentinel node.
	 */
	public void insertInOrder(Comparable toInsert) {
		insertBefore(toInsert);
	}
	
	@Override
	public Object getNthNext(int i) {
		throw new IndexOutOfBoundsException();
	}
}

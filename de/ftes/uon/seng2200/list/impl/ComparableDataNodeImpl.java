package de.ftes.uon.seng2200.list.impl;

class ComparableDataNodeImpl extends DataNodeImpl implements ComparableNode {
	ComparableDataNodeImpl(Comparable data) {
		super(data);
	}

	@Override
	protected Node createNode(Object data) {
		return new ComparableDataNodeImpl((Comparable) data);
	}

	@Override
	public void insertInOrder(Comparable toInsert) {
		if (((Comparable) getData()).compareTo(toInsert) < 0) {
			((ComparableNode) getNext()).insertInOrder(toInsert);
		} else {
			insertBefore(toInsert);
		}
	}
}

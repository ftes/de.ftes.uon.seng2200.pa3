package de.ftes.uon.seng2200.list.impl;

/**
 * A sentinel node that holds no data, but is merely used as the first/last node in a list
 * to make implementation easier by using polymorphism rather than if statements.
 * @author Fredrik Teschke
 *
 */
abstract class SentinelNodeImpl extends ComparableDataNodeImpl {
	public SentinelNodeImpl() {
		super(null);
	}

	public void remove() {
		throw new RuntimeException("Can't remove sentinel node");
	};
	
	public Object getData() {
		throw new IndexOutOfBoundsException();
	}
}

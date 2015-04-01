package de.ftes.uon.seng2200.list.impl;

/**
 * A sentinel node for the end of the list that has no {@code next} node.
 * @author Fredrik Teschke
 *
 */
abstract class BaseEndNode<T, N extends BaseNode<T, N>> extends AbstractSentinelNode<T, N> {
	protected BaseEndNode(N wrappedNode) {
		super(wrappedNode);
	}

	@Override
	public N getNext() {
		throw new IndexOutOfBoundsException("End Node has no next");
	}
	
	@Override
	public void setNext(N next) {
		throw new IndexOutOfBoundsException("End Node has no next");
	}
	
	@Override
	public N find(T o) {
		return null;
	}
	
	@Override
	public T getNthNext(int i) {
		throw new IndexOutOfBoundsException();
	}
}

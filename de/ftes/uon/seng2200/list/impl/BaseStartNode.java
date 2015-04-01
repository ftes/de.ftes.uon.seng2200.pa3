package de.ftes.uon.seng2200.list.impl;


/**
 * A sentinel node used as the first node of a list that has no {@link #getPrevious() previous} node.
 * @author Fredrik Teschke
 *
 */
abstract class BaseStartNode<T, N extends BaseNode<T, N>> extends AbstractSentinelNode<T, N> {
	protected BaseStartNode(N wrappedNode) {
		super(wrappedNode);
	}
	
	@Override
	public N getPrevious() {
		throw new IndexOutOfBoundsException("Start Node has no previous");
	}
	
	@Override
	public void setPrevious(N previous) {
		throw new IndexOutOfBoundsException("Start Node has no previous");
	}
	
	@Override
	public N getNthPrevious(int i) {
		throw new IndexOutOfBoundsException();
	}
}

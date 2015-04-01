package de.ftes.uon.seng2200.list.impl;

import de.ftes.uon.seng2200.list.ListException;


/**
 * A sentinel node that holds no data, but is merely used as the first/last node in a list
 * to make implementation easier by using polymorphism rather than if statements.
 * 
 * Implemented by wrapping a node, so that sentinel nodes can be easily constructed for different
 * kinds of lists.
 * 
 * @author Fredrik Teschke
 *
 */
abstract class AbstractSentinelNode<T, N extends BaseNode<T, N>> implements BaseNode<T, N> {
	protected final N wrappedNode;
	
	public AbstractSentinelNode(N wrappedNode) {
		this.wrappedNode = wrappedNode;
	}
	
	@Override
	public N find(T o) {
		return wrappedNode.find(o);
	}
	
	@Override
	public N getNext() {
		return wrappedNode.getNext();
	}
	
	@Override
	public N getPrevious() {
		return wrappedNode.getPrevious();
	}
	
	@Override
	public N getNthNext(int i) {
		return wrappedNode.getNthNext(i);
	}
	
	@Override
	public N getNthPrevious(int i) {
		return wrappedNode.getNthPrevious(i);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void insertBefore(T dataToInsert) {
		wrappedNode.insertBefore(dataToInsert);
		
		// set next to this instead of wrapped node
		getPrevious().setNext((N) this);
	}
	
	@Override
	public void setNext(N next) {
		wrappedNode.setNext(next);
	}
	
	@Override
	public void setPrevious(N previous) {
		wrappedNode.setPrevious(previous);
	}

	@Override
	public void remove() {
		throw new ListException("Can't remove sentinel node");
	};
	
	@Override
	public T getData() {
		throw new IndexOutOfBoundsException();
	}
	
	@Override
	public N createNode(T data) {
		return wrappedNode.createNode(data);
	}
}

package de.ftes.uon.seng2200.list.impl;

class EndNodeImpl<T> extends BaseEndNode<T, Node<T>> implements Node<T> {
	public EndNodeImpl() {
		super(new DataNodeImpl<T>(null));
	}
}

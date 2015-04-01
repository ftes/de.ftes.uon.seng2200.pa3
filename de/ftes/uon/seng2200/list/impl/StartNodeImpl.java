package de.ftes.uon.seng2200.list.impl;


class StartNodeImpl<T> extends BaseStartNode<T, Node<T>> implements Node<T> {
	public StartNodeImpl() {
		super(new DataNodeImpl<T>(null));
	}
}

package de.ftes.uon.seng2200.list.impl;


public class ListImpl<T> extends BaseList<T, Node<T>> {
	public ListImpl() {
		super(new StartNodeImpl<T>(), new EndNodeImpl<T>());
	}
}

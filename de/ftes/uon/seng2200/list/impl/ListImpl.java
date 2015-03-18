package de.ftes.uon.seng2200.list.impl;

import de.ftes.uon.seng2200.list.List;

/**
 * A doubly linked list implementation.
 * @author Fredrik Teschke
 *
 */
public class ListImpl
// implements Iterable
 implements List
{
	protected final StartNodeImpl start;
	protected final EndNodeImpl end;
	private int size = 0;

	public ListImpl() {
		start = new StartNodeImpl();
		end = new EndNodeImpl(start);
	}

	@Override
	public void prepend(Object o) {
		start.getNext().insertBefore(o);
		size++;
	}

	@Override
	public void append(Object o) {
		end.insertBefore(o);
		size++;
	}

	/**
	 * {@code null} if {@code o} can't be found
	 */
	private Node find(Object o) {
		return start.find(o);
	}

	@Override
	public boolean insertBefore(Object toInsert, Object before) {
		Node beforeNode = find(before);
		if (beforeNode == null) {
			return false;
		}
		beforeNode.insertBefore(toInsert);
		size++;
		return true;
	}

	@Override
	public Object pop() {
		Node head = start.getNext();
		head.remove();
		size--;
		return head.getData();
	}

	@Override
	public Object get(int i) throws IndexOutOfBoundsException {
		return start.getNext().getNthNext(i);
	}

	@Override
	public Object getReversed(int i) throws IndexOutOfBoundsException {
		return end.getPrevious().getNthPrevious(i);
	}
	
	@Override
	public int size() {
		return size;
	}

	// @Override
	// public Iterator iterator() {
	// return new Iterator() {
	// private Node next = List.this.start.getNext();
	//
	// @Override
	// public boolean hasNext() {
	// return ! (next instanceof EndNodeImpl);
	// }
	//
	// @Override
	// public Object next() {
	// Object data = next.getData();
	// next = next.getNext();
	// return data;
	// }
	// };
	// }
}

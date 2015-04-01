package de.ftes.uon.seng2200.list.impl;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import de.ftes.uon.seng2200.list.List;
import de.ftes.uon.seng2200.list.ListEmptyException;

/**
 * A doubly linked list implementation.
 * 
 * @author Fredrik Teschke
 *
 */
abstract class BaseList<T, N extends BaseNode<T, N>> implements List<T> {
	protected final N start;
	protected final N end;
	protected int size = 0;
	protected int modCount = 0;

	protected BaseList(N start, N end) {
		this.start = start;
		this.end = end;
		end.setPrevious(start);
		start.setNext(end);
	}

	@Override
	public void prepend(T o) {
		start.getNext().insertBefore(o);
		size++;
		modCount++;
	}

	@Override
	public void append(T o) {
		end.insertBefore(o);
		size++;
		modCount++;
	}

	/**
	 * {@code null} if {@code o} can't be found
	 */
	private N find(T o) {
		return start.find(o);
	}

	@Override
	public boolean insertBefore(T toInsert, T before) {
		N beforeNode = find(before);
		if (beforeNode == null) {
			return false;
		}
		beforeNode.insertBefore(toInsert);
		size++;
		modCount++;
		return true;
	}

	@Override
	public void put(int i, T toInsert) throws IndexOutOfBoundsException {
		N oldNode = start.getNext().getNthNext(i);
		oldNode.insertBefore(toInsert);
		oldNode.remove();
		modCount++;
	}

	@Override
	public T pop() {
		if (size == 0) {
			throw new ListEmptyException("Cannot pop element from empty list");
		}
		N head = start.getNext();
		head.remove();
		size--;
		modCount++;
		return head.getData();
	}

	@Override
	public T get(int i) throws IndexOutOfBoundsException {
		return start.getNext().getNthNext(i).getData();
	}

	@Override
	public T getReversed(int i) throws IndexOutOfBoundsException {
		return end.getPrevious().getNthPrevious(i).getData();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private N next = BaseList.this.start.getNext();
			private int expectedModCount = modCount;

			@Override
			public boolean hasNext() {
				return !(next instanceof BaseEndNode);
			}

			@Override
			public T next() {
				if (modCount != expectedModCount) {
					throw new ConcurrentModificationException(
							"List may not be modified while being iterated over.");
				} else if (!hasNext()) {
					throw new NoSuchElementException(
							"List has no more elements");
				}
				T data = next.getData();
				next = next.getNext();
				return data;
			}
		};
	}

	@Override
	public String toString() {
		String s = "[";
		for (T data : this) {
			s += data + ", ";
		}
		s += "]";
		return s;
	}
}

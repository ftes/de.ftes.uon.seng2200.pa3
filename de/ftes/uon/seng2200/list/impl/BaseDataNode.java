package de.ftes.uon.seng2200.list.impl;

/**
 * A data node that actually holds a {@link #getData() data object}.
 * @author Fredrik Teschke
 *
 */
abstract class BaseDataNode<T, N extends BaseNode<T, N>> implements BaseNode<T, N> {
	private final T data;
	private N next;
	private N previous;

	protected BaseDataNode(T data) {
		this.data = data;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public N getNext() {
		return next;
	}

	@Override
	public N getPrevious() {
		return previous;
	}

	@Override
	public void setNext(N next) {
		this.next = next;
	}

	@Override
	public void setPrevious(N previous) {
		this.previous = previous;
	}

	@SuppressWarnings("unchecked")
	@Override
	public N find(T o) {
		if (o == data) {
			return (N) this;
		}
		return next.find(o);
	}

	@Override
	public void remove() {
		previous.setNext(next);
		next.setPrevious(previous);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insertBefore(T data) {
		N toInsert = createNode(data);
		previous.setNext(toInsert);
		toInsert.setPrevious(previous);
		toInsert.setNext((N) this);
		this.setPrevious(toInsert);
	}

	public T getNthNext(int i) {
		if (i == 0) {
			return data;
		} else {
			return next.getNthNext(i - 1);
		}
	}

	public T getNthPrevious(int i) {
		if (i == 0) {
			return data;
		} else {
			return previous.getNthPrevious(i - 1);
		}
	}
}

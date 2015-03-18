package de.ftes.uon.seng2200.list.impl;

/**
 * A data node that actually holds a {@link #getData() data object}.
 * @author Fredrik Teschke
 *
 */
class DataNodeImpl implements Node {
	private final Object data;
	private Node next;
	private Node previous;

	public DataNodeImpl(Object data) {
		this.data = data;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public Node getNext() {
		return next;
	}

	@Override
	public Node getPrevious() {
		return previous;
	}

	@Override
	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	@Override
	public Node find(Object o) {
		if (o == data) {
			return this;
		}
		return next.find(o);
	}

	@Override
	public void remove() {
		previous.setNext(next);
		next.setPrevious(previous);
	}

	/**
	 * Factory method, can be overridden by subclasses.
	 */
	protected Node createNode(Object data) {
		return new DataNodeImpl(data);
	}

	@Override
	public void insertBefore(Object data) {
		Node toInsert = createNode(data);
		previous.setNext(toInsert);
		toInsert.setPrevious(previous);
		toInsert.setNext(this);
		this.setPrevious(toInsert);
	}

	public Object getNthNext(int i) {
		if (i == 0) {
			return data;
		} else {
			return next.getNthNext(i - 1);
		}
	}

	public Object getNthPrevious(int i) {
		if (i == 0) {
			return data;
		} else {
			return previous.getNthPrevious(i - 1);
		}
	}
}

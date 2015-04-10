package de.ftes.uon.seng2200.list.impl;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import de.ftes.uon.seng2200.list.ArrayList;
import de.ftes.uon.seng2200.list.ListEmptyException;
import de.ftes.uon.seng2200.list.ListException;

/**
 * A doubly linked list implementation.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class ArrayListImpl<T> implements ArrayList<T> {
	/**
	 * A node in a doubly linked list.
	 * @author Fredrik Teschke
	 *
	 * @param <T> type of managed data element
	 * @param <N> type of node
	 */
	private interface Node<T> {
		/**
		 * @return the managed data object
		 */
		T getData();

		/**
		 * @return the next node in the list {@code null} if this is the last node).
		 */
		Node<T> getNext();
		
		/**
		 * @return the previous node in the list {@code null} if this is the first node).
		 */
		Node<T> getPrevious();

		void setNext(Node<T> next);

		void setPrevious(Node<T> previous);

		/**
		 * A recursive find operation for the data object {@code o}.
		 * @return {@code this} if {@code o} is the object managed by this node, else
		 * 		delegate to {@code getNext()} node. Exception to the rule:
		 * 		{@link SentinelNodeImpl end node), that returns null.
		 */
		Node<T> find(T o);

		/**
		 * Inserts {@code dataToInsert} before this node by creating a new node {@code N} that holds
		 * {@code dataToInsert}. The relevant pointers of this node and the current {@link #getPrevious() previous node}
		 * are adjusted to point to {@code N}.
		 */
		void insertBefore(T dataToInsert);

		/**
		 * Removes this node from a list by adjusting the pointers of its {@link #getPrevious() previous} and
		 * {@link #getNext() next} nodes.
		 */
		void remove();

		/**
		 * Recursive operation for getting a node at a specified index.
		 * @return {@code this} if {@code i == 0}, otherwise call
		 *		{@code next.getNthNext(i-1)}
		 */
		Node<T> getNthNext(int i);
		
		/**
		 * Recursive operation for getting a node at a specified index, counted from the end of the list.
		 * @return {@code this} if {@code i == 0}, otherwise call
		 *		{@code next.getNthPrevious(i-1)}
		 */
		Node<T> getNthPrevious(int i);
	}
	
	/**
	 * A data node that actually holds a {@link #getData() data object}.
	 * @author Fredrik Teschke
	 *
	 */
	private class DataNodeImpl implements Node<T> {
		private final T data;
		private Node<T> next;
		private Node<T> previous;

		protected DataNodeImpl(T data) {
			this.data = data;
		}

		@Override
		public T getData() {
			return data;
		}

		@Override
		public Node<T> getNext() {
			return next;
		}

		@Override
		public Node<T> getPrevious() {
			return previous;
		}

		@Override
		public void setNext(Node<T> next) {
			this.next = next;
		}

		@Override
		public void setPrevious(Node<T> previous) {
			this.previous = previous;
		}

		@Override
		public Node<T> find(T o) {
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

		@Override
		public void insertBefore(T data) {
			Node<T> toInsert = new DataNodeImpl(data);
			previous.setNext(toInsert);
			toInsert.setPrevious(previous);
			toInsert.setNext(this);
			this.setPrevious(toInsert);
		}

		public Node<T> getNthNext(int i) {
			if (i == 0) {
				return this;
			} else {
				return next.getNthNext(i - 1);
			}
		}

		public Node<T> getNthPrevious(int i) {
			if (i == 0) {
				return this;
			} else {
				return previous.getNthPrevious(i - 1);
			}
		}
	}
	
	/**
	 * A sentinel node that holds no data, but is merely used as the first/last node in a list
	 * to make implementation easier by using polymorphism rather than if statements.
	 * 
	 * @author Fredrik Teschke
	 *
	 */
	private abstract class AbstractSentinelNode extends DataNodeImpl {
		protected AbstractSentinelNode() {
			super(null);
		}

		@Override
		public void remove() {
			throw new ListException("Can't remove sentinel node");
		};
		
		@Override
		public T getData() {
			throw new ListException("Sentinel Node has no data");
		}
	}
	
	/**
	 * A sentinel node used as the first node of a list that has no {@link #getPrevious() previous} node.
	 * @author Fredrik Teschke
	 *
	 */
	private class StartNodeImpl extends AbstractSentinelNode {
		@Override
		public Node<T> getPrevious() {
			throw new IndexOutOfBoundsException("Start Node has no previous");
		}
		
		@Override
		public void setPrevious(Node<T> previous) {
			throw new IndexOutOfBoundsException("Start Node has no previous");
		}
		
		@Override
		public Node<T> getNthPrevious(int i) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * A sentinel node for the end of the list that has no {@code next} node.
	 * @author Fredrik Teschke
	 *
	 */
	private class EndNodeImpl extends AbstractSentinelNode {
		@Override
		public Node<T> getNext() {
			throw new IndexOutOfBoundsException("End Node has no next");
		}
		
		@Override
		public void setNext(Node<T> next) {
			throw new IndexOutOfBoundsException("End Node has no next");
		}
		
		@Override
		public Node<T> find(T o) {
			return null;
		}
		
		@Override
		public Node<T> getNthNext(int i) {
			throw new IndexOutOfBoundsException();
		}
	}

	
	private final Node<T> start = new StartNodeImpl();
	private final Node<T> end = new EndNodeImpl();
	private int size = 0;
	private int modCount = 0;

	public ArrayListImpl() {
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
	private Node<T> find(T o) {
		return start.find(o);
	}

	@Override
	public boolean insertBefore(T toInsert, T before) {
		Node<T> beforeNode = find(before);
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
		Node<T> oldNode = start.getNext().getNthNext(i);
		oldNode.insertBefore(toInsert);
		oldNode.remove();
		modCount++;
	}

	@Override
	public T pop() {
		if (size == 0) {
			throw new ListEmptyException("Cannot pop element from empty list");
		}
		Node<T> head = start.getNext();
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
			private Node<T> next = ArrayListImpl.this.start.getNext();
			private int expectedModCount = modCount;

			@Override
			public boolean hasNext() {
				return !(next instanceof ArrayListImpl.EndNodeImpl);
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

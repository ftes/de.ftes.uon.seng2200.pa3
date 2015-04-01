package de.ftes.uon.seng2200.list.impl;

/**
 * A node in a doubly linked list.
 * @author Fredrik Teschke
 *
 * @param <T> type of managed data element
 * @param <N> type of node
 */
interface BaseNode<T, N extends BaseNode<T, N>> {
	/**
	 * @return the managed data object
	 */
	T getData();

	/**
	 * @return the next node in the list {@code null} if this is the last node).
	 */
	N getNext();
	
	/**
	 * @return the previous node in the list {@code null} if this is the first node).
	 */
	N getPrevious();

	void setNext(N next);

	void setPrevious(N previous);

	/**
	 * A recursive find operation for the data object {@code o}.
	 * @return {@code this} if {@code o} is the object managed by this node, else
	 * 		delegate to {@code getNext()} node. Exception to the rule:
	 * 		{@link SentinelNodeImpl end node), that returns null.
	 */
	N find(T o);

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
	N getNthNext(int i);
	
	/**
	 * Recursive operation for getting a node at a specified index, counted from the end of the list.
	 * @return {@code this} if {@code i == 0}, otherwise call
	 *		{@code next.getNthPrevious(i-1)}
	 */
	N getNthPrevious(int i);

	/**
	 * Factory method.
	 */
	N createNode(T data);
}

package de.ftes.uon.seng2200.list.impl;

/**
 * A node in a doubly linked list.
 * @author Fredrik Teschke
 *
 */
interface Node {
	/**
	 * @return the managed data object
	 */
	Object getData();

	/**
	 * @return the next node in the list {@code null} if this is the last node).
	 */
	Node getNext();
	
	/**
	 * @return the previous node in the list {@code null} if this is the first node).
	 */
	Node getPrevious();

	void setNext(Node next);

	void setPrevious(Node previous);

	/**
	 * A recursive find operation for the data object {@code o}.
	 * @return {@code this} if {@code o} is the object managed by this node, else
	 * 		delegate to {@code getNext()} node. Exception to the rule:
	 * 		{@link SentinelNodeImpl end node), that returns null.
	 */
	Node find(Object o);

	/**
	 * Inserts {@code dataToInsert} before this node by creating a new node {@code N} that holds
	 * {@code dataToInsert}. The relevant pointers of this node and the current {@link #getPrevious() previous node}
	 * are adjusted to point to {@code N}.
	 */
	void insertBefore(Object dataToInsert);

	/**
	 * Removes this node from a list by adjusting the pointers of its {@link #getPrevious() previous} and
	 * {@link #getNext() next} nodes.
	 */
	void remove();

	/**
	 * Recursive get operation for getting a data object from a specified index.
	 * @return the {@link #getData() managed data object} if {@code i == 0}, otherwise call
	 *		{@code next.getNthNext(i-1)}
	 */
	Object getNthNext(int i);
	
	/**
	 * Recursive get operation for getting a data object from a specified index, counted from the end of the list.
	 * @return the {@link #getData() managed data object} if {@code i == 0}, otherwise call
	 *		{@code next.getNthPrevious(i-1)}
	 */
	Object getNthPrevious(int i);
}

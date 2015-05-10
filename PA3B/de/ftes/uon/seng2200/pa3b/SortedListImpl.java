package de.ftes.uon.seng2200.pa3b;




/**
 * A sorted list that defers all sorting functionality to its
 * {@link ComparableNode}s.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class SortedListImpl<T extends LessThanComparable<T>> extends ArrayListImpl<T>
		implements SortedList<T> {
	public SortedListImpl() {
		super();
	}

	public SortedListImpl(List<T> fromList) {
		for (T data : fromList) {
			insertInOrder(data);
		}
	}

	@Override
	public void insertInOrder(T data) {
		for (T t : this) {
			if (data.lessThan(t)) {
				super.insertBefore(data, t);
				return;
			}
		}
		// not inserted? insert at end!
		append(data);
	}
}

package de.ftes.uon.seng2200.pa3b;


/**
 * Holds all the attempts (events) of one athlete.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public interface EventCollection<T, EventT extends Event<EventT>> extends Comparable<T> {
	SortedList<EventT> getSortedDistances();
	EventT getNthBestEvent(int n);
	void addData(int numberOfAttempt, float value);
	int getIndex();
	String getAthleteName();
}

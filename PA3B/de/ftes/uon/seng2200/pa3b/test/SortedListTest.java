package de.ftes.uon.seng2200.pa3b.test;


import org.junit.Assert;
import org.junit.Test;

import de.ftes.uon.seng2200.pa3b.DistanceAttempt;
import de.ftes.uon.seng2200.pa3b.SortedList;
import de.ftes.uon.seng2200.pa3b.SortedListImpl;

/**
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class SortedListTest {
	@Test
	public void createListThenInsertAndRemoveItems() {
		SortedList<DistanceAttempt> list = new SortedListImpl<>();
		
		DistanceAttempt i1 = new DistanceAttempt(1, 3);
		DistanceAttempt i2 = new DistanceAttempt(2, 2);
		DistanceAttempt i3 = new DistanceAttempt(3, 1);
		
		list.insertInOrder(i3);
		list.insertInOrder(i1);
		list.insertInOrder(i2);
		
		Assert.assertEquals(i1, list.pop());
		Assert.assertEquals(i2, list.pop());
		Assert.assertEquals(i3, list.pop());
	}
}

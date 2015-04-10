package de.ftes.uon.seng2200.pa2.test;


import org.junit.Assert;
import org.junit.Test;

import de.ftes.uon.seng2200.pa2.SortedList;
import de.ftes.uon.seng2200.pa2.SortedListImpl;

/**
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class SortedListTest {
	@Test
	public void createListThenInsertAndRemoveItems() {
		SortedList<Integer> list = new SortedListImpl<>();
		
		Integer i1 = 1;
		Integer i2 = 2;
		Integer i3 = 3;
		
		list.insertInOrder(i3);
		list.insertInOrder(i1);
		list.insertInOrder(i2);
		
		Assert.assertEquals(i1, list.pop());
		Assert.assertEquals(i2, list.pop());
		Assert.assertEquals(i3, list.pop());
	}
}

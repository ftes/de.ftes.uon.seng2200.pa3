package de.ftes.uon.seng2200.list.test;

import org.junit.Assert;
import org.junit.Test;

import de.ftes.uon.seng2200.list.impl.SortedListImpl;

public class SortedListTest {
	@Test
	public void createListThenInsertAndRemoveItems() {
		SortedListImpl list = new SortedListImpl();
		
		int i1 = 1;
		int i2 = 2;
		int i3 = 3;
		
		list.insertInOrder(i3);
		list.insertInOrder(i1);
		list.insertInOrder(i2);
		
		Assert.assertEquals(i1, list.pop());
		Assert.assertEquals(i2, list.pop());
		Assert.assertEquals(i3, list.pop());
	}
}

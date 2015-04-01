package de.ftes.uon.seng2200.list.test;

import org.junit.Assert;
import org.junit.Test;

import de.ftes.uon.seng2200.list.List;
import de.ftes.uon.seng2200.list.impl.ListImpl;

public class ListTest {
	@Test
	public void createListThenInsertAndRemoveItems() {
		List<Integer> list = new ListImpl<Integer>();
		
		Integer i1 = 1;
		Integer i2 = 2;
		Integer i3 = 3;
		
		list.prepend(i1);
		list.append(i3);
		
		//can't insert before element not in list
		Assert.assertFalse(list.insertBefore(i2, i2));
		
		Assert.assertTrue(list.insertBefore(i2, i1));
		
		Assert.assertEquals(i3, list.get(2));
		Assert.assertEquals(i3, list.getReversed(0));
		
		Assert.assertEquals(i2, list.pop());
		Assert.assertEquals(i1, list.pop());
		Assert.assertEquals(i3, list.pop());
	}
}

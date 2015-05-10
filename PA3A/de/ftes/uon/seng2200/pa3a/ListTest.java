package de.ftes.uon.seng2200.pa2;


import java.util.ConcurrentModificationException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class ListTest {
	@Test
	public void createListThenInsertAndRemoveItems() {
		ArrayList<Integer> list = new ArrayListImpl<>();
		
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
	
	@Test
	public void putItem() {
		ArrayList<Integer> list = new ArrayListImpl<>();
		list.append(1);
		list.append(2);
		list.put(1, 3);
		Assert.assertEquals((Integer) 3, list.get(1));
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testIterator() {
		ArrayList<Integer> list = new ArrayListImpl<>();
		list.append(1);
		list.append(2);
		list.append(3);
		
		// should work if not modified
		for (@SuppressWarnings("unused") Integer i : list) {
			//pass
		}
		
		thrown.expect(ConcurrentModificationException.class);
		
		for (Integer i : list) {
			if (i == 2) {
				list.append(3);
			}
		}
	}
}

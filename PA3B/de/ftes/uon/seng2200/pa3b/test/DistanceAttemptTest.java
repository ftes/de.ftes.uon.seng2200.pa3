package de.ftes.uon.seng2200.pa3b.test;

import org.junit.Assert;
import org.junit.Test;

import de.ftes.uon.seng2200.pa3b.Distance;

public class DistanceAttemptTest {
	@Test
	public void isComparisonCorrect() {
		Distance foul = new Distance("", "", 0, 0); //foul
		Distance notMade = new Distance("", "", 0); //not yet attempted
		Distance one = new Distance("", "", 0, 1);
		Distance two = new Distance("", "", 0, 2);
		
		Assert.assertEquals(1, foul.compareTo(notMade)); //is foul ranked behind attempt not yet made?
		Assert.assertEquals(1, foul.compareTo(one)); //is foul ranked behind regular attempt?
		Assert.assertEquals(1, one.compareTo(two)); //are regular attempts ordered correctly?
	}
}

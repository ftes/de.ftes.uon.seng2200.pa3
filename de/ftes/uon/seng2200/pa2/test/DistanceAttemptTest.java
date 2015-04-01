package de.ftes.uon.seng2200.pa2.test;

import org.junit.Assert;
import org.junit.Test;

import de.ftes.uon.seng2200.pa2.DistanceAttempt;

public class DistanceAttemptTest {
	@Test
	public void isComparisonCorrect() {
		DistanceAttempt foul = new DistanceAttempt(0, 0.); //foul
		DistanceAttempt notMade = new DistanceAttempt(0); //not yet attempted
		DistanceAttempt one = new DistanceAttempt(0, 1);
		DistanceAttempt two = new DistanceAttempt(0, 2);
		
		Assert.assertEquals(1, foul.compareTo(notMade)); //is foul ranked behind attempt not yet made?
		Assert.assertEquals(1, foul.compareTo(one)); //is foul ranked behind regular attempt?
		Assert.assertEquals(1, one.compareTo(two)); //are regular attempts ordered correctly?
	}
}

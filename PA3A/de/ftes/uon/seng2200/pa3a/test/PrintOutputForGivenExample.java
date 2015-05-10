package de.ftes.uon.seng2200.pa2.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import de.ftes.uon.seng2200.pa2.Event;

public class PrintOutputForGivenExample {
	@Test
	public void testGivenExample() throws IOException {
		InputStream input = getClass().getResourceAsStream("asgn2_Input.txt");
		new Event(input, System.out);
	}
}

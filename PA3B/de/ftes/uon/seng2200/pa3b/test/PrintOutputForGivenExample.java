package de.ftes.uon.seng2200.pa3b.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import de.ftes.uon.seng2200.pa3b.EventParser;

public class PrintOutputForGivenExample {
	@Test
	public void testGivenExample() throws IOException {
		InputStream input = getClass().getResourceAsStream("asgn3_Input.txt");
		EventParser.parse(input, System.out);
	}
}

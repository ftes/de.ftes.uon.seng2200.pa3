package de.ftes.uon.seng2200.pa3b.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import de.ftes.uon.seng2200.pa3b.EventParser;

public class CompareComputedToExpectedOutputTest {
	private void compareToExpected(InputStream inputStream,
			InputStream expectedOutputInputStream) throws IOException {
		ByteArrayOutputStream computedOut = new ByteArrayOutputStream();

		EventParser.parse(inputStream, computedOut);
		byte[] computedBytes = computedOut.toByteArray();
		computedOut.close();

		InputStream computedIn = new ByteArrayInputStream(computedBytes);
		try {
			Assert.assertTrue(StreamUtils.equals(expectedOutputInputStream,
					computedIn));
		} finally {
			expectedOutputInputStream.close();
			computedIn.close();
		}
	}

	private void compareToExpectedFiles(String inputFileName,
			String expectedOutputFileName) throws IOException {
		InputStream input = getClass().getResourceAsStream(inputFileName);
		InputStream expectedOutput = getClass().getResourceAsStream(
				expectedOutputFileName);
		compareToExpected(input, expectedOutput);
	}

//	private void compareToExpectedStrings(String inputString,
//			String expectedOutputString) throws IOException {
//		InputStream input = new ByteArrayInputStream(inputString.getBytes());
//		InputStream expectedOutput = new ByteArrayInputStream(
//				expectedOutputString.getBytes());
//		compareToExpected(input, expectedOutput);
//	}

	@Test
	public void testGivenExample() throws IOException {
		compareToExpectedFiles("asgn3_Input.txt", "asgn3_Output.txt");
	}
}

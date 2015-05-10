package de.ftes.uon.seng2200.pa2.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {
	public static boolean equals(InputStream a, InputStream b) throws IOException {
		BufferedReader inA = new BufferedReader(new InputStreamReader(a));
		BufferedReader inB = new BufferedReader(new InputStreamReader(b));

		try {
			String lineA = inA.readLine();
			String lineB = inB.readLine();

			while (lineA != null) {
				if (lineB == null || lineA.compareTo(lineB) != 0) {
					return false;
				}
				lineA = inA.readLine();
				lineB = inB.readLine();
			}

			return lineB == null;
		} finally {
			inA.close();
			inB.close();
		}
	}
}

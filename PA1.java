

import java.io.IOException;

import de.ftes.uon.seng2200.pa1.Event;

/**
 * Course		SENG6220
 * Assignment	PA1
 * @author		Fredrik Teschke
 */
public class PA1 {
	/**
	 * Read the attempt data from standard input
	 * and print the result list to standard output.
	 */
	public static void main(String[] args) throws IOException {
		Event e = new Event(System.in);
		e.print(System.out);
	}
}



import java.io.IOException;

/**
 * Course		SENG6220
 * Assignment	PA3A
 * @author		Fredrik Teschke (3228760)
 */
public class PA3A {
	/**
	 * Read the attempt data from standard input
	 * and print the result list to standard output.
	 */
	public static void main(String[] args) throws IOException {
		EventParser.parse(System.in, System.out);
	}
}
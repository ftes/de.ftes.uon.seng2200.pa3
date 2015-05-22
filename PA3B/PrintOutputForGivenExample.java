

import java.io.IOException;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

public class PrintOutputForGivenExample {
	@Test
	@Ignore
	public void testGivenExample() throws IOException {
		InputStream input = getClass().getResourceAsStream("my_asgn3_Input.txt");
		EventParser.parse(input, System.out);
	}
}

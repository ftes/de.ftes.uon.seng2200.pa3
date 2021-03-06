

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class CheckExpectedOrderTest {

	private static String constructInput(String eventName,
			String[] athletesWithCountries, double[]... attempts) {
		String s = eventName + ", " + athletesWithCountries.length;
		for (String c : athletesWithCountries) {
			s += "\n" + c;
		}

		for (int i = 0; i < attempts[0].length; i++) {
			for (int j = 0; j < athletesWithCountries.length; j++) {
				s += "\n" + attempts[j][i];
			}
		}

		return s;
	}

	private static void checkOrder(EventParser<?> event, String[] expectedAthleteOrder) {
		for (int i = 0; i < expectedAthleteOrder.length; i++) {
			Assert.assertEquals(expectedAthleteOrder[i],
					event.getSortedEvents().get(i).getAthleteName());
		}
	}

	private static void testOrder(String[] athletes,
			String[] expectedAthleteOrder, double[]... attempts)
			throws IOException {
		String[] athletesWithCountries = new String[athletes.length];
		for (int i=0; i<athletes.length; i++) {
			athletesWithCountries[i] = athletes[i] + ",GER";
		}
		String input = constructInput("Hammer Throw", athletesWithCountries, attempts);
		EventParser<?> event = EventParser.parse(new ByteArrayInputStream(input.getBytes()), null, attempts[0].length);
		checkOrder(event, expectedAthleteOrder);
	}

	private static void testWinner(String[] athletes,
			String winner, double[]... attempts)
			throws IOException {
		String[] athletesWithCountries = new String[athletes.length];
		for (int i=0; i<athletes.length; i++) {
			athletesWithCountries[i] = athletes[i] + ",GER";
		}
		String input = constructInput("Hammer Throw", athletesWithCountries, attempts);
		EventParser<?> event = EventParser.parse(new ByteArrayInputStream(input.getBytes()), null,attempts[0].length);
		Assert.assertEquals(winner, event.getSortedEvents().get(0).getAthleteName());
	}

	@Test
	public void testAthleteWithHighestDistanceWins() throws IOException {
		testWinner(new String[] { "Max", "Moritz" }, "Max",
				new double[] { 1., 2., 3., 1. },
				new double[] { 2., 2., 2., 2. });
	}

	@Test
	public void testBestTwoDistancesAreEqualButhThirdDiffers() throws IOException {
		testWinner(new String[] { "Max", "Moritz" }, "Moritz",
				new double[] { 3., 2., 1. },
				new double[] { 2., 1.5, 3. });
	}

	@Test
	public void testEqualButForOrderOfAttempts() throws IOException {
		testWinner(new String[] { "Max", "Moritz" }, "Moritz",
				new double[] { 1., 2. },
				new double[] { 2., 1. });
	}

	@Test
	public void testAllEqualInputOrderWins() throws IOException {
		testOrder(new String[] { "Max", "Moritz", "Anton" },
				new String[] { "Max", "Moritz", "Anton" },
				new double[] { 1., 2., 1.5 },
				new double[] { 1., 2., 1.5 },
				new double[] { 1., 2., 1.5 });
	}

}
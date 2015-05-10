package de.ftes.uon.seng2200.pa2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * An event holds all {@link DistaneEvent}s of all athletes for a competition.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class Event {
	public static final int NUMBER_OF_ATTEMPTS = 6;
	
	private static final String COMMA_AND_WHITESPACE_REGEX = ",\\s*";
	private static final String[] MEDALS = { "GOLD", "SILVER", "BRONZE" };

	@SuppressWarnings("unused")
	private final String eventName;
	private final int numberOfAthletes;
	private final ArrayList<DistanceEvent> distanceEvents;

	/**
	 * Parse the input according to spec, using a default number of
	 * {@link DistanceEvent#NUMBER_OF_ATTEMPTS} attempts.
	 */
	public Event(InputStream in, OutputStream out) throws IOException {
		this(in, out, NUMBER_OF_ATTEMPTS);
	}

	public Event(InputStream in, int numberOfAttempts) throws IOException {
		this(in, null, numberOfAttempts);
	}
	
	/**
	 * Parse the input according to spec.
	 */
	public Event(InputStream in, OutputStream out, int numberOfAttempts) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(in));

		String[] eventAndNumberOfAtheletes = r.readLine().split(
				COMMA_AND_WHITESPACE_REGEX);
		eventName = eventAndNumberOfAtheletes[0];
		numberOfAthletes = Integer.parseInt(eventAndNumberOfAtheletes[1]);

		// start by constructing the distance event objects
		distanceEvents = new ArrayListImpl<>();

		for (int i = 0; i < numberOfAthletes; i++) {
			String[] athleteNameAndCountry = r.readLine().split(
					COMMA_AND_WHITESPACE_REGEX);
			distanceEvents.append(new DistanceEvent(athleteNameAndCountry[0],
					athleteNameAndCountry[1], i, numberOfAttempts));
		}

		int lineNumber = 0;
		String line = null;
		while ((line = r.readLine()) != null) {
			if (line.equals("list")) {
				print("Competitor List", out, false, false);
			} else if (line.equals("rank")) {
				print("Current Ranking", out, true, false);
			} else {
				double distance = Double.parseDouble(line);
				int athleteIndex = lineNumber % numberOfAthletes;
				int numberOfAttempt = lineNumber / numberOfAthletes;
				distanceEvents.get(athleteIndex).setDistance(numberOfAttempt, distance);
				lineNumber++;
			}
		}

		print("Final Results", out, true, true);
	}

	public SortedList<DistanceEvent> getSortedDistanceEvents() {
		return new SortedListImpl<>(distanceEvents);
	}

	/**
	 * Output the list of sorted athletes according to spec.
	 * 
	 * @param out
	 */
	public void print(String heading, OutputStream out, boolean ranked, boolean withMedals) {
		if (out == null) {
			return;
		}
		
		PrintWriter p = new PrintWriter(out);
		p.println(heading);
		//p.println(eventName);
		List<DistanceEvent> listToUse = ranked ? getSortedDistanceEvents()
				: distanceEvents;
		int i = 0;
		for (DistanceEvent d : listToUse) {
			String optionalMedal = withMedals ? (i < MEDALS.length ? " "
					+ MEDALS[i] : "") : "";
			p.println(d + optionalMedal);
			i++;
		}
		p.flush();
	}
}

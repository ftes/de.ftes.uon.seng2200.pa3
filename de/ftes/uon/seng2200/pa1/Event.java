package de.ftes.uon.seng2200.pa1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * An event holds all {@link DistaneEvent}s of all athletes for a competition.
 * @author Fredrik Teschke
 *
 */
public class Event {
	private static final String COMMA_AND_WHITESPACE_REGEX = ",\\s*";
	private static final String[] MEDALS = { "GOLD", "SILVER", "BRONZE" };

	private final String eventName;
	private final int numberOfAthletes;
	private final SortedDistanceEventList sortedDistanceEvents;


	/**
	 * Parse the input according to spec, using a default number of {@link DistanceEvent#NUMBER_OF_ATTEMPTS} attempts.
	 */
	public Event(InputStream in) throws IOException {
		this(in, DistanceEvent.NUMBER_OF_ATTEMPTS);
	}

	/**
	 * Parse the input according to spec.
	 */
	public Event(InputStream in, int numberOfAttempts) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(in));

		String[] eventAndNumberOfAtheletes = r.readLine().split(
				COMMA_AND_WHITESPACE_REGEX);
		eventName = eventAndNumberOfAtheletes[0];
		numberOfAthletes = Integer.parseInt(eventAndNumberOfAtheletes[1]);

		//start by constructing the distance event objects
		DistanceEventList distanceEvents = new DistanceEventList();

		for (int i = 0; i < numberOfAthletes; i++) {
			String[] athleteNameAndCountry = r.readLine().split(
					COMMA_AND_WHITESPACE_REGEX);
			distanceEvents.append(new DistanceEvent(athleteNameAndCountry[0],
					athleteNameAndCountry[1], i));
		}

		for (int i = 0; i < numberOfAttempts * numberOfAthletes; i++) {
			double distance = Double.parseDouble(r.readLine());
			int athleteIndex = i % numberOfAthletes;
			distanceEvents.get(athleteIndex).addDistance(distance);
		}

		// order the DistanceEvents (can only be done now, as all attempts of the athletes have to be known)
		sortedDistanceEvents = new SortedDistanceEventList();
		for (int i = 0; i < numberOfAthletes; i++) {
			sortedDistanceEvents.insertInOrder(distanceEvents.pop());
		}
	}

	public SortedDistanceEventList getSortedDistanceEvents() {
		return sortedDistanceEvents;
	}

	/**
	 * Output the list of sorted athletes according to spec.
	 * @param out
	 */
	public void print(OutputStream out) {
		PrintWriter p = new PrintWriter(out);
		p.println(eventName);
		for (int i = 0; i < numberOfAthletes; i++) {
			DistanceEvent d = sortedDistanceEvents.get(i);
			String optionalMedal = i < MEDALS.length ? " " + MEDALS[i] : "";
			p.println(d + optionalMedal);
		}
		p.flush();
	}
}

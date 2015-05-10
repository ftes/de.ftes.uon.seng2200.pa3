package de.ftes.uon.seng2200.pa3b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Parses and then holds all {@link DistaneEvent}s of all athletes for a
 * competition.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class EventParser<T extends Event<T>, C extends EventCollection<C, T>>
		implements Iterable<C> {
	private static final String COMMA_AND_WHITESPACE_REGEX = ",\\s*";
	private static final String[] MEDALS = { "GOLD", "SILVER", "BRONZE" };

	private final ArrayList<C> eventCollections;

	public static EventParser<?, ?> parse(InputStream in, OutputStream out)
			throws IOException {
		return parse(in, out, null);
	}

	private static int getNumberOfAttempts(Integer customNumber,
			int standardNumber) {
		return customNumber != null ? customNumber : standardNumber;
	}

	private interface EventCollectionFactory<T extends Event<T>, C extends EventCollection<C, T>> {
		C create(String athleteName, String country, int index);
	}

	public static EventParser<?, ?> parse(InputStream in, OutputStream out,
			final Integer customNumberOfAttempts) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(in));
		String[] eventAndNumberOfAtheletes = r.readLine().split(
				COMMA_AND_WHITESPACE_REGEX);
		String eventName = eventAndNumberOfAtheletes[0];
		int numberOfAthletes = Integer.parseInt(eventAndNumberOfAtheletes[1]);
		switch (eventName) {
		case "Hammer Throw":
			return new EventParser<Distance, DistanceCollection>(
					new EventCollectionFactory<Distance, DistanceCollection>() {
						@Override
						public DistanceCollection create(String athleteName,
								String country, int index) {
							return new DistanceCollection(athleteName, country,
									index, getNumberOfAttempts(
											customNumberOfAttempts, 6));
						}
					}, eventName, numberOfAthletes, r, out);
		}
		throw new IllegalArgumentException("Event " + eventName
				+ " is not supported");
	}

	/**
	 * Parse the input according to spec.
	 * 
	 * @throws IOException
	 */
	private EventParser(EventCollectionFactory<T, C> factory, String eventName,
			int numberOfAthletes, BufferedReader r, OutputStream out)
			throws IOException {
		PrintWriter w = null;
		if (out != null) {
			w = new PrintWriter(out);
		}

		// start by constructing the distance event objects
		eventCollections = new ArrayListImpl<>();

		for (int i = 0; i < numberOfAthletes; i++) {
			String[] athleteNameAndCountry = r.readLine().split(
					COMMA_AND_WHITESPACE_REGEX);

			C eventCollection = factory.create(athleteNameAndCountry[0],
					athleteNameAndCountry[1], i);
			eventCollections.append(eventCollection);
		}

		// print event name
		if (w != null)
			w.println(eventName);

		int lineNumber = 0;
		String line = null;
		while ((line = r.readLine()) != null) {
			if (line.equals("list")) {
				print("Competitor List", w, false, false);
			} else if (line.equals("rank")) {
				print("Current Ranking", w, true, false);
			} else {
				float distance = Float.parseFloat(line);
				int athleteIndex = lineNumber % numberOfAthletes;
				int numberOfAttempt = lineNumber / numberOfAthletes;
				eventCollections.get(athleteIndex).addData(numberOfAttempt,
						distance);
				lineNumber++;
			}
		}

		print("Final Results", w, true, true);
		r.close();
		if (w != null)
			w.close();
	}

	public SortedList<C> getSortedDistanceEvents() {
		return new SortedListImpl<>(eventCollections);
	}

	/**
	 * Output the list of sorted athletes according to spec.
	 * 
	 * @param out
	 */
	public void print(String heading, PrintWriter w, boolean ranked,
			boolean withMedals) {
		if (w == null) {
			return;
		}

		w.println(heading);
		// p.println(eventName);
		List<C> listToUse = ranked ? getSortedDistanceEvents()
				: eventCollections;
		int i = 0;
		for (C d : listToUse) {
			String optionalMedal = withMedals ? (i < MEDALS.length ? " "
					+ MEDALS[i] : "") : "";
			w.println(d + optionalMedal);
			i++;
		}
	}

	@Override
	public Iterator<C> iterator() {
		return getSortedDistanceEvents().iterator();
	}
}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/**
 * Parses and then holds all {@link DistaneEvent}s of all athletes for a
 * competition.
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class EventParser<T extends Event<T>> implements Iterable<T> {
	private static final String COMMA_AND_WHITESPACE_REGEX = ",\\s*";
	private static final String[] MEDALS = { "GOLD", "SILVER", "BRONZE" };

	private final ArrayList<T> events;
	private final Class<T> eventClass;
	private final int numberOfAttempts;

	public static EventParser<?> parse(InputStream in, OutputStream out)
			throws IOException {
		return parse(in, out, null);
	}

	public static EventParser<?> parse(InputStream in, OutputStream out,
			final Integer customNumberOfAttempts) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(in));
		String[] eventAndNumberOfAtheletes = r.readLine().split(
				COMMA_AND_WHITESPACE_REGEX);
		String eventName = eventAndNumberOfAtheletes[0];
		int numberOfAthletes = Integer.parseInt(eventAndNumberOfAtheletes[1]);
		switch (eventName) {
		case "Hammer Throw":
		case "Javelin":
			return new EventParser<Distance>(Distance.class, eventName,
					numberOfAthletes, r, out,
					customNumberOfAttempts == null ? 6 : customNumberOfAttempts);
		case "Womens 1000m Time Trial":
		case "Mens 1000m Time Trial":
			return new EventParser<Timed>(Timed.class, eventName,
					numberOfAthletes, r, out, 1);
		default:
			throw new InvalidEventNameException("Event " + eventName
					+ " is not supported");
		}

	}

	/**
	 * Parse the input according to spec.
	 * 
	 * @throws IOException
	 */
	private EventParser(Class<T> eventClass, String eventName,
			int numberOfAthletes, BufferedReader r, OutputStream out,
			int numberOfAttempts) throws IOException {
		this.eventClass = eventClass;
		this.numberOfAttempts = numberOfAttempts;
		PrintWriter w = null;
		if (out != null) {
			w = new PrintWriter(out);
		}

		// start by constructing the distance event objects
		events = new ArrayListImpl<>();

		for (int i = 0; i < numberOfAthletes; i++) {
			String[] athleteNameAndCountry = r.readLine().split(
					COMMA_AND_WHITESPACE_REGEX);

			T event = createEvent(athleteNameAndCountry[0],
					athleteNameAndCountry[1], i);
			events.append(event);
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
				events.get(athleteIndex).setData(distance);
				lineNumber++;
			}
		}

		print("Final Results", w, true, true);
		r.close();
		if (w != null)
			w.close();
	}

	public SortedList<T> getSortedEvents() {
		return new SortedListImpl<>(events);
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
		List<T> listToUse = ranked ? getSortedEvents() : events;
		int i = 0;
		for (T d : listToUse) {
			String optionalMedal = withMedals ? (i < MEDALS.length ? " "
					+ MEDALS[i] : "") : "";
			w.println(d + optionalMedal);
			i++;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return getSortedEvents().iterator();
	}

	private T createEvent(String athleteName, String country, int index) {
		try {
			return eventClass.getConstructor(String.class, String.class,
					int.class, int.class).newInstance(athleteName, country,
					index, numberOfAttempts);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"Create a constructor with parameters athleteName, country, index, numberOfAttempts for "
							+ eventClass.getName());
		} catch (SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// should not occur
			e.printStackTrace();
			return null;
		}
	}
}

package de.ftes.uon.seng2200.pa3b;

public class InvalidEventNameException extends IllegalArgumentException {
	private static final long serialVersionUID = 4342084565193859101L;

	public InvalidEventNameException(String msg) {
		super(msg);
	}
}

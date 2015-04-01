package de.ftes.uon.seng2200.list;

public class ListException extends RuntimeException {
	private static final long serialVersionUID = -2919995726670519039L;

	public ListException(String msg, Throwable caught) {
		super(msg, caught);
	}
	
	public ListException(String msg) {
		super(msg);
	}
}

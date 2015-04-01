package de.ftes.uon.seng2200.list;

public class ListEmptyException extends ListException {
	private static final long serialVersionUID = 1915286987001385379L;

	public ListEmptyException(String msg, Throwable caught) {
		super(msg, caught);
	}
	
	public ListEmptyException(String msg) {
		super(msg);
	}
}

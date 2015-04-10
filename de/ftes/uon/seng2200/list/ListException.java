package de.ftes.uon.seng2200.list;

/**
 * 
 * @author Fredrik Teschke (3228760)
 *
 */
public class ListException extends RuntimeException {
	private static final long serialVersionUID = -2919995726670519039L;

	public ListException(String msg, Throwable caught) {
		super(msg, caught);
	}
	
	public ListException(String msg) {
		super(msg);
	}
}

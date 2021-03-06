package org.etl.tools.data.generation.persistence;
/**
 * 
 * A checked exception that is thrown when persistence operation fails.
 * 
 * @author Lachezar.Nedelchev
 *
 */
public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}

}

package org.etl.tools.data.generation.persistence;

/**
 * 
 * The root class that defines persistence operations on an object of a type T
 * implementing <code>{@link PersistenceProvider}</code> interface.
 * 
 * @author Lachezar.Nedelchev
 *
 * @param <T>
 *            the type of the object.
 */
public abstract class AbstractPersistenceProvider<T> implements PersistenceProvider<T> {

	private final String fileName;
	private final Class<T> type;

	/**
	 * 
	 * Root constructor of persistence providers.
	 * 
	 * @param fileName
	 *            the name of the file we read form or write to.
	 * @param type
	 *            the type of the object.
	 */
	public AbstractPersistenceProvider(String fileName, Class<T> type) {
		super();
		this.fileName = fileName;
		this.type = type;
	}

	/**
	 * 
	 * @return the name of the source/target file.
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 
	 * @return the type of the object
	 */
	public Class<T> getType() {
		return this.type;
	}

}

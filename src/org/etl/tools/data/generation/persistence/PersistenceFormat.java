package org.etl.tools.data.generation.persistence;

/**
 * 
 * Interface that declares how Persistent providers are constructed.
 * 
 * @author Lachezar.Nedelchev
 *
 */
public interface PersistenceFormat {
	/**
	 * Creates a new instance of a PersistenceProvider
	 * 
	 * @param fileName
	 *            file name bound to this concrete instance of the
	 *            PersistenceProvider
	 * @param type
	 *            type of persistent object
	 * @return a new instance of a PersistenceProvider
	 */
	public <T> PersistenceProvider<T> create(String fileName, Class<T> type);

	/**
	 * The fully qualified class name of the PersistenceProvider.
	 * 
	 * @return String representation of the fully qualified class name of the
	 *         PersistenceProvider.
	 */
	public String getClassName();

}
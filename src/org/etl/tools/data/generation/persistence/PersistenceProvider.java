package org.etl.tools.data.generation.persistence;

/**
 * 
 * Interface that declares persistent operations (read and write) to an object
 * of type T.
 * 
 * @author Lachezar.Nedelchev
 *
 * @param <T>
 *            The type of the object that is persisted/read.
 */
public interface PersistenceProvider<T> {
	/**
	 * 
	 * Reads a object of type T to a file.
	 * 
	 * @param type
	 *            The type of the object
	 * @return An object of a type T if successfully read.
	 * @throws PersistenceException
	 *             when reading fails.
	 */
	public T read(Class<T> type) throws PersistenceException;

	/**
	 * 
	 * Writes a object of type T to a file.
	 * 
	 * @param object
	 *            The object to be written.
	 * @throws PersistenceException
	 *             When writing fails.
	 */
	public void write(T object) throws PersistenceException;
}

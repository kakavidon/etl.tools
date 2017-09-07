package org.etl.tools.data.generation.persistence;

public interface PersistenceProvider<T> {
	
	T read(Class<T> type) throws PersistenceException;

	void write(T object) throws PersistenceException;
}

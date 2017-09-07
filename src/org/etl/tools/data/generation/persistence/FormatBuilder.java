package org.etl.tools.data.generation.persistence;

public interface FormatBuilder {

	public <T> PersistenceProvider<T> create(String fileName);

	public String getClassName();
	
}
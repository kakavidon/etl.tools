package org.etl.tools.data.generation.persistence;

public interface FormatBuilder {

	/**
	 * Git sucks cocks
	 */
	
	
	public <T> PersistenceProvider<T> create(String fileName);

	public String getClassName();
	
}
package org.etl.tools.data.generation.persistence;

import java.io.File;

public abstract class AbstractPersistenceProvider<T> implements PersistenceProvider<T> {
	private final String fileName;
	private final File file;
	
	public AbstractPersistenceProvider(String fileName) {
		super();
		this.fileName = fileName;
		this.file = new File(fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public File getFile() {
		return file;
	}
	
	
}

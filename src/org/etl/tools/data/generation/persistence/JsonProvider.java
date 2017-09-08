package org.etl.tools.data.generation.persistence;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Concrete implementation of <code>{@link AbstractPersistenceProvider}</code>
 * that supports persistence operations in JSON file format.
 * 
 * @author Lachezar.Nedelchev
 *
 * @param <T>
 *            the type of the persistent object.
 */
public class JsonProvider<T> extends AbstractPersistenceProvider<T> {
	private final ObjectMapper mapper;
	private final File file;

	/**
	 * Constructor that initializes all necessary things regarding persistent
	 * operations to a file in JSON format.
	 * 
	 * @param fileName
	 *            the name of the file
	 * @param type
	 *            the type of the persistent object.
	 */
	public JsonProvider(String fileName, Class<T> type) {
		super(fileName, type);
		this.mapper = new ObjectMapper();
		this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		this.file = new File(fileName);
	}

	/**
	 * 
	 * Reads an object of an type <T> from a JSON file;
	 * 
	 * @return object of an type <T>
	 * @throws PersistenceException
	 *             if the object cannot be read for various reasons ( i.e. no such
	 *             file exists, permission issues etc.)
	 */
	@Override
	public T read() throws PersistenceException {
		try {
			return getMapper().readValue(getFile(), getType());
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * 
	 * Writes an object of an type <T> to a file in JSON format.
	 * 
	 * @param object
	 *            of an type <T>
	 * @throws PersistenceException
	 *             if the object cannot be written for various reasons ( i.e. no
	 *             such file exists, permission issues etc.)
	 */
	@Override
	public void write(T object) throws PersistenceException {
		try {
			getMapper().writeValue(getFile(), object);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * 
	 * Returns a Jackson ObjectMapper instance.
	 * 
	 * @return an instance of com.fasterxml.jackson.databind.ObjectMapper used for
	 *         applying the persistent operations.
	 */
	public ObjectMapper getMapper() {
		return this.mapper;
	}

	/**
	 * 
	 * Returns a File instance.
	 * 
	 * @return an instance of java.io.file bound to <code>fileName</code> field
	 */
	private File getFile() {
		return this.file;
	}

};
package org.etl.tools.data.generation.persistence;

import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProvider<T> extends AbstractPersistenceProvider<T> {
	private ObjectMapper mapper;

	public JsonProvider(String fileName) {
		super(fileName);
		this.mapper = new ObjectMapper();
		this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	}

	@Override
	public T read(Class<T> type) throws PersistenceException {
		try {
			return getMapper().readValue(getFile(), type);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void write(T object) throws PersistenceException {
		try {
			getMapper().writeValue(getFile(), object);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	public ObjectMapper getMapper() {
		return this.mapper;
	}

};
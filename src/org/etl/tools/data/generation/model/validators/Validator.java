package org.etl.tools.data.generation.model.validators;

public abstract class Validator<T, V> {
	protected final T model;
	
	public Validator(T model) {
		super();
		this.model = model;
	}

	public abstract V validate();

	public T getModel() {
		return model;
	}
	
	
}

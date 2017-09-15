package org.etl.tools.data.generation.model.validators;

import java.math.BigInteger;

public abstract class NumberValidator<T extends Number> {
	private final T value;
	private boolean nullAllowed = false;

	public NumberValidator(T value) {
		this.value = value;

	}

	public NumberValidator<T> notNull() {
		if (value == null) {
			throw new IllegalStateException("Value should not be null.");
		}
		return this;
	}

	public NumberValidator<T> allowNull() {
		this.nullAllowed = true;
		return this;
	}

	public NumberValidator<T> isValid() {
		if (isNullAllowed() && getValue() == null) {
			return this;
		}
		if (value != null) {
			parse(value);
			inTypeRange(BigInteger.valueOf((Long) value));
			return this;
		}

		return this;
	}

	protected void parse(T value) {
		final String string = value.toString().toLowerCase();
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new IllegalStateException(String.format("Value should not be a valid digit between %d and %d.",
					Integer.MIN_VALUE, Integer.MAX_VALUE));
		}
	}

	public T validate() {
		return this.value;
	}

	protected abstract void inTypeRange(BigInteger value) throws IllegalStateException;

	public abstract NumberValidator<T> greaterThan(T otherValue);

	public abstract NumberValidator<T> lessThan(T otherValue);

	public boolean isNullAllowed() {
		return this.nullAllowed;
	}
	
	protected T getValue(){
		return this.value;
	}


}
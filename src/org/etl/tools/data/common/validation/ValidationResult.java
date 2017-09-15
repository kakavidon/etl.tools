package org.etl.tools.data.common.validation;

public final class ValidationResult<T> {
	private boolean valid;
	private String messsage;
	private T testedObject;

	public static <T> ValidationResult<T> ok(T testedObject) {
		return new ValidationResult<T>(testedObject, true, null);
	}

	public static <T> ValidationResult<T> fail(T testedObject, String message) {
		return new ValidationResult<T>(testedObject, false, message);
	}

	private ValidationResult(T testedObject, boolean valid, String messsage) {
		this.testedObject = testedObject;
		this.valid = valid;
		this.messsage = messsage;
	}

	public boolean isValid() {
		return valid;
	}

	public T throwIfInvalid() {
		if (!isValid()) {
			throw new IllegalArgumentException(getMesssage());
		}
		return this.testedObject;
	}

	public T throwIfInvalid(String fieldName) {
		if (!isValid()) {
			throw new IllegalArgumentException(fieldName + " : " + getMesssage());
		}
		return this.testedObject;
	}

	public String getMesssage() {
		return messsage;
	}


}

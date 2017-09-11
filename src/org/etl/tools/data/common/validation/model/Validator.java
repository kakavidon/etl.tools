package org.etl.tools.data.common.validation.model;

public interface Validator<T> {
	public void validate (T model);
}

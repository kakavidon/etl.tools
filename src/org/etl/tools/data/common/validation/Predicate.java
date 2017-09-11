package org.etl.tools.data.common.validation;

public interface Predicate<T> {
	public boolean test(T value);
}

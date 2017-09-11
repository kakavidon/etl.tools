package org.etl.tools.data.common.validation;

public abstract class AbstractValidation<K> {

	public abstract ValidationResult test(K param);

	public AbstractValidation<K> and(final AbstractValidation<K> other) {
		return new AbstractValidation<K>() {
			@Override
			public ValidationResult test(K param) {
				ValidationResult firstResult = AbstractValidation.this.test(param);
				return !firstResult.isValid() ? firstResult : other.test(param);
			}
		};
	}

	public AbstractValidation<K> or(final AbstractValidation<K> other) {
		return new AbstractValidation<K>() {
			@Override
			public ValidationResult test(K param) {
				ValidationResult firstResult = AbstractValidation.this.test(param);
				return firstResult.isValid() ? firstResult : other.test(param);
			}
		};
	}

}
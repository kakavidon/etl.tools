package org.etl.tools.data.common.validation;

import java.util.Objects;

public abstract class AbstractValidation<K> {

	public abstract ValidationResult<K> test(K param);

	public AbstractValidation<K> and(final AbstractValidation<K> other) {
		Objects.nonNull(other);
		return new AbstractValidation<K>() {
			@Override
			public ValidationResult<K> test(K param) {
				ValidationResult<K> firstResult = AbstractValidation.this.test(param);
				return !firstResult.isValid() ? firstResult : other.test(param);
			}
		};
	}

	public AbstractValidation<K> or(final AbstractValidation<K> other) {
		Objects.nonNull(other);
		return new AbstractValidation<K>() {
			@Override
			public ValidationResult<K> test(K param) {
				ValidationResult<K> firstResult = AbstractValidation.this.test(param);
				return firstResult.isValid() ? firstResult : other.test(param);
			}
		};
	}
	
//	public AbstractValidation<K> negate() {
//		return new AbstractValidation<K>() {
//			@Override
//			public ValidationResult test(K param) {
//				ValidationResult firstResult = AbstractValidation.this.test(param);
//				return firstResult.isValid() ?  ValidationResult.fail("") :  ValidationResult.ok() ;
//			}
//		};
//	}
}
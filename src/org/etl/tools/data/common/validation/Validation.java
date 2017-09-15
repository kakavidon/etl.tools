package org.etl.tools.data.common.validation;

public class Validation<K> extends AbstractValidation<K> {

	private Predicate<K> predicate;
	private String onErrorMessage;

	public static <K> Validation<K> from(Predicate<K> predicate, String onErrorMessage) {
		return new Validation<K>(predicate, onErrorMessage);
	}

	private Validation(Predicate<K> predicate, String onErrorMessage) {
		this.predicate = predicate;
		this.onErrorMessage = onErrorMessage;
	}

	@Override
	public ValidationResult<K> test(K param) {
		return predicate.test(param) ? ValidationResult.ok(param) : ValidationResult.fail(param, onErrorMessage);
	}
}
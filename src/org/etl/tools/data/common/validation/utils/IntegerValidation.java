package org.etl.tools.data.common.validation.utils;

import static java.lang.String.format;

import org.etl.tools.data.common.validation.AbstractValidation;
import org.etl.tools.data.common.validation.Predicate;
import org.etl.tools.data.common.validation.Validation;

public final class IntegerValidation {
	public static AbstractValidation<Integer> lowerThan(final int max) {
		return Validation.from(new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				return i < max;
			}
		}, format("must be lower than %s.", max));
	}

	public static AbstractValidation<Integer> greaterThan(final int min) {
		return Validation.from(new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				return i > min;
			}
		}, format("must be greater than %s.", min));
	}

	public static AbstractValidation<Integer> intBetween(int min, int max) {
		return greaterThan(min).and(lowerThan(max));
	}
}

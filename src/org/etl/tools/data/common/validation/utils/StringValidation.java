package org.etl.tools.data.common.validation.utils;

import static java.lang.String.format;

import org.etl.tools.data.common.validation.AbstractValidation;
import org.etl.tools.data.common.validation.Predicate;
import org.etl.tools.data.common.validation.Validation;

public final class StringValidation{

	public static AbstractValidation<String> notEmpty() {
		return Validation.from(new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return !s.isEmpty();
			}
		}, "must not be null.");
	}

	public static AbstractValidation<String> STR_NOT_NULL = Validation.from(new Predicate<String>() {
		@Override
		public boolean test(String s) {
			return s != null;
		}
	}, "must not be null.");

	public static AbstractValidation<String> moreThan(final int size) {
		return Validation.from(new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return s.length() >= size;
			}
		}, format("must have more than %s chars.", size));
	}

	public static AbstractValidation<String> lessThan(final int size) {
		return Validation.from(new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return s.length() <= size;
			}
		}, format("must have less than %s chars.", size));
	}

	public static AbstractValidation<String> between(int minSize, int maxSize) {
		return moreThan(minSize).and(lessThan(maxSize));
	}

	public static Validation<String> contains(final String c) {
		return Validation.from(new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return s.contains(c);
			}
		}, format("must contain %s", c));
	}
	
//	public static AbstractValidation<String> STR_NOT_NULLNOT_NULL = Validation.from(new Predicate<String>() {
//		@Override
//		public boolean test(String s) {
//			return s != null;
//		}
//	}, "must not be null.");
}

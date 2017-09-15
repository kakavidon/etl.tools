package org.etl.tools.data.common.validation.utils;

import static java.lang.String.format;
import static org.etl.tools.data.common.validation.utils.IntegerValidation.INT_IS_NULL;
import static org.etl.tools.data.common.validation.utils.IntegerValidation.greaterThan;
import static org.etl.tools.data.common.validation.utils.IntegerValidation.lowerThan;

import org.etl.tools.data.common.validation.AbstractValidation;
import org.etl.tools.data.common.validation.AllowNullValidation;
import org.etl.tools.data.common.validation.BiAbstractValidation;
import org.etl.tools.data.common.validation.BiPredicate;
import org.etl.tools.data.common.validation.BiValidation;
import org.etl.tools.data.common.validation.Predicate;
import org.etl.tools.data.common.validation.Validation;

public final class IntegerValidation {

	public static AbstractValidation<Integer> notNull = Validation.from(new Predicate<Integer>() {
		@Override
		public boolean test(Integer s) {
			return s != null;
		}
	}, "must not be null.");

	public static AbstractValidation<Integer> isValid(final String value) {
		return Validation.from(new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				try {
					Integer.valueOf(value);
				} catch (NumberFormatException e) {
					return false;
				}
				return true;
			}
		}, format("%s is invalid number.", value));
	}

	public static AbstractValidation<Integer> lowerThan(final int max) {
		return Validation.from(new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				return i < max;
			}
		}, format("must be lower than %s.", max));
	}

	public static AbstractValidation<Integer> lowerOrEqual(final int max) {
		return Validation.from(new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				return i <= max;
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

	public static AbstractValidation<Integer> greaterOrEqual(final int min) {
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

	public static AbstractValidation<Integer> betweenInclusive(int min, int max) {
		return greaterOrEqual(min).and(lowerOrEqual(max));
	}

	public static AbstractValidation<Integer> equalTo(int x) {
		return Validation.from(new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				return i == x;
			}
		}, format("should be equal to %s.", x));
	}

	public static AbstractValidation<Integer> notEqual(int x) {
		return Validation.from(new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				return i != x;
			}
		}, format("Should not be equal to %s.", x));
	}

	public static AbstractValidation<Integer> INT_NOT_NULL = Validation.from(new Predicate<Integer>() {
		@Override
		public boolean test(Integer s) {
			return s != null;
		}
	}, "must not be null.");

	public static AbstractValidation<Integer> INT_IS_NULL = AllowNullValidation.from(new Predicate<Integer>() {
		@Override
		public boolean test(Integer s) {
			return s != null;
		}
	});

	public static BiAbstractValidation<Integer, Integer> boundaryCheck() {

		return BiValidation.from(new BiPredicate<Integer, Integer>() {
			@Override
			public boolean test(Integer s, Integer t) {
				return s < t;
			}
		}, "To value shoud be greater than from.");
	}

	public static BiAbstractValidation<Integer, Integer> INT_BOTH_NOT_NULL = BiValidation
			.from(new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer s, Integer t) {
					return s != null && t != null;
				}
			}, "must both be null.");
	
	public static Integer parseInteger(final Integer value) {
		return INT_IS_NULL.or(lowerThan(Integer.MIN_VALUE).and(greaterThan(Integer.MAX_VALUE))).test(value).throwIfInvalid();
	}


}

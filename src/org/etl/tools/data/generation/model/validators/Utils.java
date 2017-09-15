package org.etl.tools.data.generation.model.validators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.etl.tools.data.generation.model.IntegerModel;
import org.etl.tools.data.generation.model.generators.ValueFactory;

public final class Utils {
	/**
	 * Suspicious logic
	 */
	@SuppressWarnings("unused")
	private String value;
	@SuppressWarnings("unused")
	private Object object;
	@SuppressWarnings("unused")
	private Integer integer;

	private Utils(StringValidator validator) {
		this.value = validator.value;
	}

	private Utils(Validator validator) {
		this.object = validator.value;
	}

	public static class IntegerValidator {
		private final Integer from;
		private Integer to;

		public IntegerValidator(final Integer value) {
			this.from = value;
		}

		public IntegerValidator(Integer from, Integer to) {
			this.from = from;
			this.to = to;
		}

		public IntegerValidator notNull() {
			if (from == null) {
				throw new IllegalStateException("Value should not be null.");
			}
			return this;
		}

		public IntegerValidator allowNull() {
			return this;
		}

		public IntegerValidator isValid() {

			if (from == null) {
				return this;
			}

			final String string = from.toString().toLowerCase();
			try {
				Integer.parseInt(string);
			} catch (NumberFormatException e) {
				throw new IllegalStateException(String.format("Value should not be a valid digit between %d and %d.",
						Integer.MIN_VALUE, Integer.MAX_VALUE));
			}
			return this;
		}

		public IntegerValidator check() {
			if ((from == null && to == null) || (from != null && to == null) || (from == null && to != null)) {
				return this;
			}

			if (from >= to) {
				throw new IllegalStateException(
						String.format("From value[%d] should be greater than to value [%d].", from, to));
			}
			return this;
		}

		public java.lang.Integer validate() {
			return this.from;
		}

	}

	public final static class BigDecimalValidator extends NumberValidator<BigDecimal> {
		public BigDecimalValidator(BigDecimal value) {
			super(value);

		}

		@Override
		public NumberValidator<BigDecimal> greaterThan(BigDecimal otherValue) {
			if (isNullAllowed() && getValue() == null) {
				return this;
			}
			if (otherValue.compareTo(getValue()) == -1) {
				throw new IllegalStateException("To Value should be less than from value.");
			}
			return this;

		}

		@Override
		public NumberValidator<BigDecimal> lessThan(BigDecimal otherValue) {
			if (isNullAllowed() && getValue() == null) {
				return this;
			}
			if (otherValue.compareTo(getValue()) == 1) {
				throw new IllegalStateException("From Value should  be greater than to value.");
			}
			return this;
		}

		@Override
		protected void inTypeRange(BigInteger value) throws IllegalStateException {
			final BigInteger min = BigInteger.valueOf(Integer.MIN_VALUE * Integer.MIN_VALUE);
			final BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE * Integer.MAX_VALUE);

			if (value.compareTo(max) == 1 || value.compareTo(min) == -1) {
				final String message = String.format("Value[%d] should be in [%d ,%d].", value, min, max);
				throw new IllegalStateException(message);
			}

		}
	}

	public final static class BigIntegerValidator extends NumberValidator<BigInteger> {

		public BigIntegerValidator(BigInteger value) {
			super(value);

		}

		@Override
		protected void inTypeRange(BigInteger value) throws IllegalStateException {
			final BigInteger min = BigInteger.valueOf(Integer.MIN_VALUE * Integer.MIN_VALUE);
			final BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE * Integer.MAX_VALUE);

			if (value.compareTo(max) == 1 || value.compareTo(min) == -1) {
				final String message = String.format("Value[%d] should be in [%d ,%d].", value, min, max);
				throw new IllegalStateException(message);
			}

		}

		@Override
		public NumberValidator<BigInteger> greaterThan(BigInteger otherValue) {
			if (isNullAllowed() && getValue() == null) {
				return this;
			}
			if (otherValue.compareTo(getValue()) == -1) {
				throw new IllegalStateException("To Value should be less than from value.");
			}
			return this;

		}

		@Override
		public NumberValidator<BigInteger> lessThan(BigInteger otherValue) {
			if (isNullAllowed() && getValue() == null) {
				return this;
			}
			if (otherValue.compareTo(getValue()) == 1) {
				throw new IllegalStateException("From Value should  be greater than to value.");
			}
			return this;
		}

	}

	public final static class ShortValidator extends NumberValidator<Short> {

		public ShortValidator(Short value) {
			super(value);
		}

		@Override
		protected void inTypeRange(BigInteger value) throws IllegalStateException {
			final BigInteger min = BigInteger.valueOf(Short.MIN_VALUE);
			final BigInteger max = BigInteger.valueOf(Short.MAX_VALUE);

			if (value.compareTo(max) == 1 || value.compareTo(min) == -1) {
				final String message = String.format("Value[%d] should be in [%d ,%d].", value, min, max);
				throw new IllegalStateException(message);
			}

		}

		@Override
		public NumberValidator<Short> greaterThan(Short otherValue) {
			if (isNullAllowed() && getValue() == null) {
				return this;
			}
			if (otherValue <= getValue()) {
				throw new IllegalStateException("To Value should  be less than from value.");
			}
			return this;
		}

		@Override
		public NumberValidator<Short> lessThan(Short otherValue) {
			if (isNullAllowed() && getValue() == null) {
				return this;
			}
			if (otherValue >= getValue()) {
				throw new IllegalStateException("From Value should  be greater than to value.");
			}
			return this;
		}

	}

	public static class StringValidator {
		private final String value;

		public StringValidator(final String value) {
			this.value = value;
		}

		public StringValidator notNull() {
			if (value == null) {
				throw new IllegalStateException("Value should not be null.");
			}
			return this;
		}

		public StringValidator notEmpty() {
			if (value == null) {
				return this;
			}
			if (value.trim().isEmpty()) {
				throw new IllegalStateException("Value should not be empty or should not only spaces.");
			}
			return this;
		}

		public StringValidator inRange(final int min, final int max) {
			if (value == null) {
				return this;
			}
			if (value.length() < min || value.length() > max) {
				throw new IllegalStateException(
						String.format("Value should be between %d and %d characters in length.", min, max));
			}
			return this;
		}

		public String validate() {
			return this.value;
		}

		public StringValidator longerThan(final int min) {
			if (value == null) {
				return this;
			}
			if (value.trim().length() < min) {
				throw new IllegalStateException(String.format("Value should be longer than %d.", min));
			}
			return this;

		}

		public StringValidator valueIsAllowed(final List<String> allowedValues) {
			if (value == null) {
				return this;
			}
			if (!allowedValues.contains(value)) {
				throw new IllegalStateException(String.format("Value \"%s\" is invalid attribute.", value));
			}
			return this;

		}

		public StringValidator allowNull() {
			return this;
		}
	}

	public static class Validator {
		private Object value;

		public Validator(final Object value) {
			this.value = value;
		}

		public Validator notNull() {
			if (value == null) {
				throw new IllegalStateException("Value should not be null.");
			}
			return this;
		}

		public Object validate() {
			return this.value;
		}

	}

	public static class BooleanValidator {

		private java.lang.Boolean value;

		public BooleanValidator(final java.lang.Boolean value) {
			this.value = value;
		}

		public BooleanValidator allowNull() {
			return this;
		}

		public BooleanValidator notNull() {
			if (value == null) {
				throw new IllegalStateException("Value should not be null.");
			}
			return this;
		}

		public BooleanValidator isValid() {

			if (value == null) {
				return this;
			}
			final List<String> bools = Arrays.asList("true", "false");
			final String string = value.toString().toLowerCase();
			if (!bools.contains(string)) {
				throw new IllegalStateException("Value should not be true or false.");
			}
			return this;
		}

		public java.lang.Boolean validate() {
			return this.value;
		}

	}

	public static Validator with(final java.lang.Object value) {
		return new Validator(value);
	}

	public static StringValidator with(final java.lang.String value) {
		return new StringValidator(value);
	}

	public static BooleanValidator with(final java.lang.Boolean value) {
		return new BooleanValidator(value);
	}

	public static IntegerValidator with(final java.lang.Integer value) {
		return new IntegerValidator(value);
	}

	public static ShortValidator with(final java.lang.Short value) {
		return new ShortValidator(value);
	}

	public static BigIntegerValidator with(final BigInteger value) {
		return new BigIntegerValidator(value);
	}

	public static BigDecimalValidator with(final BigDecimal value) {
		return new BigDecimalValidator(value);
	}

	public static IntegerValidator range(final java.lang.Integer from, final java.lang.Integer to) {
		return new IntegerValidator(from, to);
	}

	public static void main(String[] args) {
		ValueFactory.init();
		final IntegerModel integerModel = new IntegerModel("PEdko");
//		// integerModel.setConstantValue(100);
//		integerModel.setDefaultValue(20);
//		integerModel.setNullable(true);
//		integerModel.setSigned(false);
//		integerModel.setFrom(20);
//		integerModel.setTo(30);
//		final IntegerValidator validko = new IntegerValidator(
//				integerModel);
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(validko.validate().getValue());
//		}

	}
}

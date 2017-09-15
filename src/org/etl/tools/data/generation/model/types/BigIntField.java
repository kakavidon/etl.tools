package org.etl.tools.data.generation.model.types;

import java.math.BigInteger;

import org.etl.tools.data.generation.model.generators.ValueFactory;

public class BigIntField extends Field<BigInteger> {
	private final BigInteger from;
	private final BigInteger to;
	private java.lang.Boolean signed = false;
	
	protected BigIntField(BigIntBuilder builder) {
		super(builder.baseBuilder);
		this.signed = builder.signed;
		this.from = builder.from;
		this.to = builder.to;
	}
	
	public static class BigIntBuilder implements FieldBuilder<BigIntBuilder, BigInteger> {
		private Field.Builder<BigInteger> baseBuilder;
		private Boolean signed = false;
		private BigInteger from;
		private BigInteger to;

		public BigIntBuilder(java.lang.String name) {
			baseBuilder = new Field.Builder<BigInteger>(name);
		}

		@Override
		public BigIntBuilder constantValue(BigInteger constantValue) {
			baseBuilder.constantValue(constantValue);
			return this;
		}

		@Override
		public BigIntBuilder defaultValue(BigInteger defaultValue) {
			baseBuilder.defaultValue(defaultValue);
			return this;
		}

		@Override
		public BigIntBuilder nullable(boolean nullable) {
			baseBuilder.nullable(nullable);
			return this;
		}

		public BigIntBuilder signed(boolean signed) {
			this.signed = signed;
			return this;
		}

		public BigIntBuilder from(BigInteger from) {
			this.from = from;
			return this;
		}

		public BigIntBuilder to(BigInteger to) {
			this.to = to;
			return this;
		}

		public BigIntField build() {
			return new BigIntField(this);
		}
	}

	@Override
	public String getValue() {
		final BigInteger value = ValueFactory.BIGINT.create(this);
		return String.valueOf(value);
	}

	public boolean isSigned() {
		return this.signed;
	}

	public BigInteger getFrom() {
		return this.from;
	}

	public BigInteger getTo() {
		return this.to;
	}
}

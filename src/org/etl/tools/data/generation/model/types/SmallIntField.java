package org.etl.tools.data.generation.model.types;

import org.etl.tools.data.generation.model.generators.ValueFactory;

public class SmallIntField extends Field<java.lang.Short> {
	private final java.lang.Short from;
	private final java.lang.Short to;
	private java.lang.Boolean signed = false;

	protected SmallIntField(SmallIntBuilder builder) {
		super(builder.baseBuilder);
		this.signed = builder.signed;
		this.from = builder.from;
		this.to = builder.to;
	}

	public static class SmallIntBuilder implements FieldBuilder<SmallIntBuilder, java.lang.Short> {
		private Field.Builder<java.lang.Short> baseBuilder;
		private java.lang.Boolean signed = false;
		private java.lang.Short from;
		private java.lang.Short to;

		public SmallIntBuilder(java.lang.String name) {
			baseBuilder = new Field.Builder<java.lang.Short>(name);
		}

		@Override
		public SmallIntBuilder constantValue(java.lang.Short constantValue) {
			baseBuilder.constantValue(constantValue);
			return this;
		}

		@Override
		public SmallIntBuilder defaultValue(java.lang.Short defaultValue) {
			baseBuilder.defaultValue(defaultValue);
			return this;
		}

		@Override
		public SmallIntBuilder nullable(boolean nullable) {
			baseBuilder.nullable(nullable);
			return this;
		}

		public SmallIntBuilder signed(boolean signed) {
			this.signed = signed;
			return this;
		}

		public SmallIntBuilder from(java.lang.Short from) {
			this.from = from;
			return this;
		}

		public SmallIntBuilder to(java.lang.Short to) {
			this.to = to;
			return this;
		}

		public SmallIntField build() {
			return new SmallIntField(this);
		}
	}

	@Override
	public String getValue() {
		final java.lang.Short value = ValueFactory.SMALLINT.create(this);
		return String.valueOf(value);
	}

	public boolean isSigned() {
		return this.signed;
	}

	public java.lang.Short getFrom() {
		return this.from;
	}

	public java.lang.Short getTo() {
		return this.to;
	}


}

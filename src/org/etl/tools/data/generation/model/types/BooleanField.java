package org.etl.tools.data.generation.model.types;

import org.etl.tools.data.generation.model.generators.ValueFactory;

public final class BooleanField extends Field<java.lang.Boolean> {
	private String value;

	protected BooleanField(BooleanBuilder builder) {
		super(builder.baseBuilder);
	}

	public static class BooleanBuilder implements FieldBuilder<BooleanBuilder, java.lang.Boolean> {

		private Field.Builder<java.lang.Boolean> baseBuilder;

		protected BooleanField constantValue;
		protected BooleanField defaultValue;
		protected String name;
		protected boolean nullable;

		@Override
		public BooleanBuilder constantValue(java.lang.Boolean constantValue) {
			baseBuilder.constantValue(constantValue);
			return this;
		}

		@Override
		public BooleanBuilder defaultValue(java.lang.Boolean defaultValue) {
			baseBuilder.defaultValue(defaultValue);
			return this;
		}

		@Override
		public BooleanBuilder nullable(boolean nullable) {
			baseBuilder.nullable(nullable);
			return this;
		}

		public BooleanBuilder(String name) {
			baseBuilder = new Field.Builder<java.lang.Boolean>(name);
		}

		public BooleanField build() {
			return new BooleanField(this);
		}

	}

	@Override
	public String getValue() {
		final java.lang.Boolean value1 = ValueFactory.BOOLEAN.create(this);
		return String.valueOf(value1);
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Boolean [value=");
		builder2.append(value);
		builder2.append(", toString()=");
		builder2.append(super.toString());
		builder2.append("]");
		return builder2.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (value != null ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof BooleanField)) {
			return false;
		}
		BooleanField other = (BooleanField) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}

}

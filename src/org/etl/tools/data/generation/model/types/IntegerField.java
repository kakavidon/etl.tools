package org.etl.tools.data.generation.model.types;

import org.etl.tools.data.generation.model.generators.ValueFactory;

public class IntegerField extends Field<java.lang.Integer> {

	private final java.lang.Integer from;
	private final java.lang.Integer to;
	private java.lang.Boolean signed = false;

	protected IntegerField(IntegerBuilder builder) {
		super(builder.baseBuilder);
		this.signed = builder.signed;
		this.from = builder.from;
		this.to = builder.to;
	}

	public static class IntegerBuilder implements FieldBuilder<IntegerBuilder, java.lang.Integer> {
		private Field.Builder<java.lang.Integer> baseBuilder;
		private java.lang.Boolean signed = false;
		private java.lang.Integer from;
		private java.lang.Integer to;

		public IntegerBuilder(java.lang.String name) {
			baseBuilder = new Field.Builder<java.lang.Integer>(name);
		}

		@Override
		public IntegerBuilder constantValue(java.lang.Integer constantValue) {
			baseBuilder.constantValue(constantValue);
			return this;
		}

		@Override
		public IntegerBuilder defaultValue(java.lang.Integer defaultValue) {
			baseBuilder.defaultValue(defaultValue);
			return this;
		}

		@Override
		public IntegerBuilder nullable(boolean nullable) {
			baseBuilder.nullable(nullable);
			return this;
		}

		public IntegerBuilder signed(boolean signed) {
			this.signed = signed;
			return this;
		}

		public IntegerBuilder from(java.lang.Integer from) {
			this.from = from;
			return this;
		}

		public IntegerBuilder to(java.lang.Integer to) {
			this.to = to;
			return this;
		}

		public IntegerField build() {
			return new IntegerField(this);
		}
	}

	@Override
	public String getValue() {
		final java.lang.Integer value = ValueFactory.INTEGER.create(this);
		return String.valueOf(value);
	}

	public boolean isSigned() {
		return this.signed;
	}

	public java.lang.Integer getFrom() {
		return this.from;
	}

	public java.lang.Integer getTo() {
		return this.to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((signed == null) ? 0 : signed.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		if (!(obj instanceof IntegerField)) {
			return false;
		}
		IntegerField other = (IntegerField) obj;
		if (from == null) {
			if (other.from != null) {
				return false;
			}
		} else if (!from.equals(other.from)) {
			return false;
		}
		if (signed == null) {
			if (other.signed != null) {
				return false;
			}
		} else if (!signed.equals(other.signed)) {
			return false;
		}
		if (to == null) {
			if (other.to != null) {
				return false;
			}
		} else if (!to.equals(other.to)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("IntegerField [from=");
		builder2.append(from);
		builder2.append(", to=");
		builder2.append(to);
		builder2.append(", signed=");
		builder2.append(signed);
		builder2.append(super.toString());
		builder2.append("]");
		return builder2.toString();
	}

	public static void main(String[] args) {
		final IntegerBuilder field = FieldFactory.INTEGER.create("amount");

		final long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			final String value = field.from(-1000).to(2000).nullable(true).signed(true).build().getValue();
			System.out.println(value);
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}

package org.etl.tools.data.generation.model.types;

import java.math.BigDecimal;

import org.etl.tools.data.generation.model.generators.ValueFactory;

public class DecimalField extends Field<BigDecimal> {

	private final BigDecimal from;
	private final BigDecimal to;
	private final Integer precision;
	private Boolean signed = false;

	protected DecimalField(DecimalBuilder builder) {
		super(builder.baseBuilder);
		this.signed = builder.signed;
		this.from = builder.from;
		this.to = builder.to;
		this.precision = builder.precision;
	}

	public static class DecimalBuilder implements FieldBuilder<DecimalBuilder, BigDecimal> {
		private Field.Builder<BigDecimal> baseBuilder;
		private Integer precision;
		private Boolean signed = false;
		private BigDecimal from;
		private BigDecimal to;

		public DecimalBuilder(java.lang.String name) {
			baseBuilder = new Field.Builder<BigDecimal>(name);
		}

		@Override
		public DecimalBuilder constantValue(BigDecimal constantValue) {
			baseBuilder.constantValue(constantValue);
			return this;
		}

		@Override
		public DecimalBuilder defaultValue(BigDecimal defaultValue) {
			baseBuilder.defaultValue(defaultValue);
			return this;
		}

		@Override
		public DecimalBuilder nullable(boolean nullable) {
			baseBuilder.nullable(nullable);
			return this;
		}

		public DecimalBuilder signed(boolean signed) {
			this.signed = signed;
			return this;
		}

		public DecimalBuilder from(BigDecimal from) {
			this.from = from;
			return this;
		}

		public DecimalBuilder to(BigDecimal to) {
			this.to = to;
			return this;
		}

		public DecimalBuilder precision(Integer precision) {
			this.precision = precision;
			return this;
		}

		public DecimalField build() {
			return new DecimalField(this);
		}
	}

	public BigDecimal getFrom() {
		return this.from;
	}

	public BigDecimal getTo() {
		return this.to;
	}

	public Integer getPrecision() {
		return this.precision;
	}

	public Boolean isSigned() {
		return this.signed;
	}

	@Override
	public String getValue() {
		final BigDecimal value = ValueFactory.DECIMAL.create(this);
		return String.valueOf(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((precision == null) ? 0 : precision.hashCode());
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
		if (!(obj instanceof DecimalField)) {
			return false;
		}
		DecimalField other = (DecimalField) obj;
		if (from == null) {
			if (other.from != null) {
				return false;
			}
		} else if (!from.equals(other.from)) {
			return false;
		}
		if (precision == null) {
			if (other.precision != null) {
				return false;
			}
		} else if (!precision.equals(other.precision)) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("DecimalField [from=");
		builder.append(from);
		builder.append(", to=");
		builder.append(to);
		builder.append(", precision=");
		builder.append(precision);
		builder.append(", signed=");
		builder.append(signed);

		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}

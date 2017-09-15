package org.etl.tools.data.generation.model.types;

public class Field<T> {
	public static final String CONSTANT_FIELD = "constantValue";
	public static final String DEFAULT_FIELD = "defaultValue";
	public static final String NAME_FIELD = "name";
	public static final String NULLABLE_FIELD = "nullable";
	private final T constantValue;
	private final T defaultValue;
	private final String name;
	private final boolean nullable;

	protected Field(Builder<T> builder) {
		this.constantValue = builder.constantValue;
		this.defaultValue = builder.defaultValue;
		this.name = builder.name;
		this.nullable = builder.nullable;
	}

	public T getConstantValue() {
		return constantValue;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public String getName() {
		return name;
	}

	public boolean isNullable() {
		return nullable;
	}
	public  String getValue() {
		return null;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(", constantValue=");
		builder.append(constantValue);
		builder.append(", defaultValue=");
		builder.append(defaultValue);
		builder.append(", name=");
		builder.append(name);
		builder.append(", nullable=");
		builder.append(nullable);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((constantValue == null) ? 0 : constantValue.hashCode());
		result = prime * result + ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (nullable ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Field)) {
			return false;
		}
		Field<?> other = (Field<?>) obj;
		if (constantValue == null) {
			if (other.constantValue != null) {
				return false;
			}
		} else if (!constantValue.equals(other.constantValue)) {
			return false;
		}
		if (defaultValue == null) {
			if (other.defaultValue != null) {
				return false;
			}
		} else if (!defaultValue.equals(other.defaultValue)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (nullable != other.nullable) {
			return false;
		}
		return true;
	}

	public static class Builder<T> implements FieldBuilder<Builder<T>, T> {

		protected T constantValue;
		protected T defaultValue;
		protected String name;
		protected boolean nullable;

		public Builder(String name) {
			this.name = name ;
		}

		@Override
		public Builder<T> constantValue(T constantValue) {
			this.constantValue = constantValue;
			return null;
		}

		@Override
		public Builder<T> defaultValue(T defaultValue) {
			this.defaultValue = defaultValue;
			return null;
		}

		@Override
		public Builder<T> nullable(boolean nullable) {
			this.nullable = nullable;
			return null;
		}

		public Field<T> build() {
			return new Field<T>(this);
		}

	}
}

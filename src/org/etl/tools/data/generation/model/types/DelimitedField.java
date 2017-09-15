package org.etl.tools.data.generation.model.types;

public class DelimitedField<T> extends FormattedField<T> {

    private String delimitter;

    private DelimitedField(Field<T> decoratedField) {
        super(decoratedField);
    }

    private DelimitedField(Builder<T> builder) {
        super(builder.baseBuilder);
        this.delimitter = builder.delimitter;
        this.decoratedField = builder.decoratedField;
    }

    public static class Builder<T> implements FieldBuilder<Builder<T>, T> {

        private Field.Builder<T> baseBuilder;
        private String delimitter;
        private final Field<T> decoratedField;

        public Builder(Field<T> decoratedField) {
            this.baseBuilder = new Field.Builder<T>(decoratedField.getName());
            this.decoratedField = decoratedField;
            this.baseBuilder.constantValue = decoratedField.getConstantValue();
            this.baseBuilder.nullable = decoratedField.isNullable();
            this.baseBuilder.defaultValue = decoratedField.getDefaultValue();
        }

        @Override
        public Builder<T> constantValue(T constantValue) {
            this.baseBuilder.constantValue(constantValue);
            return this;
        }

        @Override
        public Builder<T> defaultValue(T defaultValue) {
            this.baseBuilder.defaultValue(defaultValue);
            return this;
        }

        @Override
        public Builder<T> nullable(boolean nullable) {
            this.baseBuilder.nullable(nullable);
            return this;
        }

        public Builder<T> delimitter(final String delimitter) {
            this.delimitter = delimitter;
            return this;
        }

        public FormattedField<T> build() {
            return new DelimitedField<T>(this);
        }
    }

    @Override
    public String format() {
        final String value = decoratedField.getValue();
        StringBuilder s = new StringBuilder(value);
        s.append(this.delimitter);
        return s.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DelimitedField [delimitter=");
        builder.append(delimitter);
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}

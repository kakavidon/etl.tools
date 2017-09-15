package org.etl.tools.data.generation.model.types;

public abstract class FormattedField<T> extends Field<T> {

	protected Field<T> decoratedField;

	public FormattedField(Field<T> decoratedField) {
		super(new Field.Builder<T>(decoratedField.getName()));
		this.decoratedField = decoratedField;
	}

	protected FormattedField(Field.Builder<T> builder) {
		super(builder);
	}

	@Override
	public T getConstantValue() {
		return this.decoratedField.getConstantValue();
	}

	@Override
	public T getDefaultValue() {
		return this.decoratedField.getDefaultValue();
	}

	@Override
	public String getName() {
		return this.decoratedField.getName();
	}

	@Override
	public boolean isNullable() {
		return this.decoratedField.isNullable();
	}

	@Override
	public String getValue() {
		return decoratedField.getValue();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public abstract String format();

	public static void main(String[] args) {
//		final BooleanBuilder oolcho = FieldFactory.BOOLEAN.create("Boolcho");
//		final IntegerBuilder intcho = FieldFactory.INTEGER.create("intcho");
//		final PersonBuilder cuek = FieldFactory.PERSON.create("cuek");
//
//		oolcho.constantValue(true).nullable(true).defaultValue(true);
//		intcho.from(10).to(20).nullable(false);
//		cuek.attribute(Attribute.FIRST_NAME);
//		final org.etl.tools.model.types.BooleanField bool4o = oolcho.build();
//
//		final org.etl.tools.model.types.DelimitedField.Builder<?> create = FieldFactory.DELIMITED_FIELD.create(bool4o);
//		create.delimitter(",");
//		final FormattedField<?> build = create.build();
//		System.out.println(build + " -> " + build.format());
//		// System.out.println(new DelimitedField<>(intcho.build()));
//		// System.out.println(new DelimitedField<>(cuek.build()));

	}

}

package org.etl.tools.data.generation.model.types;

import java.util.ArrayList;
import java.util.List;
import org.etl.tools.data.generation.model.generators.ValueFactory;
public class CompanyField extends Field<String> {
	private final Attribute attribute;

	protected CompanyField(CompanyBuilder builder) {
		super(builder.baseBuilder);
		this.attribute = builder.attribute;

	}

	public static class CompanyBuilder implements FieldBuilder<CompanyBuilder, java.lang.String> {
		private Field.Builder<java.lang.String> baseBuilder;
		private Attribute attribute;

		public CompanyBuilder(java.lang.String name) {
			baseBuilder = new Field.Builder<java.lang.String>(name);
		}

		@Override
		public CompanyBuilder constantValue(String constantValue) {
			baseBuilder.constantValue(constantValue);
			return this;
		}

		@Override
		public CompanyBuilder defaultValue(String defaultValue) {
			baseBuilder.defaultValue(defaultValue);
			return this;
		}

		@Override
		public CompanyBuilder nullable(boolean nullable) {
			baseBuilder.nullable(nullable);
			return this;
		}

		public CompanyBuilder attribute(Attribute attribute) {
			this.attribute = attribute;
			return this;
		}

		public CompanyField build() {
			return new CompanyField(this);
		}
	}

	public Attribute getAttribute() {
		return attribute;
	}

	@Override
	public String getValue() {
		final java.lang.String value = ValueFactory.COMPANY.create(this);
		return String.valueOf(value);
	}

	public enum Attribute {

		NAME("Name"), DOMAIN("Domain"), EMAIL("Email"), VAT_IDENTIFICATION_NUMBER("Vat Identification Number");

		private String value;

		private Attribute(String value) {

			this.value = value;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		public List<String> asList() {

			final ArrayList<String> arrayList = new ArrayList<String>();
			for (Attribute attribute : values()) {
				arrayList.add(attribute.getValue());
			}
			return arrayList;

		}
	}
}

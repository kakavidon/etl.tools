package org.etl.tools.data.generation.model.types;

import java.util.ArrayList;
import java.util.List;

import org.etl.tools.data.generation.model.generators.ValueFactory;



public class AddressField extends Field<String> {
	private final Attribute attribute;

	protected AddressField(AddressBuilder builder) {
		super(builder.baseBuilder);
		this.attribute = builder.attribute;

	}

	public static class AddressBuilder implements FieldBuilder<AddressBuilder, java.lang.String> {
		private Field.Builder<java.lang.String> baseBuilder;
		private  Attribute attribute;

		public AddressBuilder(java.lang.String name) {
			baseBuilder = new Field.Builder<java.lang.String>(name);
		}

		@Override
		public AddressBuilder constantValue(String constantValue) {
			baseBuilder.constantValue(constantValue);
			return this;
		}

		@Override
		public AddressBuilder defaultValue(String defaultValue) {
			baseBuilder.defaultValue(defaultValue);
			return this;
		}

		@Override
		public AddressBuilder nullable(boolean nullable) {
			baseBuilder.nullable(nullable);
			return this;
		}

		public AddressBuilder attribute(Attribute attribute) {
			this.attribute = attribute;
			return this;
		}

		public AddressField build() {
			return new AddressField(this);
		}
	}

	public Attribute getAttribute() {
		return attribute;
	}

	@Override
	public String getValue() {
		final java.lang.String value = ValueFactory.ADDRESS.create(this);
		return String.valueOf(value);
	    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
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
		if (!(obj instanceof AddressField)) {
			return false;
		}
		AddressField other = (AddressField) obj;
		if (attribute != other.attribute) {
			return false;
		}
		return true;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressField [attribute=");
		builder.append(attribute);
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}



	public enum Attribute {

		STREET("Street"), 
		STREET_NUMBER("Street Number"), 
		APARTMENT_NUMBER("Apartment Number"), 
		POSTAL_CODE("Postal Code"), 
		CITY("City"), 
		ADDRESS_LINE1("Address Line1"), 
		ADDRESS_LINE2("Address Line2");
		
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

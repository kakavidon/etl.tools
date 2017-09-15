package org.etl.tools.data.generation.model.types;

import java.util.ArrayList;
import java.util.List;

import org.etl.tools.data.generation.model.generators.ValueFactory;

public class PersonField extends Field<String> {

    private final Attribute attribute;

    protected PersonField(PersonBuilder builder) {
        super(builder.baseBuilder);
        this.attribute = builder.attribute;
    }

    public static class PersonBuilder implements FieldBuilder<PersonBuilder, java.lang.String> {

        private Field.Builder<java.lang.String> baseBuilder;
        private Attribute attribute;

        public PersonBuilder(java.lang.String name) {
            baseBuilder = new Field.Builder<java.lang.String>(name);
        }

        @Override
        public PersonBuilder constantValue(String constantValue) {
            baseBuilder.constantValue(constantValue);
            return this;
        }

        @Override
        public PersonBuilder defaultValue(String defaultValue) {
            baseBuilder.defaultValue(defaultValue);
            return this;
        }

        @Override
        public PersonBuilder nullable(boolean nullable) {
            baseBuilder.nullable(nullable);
            return this;
        }

        public PersonBuilder attribute(Attribute attribute) {
            this.attribute = attribute;
            return this;
        }

        public PersonField build() {
            return new PersonField(this);
        }

    }

    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public String getValue() {
        final java.lang.String value = ValueFactory.PERSON.create(this);
        return String.valueOf(value);
    }

    public PersonField copy() {
        final PersonBuilder newInstance = FieldFactory.PERSON.create(this.getName());
        newInstance.constantValue(this.getConstantValue());
        newInstance.defaultValue(this.getDefaultValue());
        newInstance.nullable(this.isNullable());
        newInstance.attribute(this.getAttribute());
        PersonField copy = newInstance.build();
        return copy;
    }

    public enum Attribute {

        FIRST_NAME("firstName"),
        MIDDLE_NAME("middleName"),
        LAST_NAME("lastName"),
        EMAIL("email"),
        USER_NAME("username"),
        PASSWORD("password"),
        TELEPHONE_NUMBER("telephoneNumber"),
        DATE_OF_BIRTH("dateOfBirth"),
        AGE("age"), 
        COMPANY_EMAIL("companyEmail"),
        NATIONAL_IDENTITY_CARD_NUMBER("nationalIdentityCardNumber"),
        NATIONAL_IDENTIFICATION_NUMBER("nationalIdentificationNumber"),
        PASSPORT_NUMBER("passportNumber");

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

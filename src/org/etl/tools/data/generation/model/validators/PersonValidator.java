package org.etl.tools.data.generation.model.validators;

import static org.etl.tools.data.generation.model.validators.Utils.*;

import org.etl.tools.data.generation.model.PersonModel;
import org.etl.tools.data.generation.model.types.FieldFactory;
import org.etl.tools.data.generation.model.types.PersonField;
import org.etl.tools.data.generation.model.types.PersonField.Attribute;
import org.etl.tools.data.generation.model.types.PersonField.PersonBuilder;

public class PersonValidator extends Validator<PersonModel, org.etl.tools.data.generation.model.types.PersonField> {

	public PersonValidator(PersonModel model) {
		super(model);
	}

	@Override
	public PersonField validate() {

		final PersonModel validatedModel = (PersonModel) with(model).notNull().validate();

		final String validName = with(validatedModel.getName()).notNull().notEmpty().longerThan(1).validate();

		final String constantValue = with(validatedModel.getConstantValue()).allowNull().notEmpty().longerThan(1)
				.validate();
		final String defaultValue = with(validatedModel.getDefaultValue()).allowNull().notEmpty().longerThan(1)
				.validate();
		final java.lang.Boolean nullable = with(validatedModel.isNullable()).allowNull().isValid().validate();

		final PersonBuilder builder = FieldFactory.PERSON.create(validName);

		if (constantValue != null) {
			return builder.constantValue(constantValue).build();
		}

		final PersonField.Attribute attribute = (Attribute) with(validatedModel.getAttribute()).notNull().validate();
		with(attribute.getValue()).notNull().notEmpty().valueIsAllowed(attribute.asList());
		return builder.attribute(attribute).defaultValue(defaultValue).nullable(nullable).build();
	}

}

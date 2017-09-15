package org.etl.tools.data.generation.model.validators;

import static org.etl.tools.data.generation.model.validators.Utils.with;

import org.etl.tools.data.generation.model.AddressModel;
import org.etl.tools.data.generation.model.types.AddressField;
import org.etl.tools.data.generation.model.types.AddressField.AddressBuilder;
import org.etl.tools.data.generation.model.types.AddressField.Attribute;
import org.etl.tools.data.generation.model.types.FieldFactory;
public class AddressValidator extends Validator<AddressModel, AddressField>{

	public AddressValidator(AddressModel model) {
		super(model);
	}

	@Override
	public AddressField validate() {
		final AddressModel validatedModel = (AddressModel) with(model).notNull().validate();

		final String validName = with(validatedModel.getName()).notNull().notEmpty().longerThan(1).validate();

		final String constantValue = with(validatedModel.getConstantValue()).allowNull().notEmpty().longerThan(1)
				.validate();
		final String defaultValue = with(validatedModel.getDefaultValue()).allowNull().notEmpty().longerThan(1)
				.validate();
		final java.lang.Boolean nullable = with(validatedModel.isNullable()).allowNull().isValid().validate();

		final AddressBuilder builder = FieldFactory.ADDRESS.create(validName);

		if (constantValue != null) {
			return builder.constantValue(constantValue).build();
		}

		final AddressField.Attribute attribute = (Attribute) with(validatedModel.getAttribute()).notNull().validate();
		with(attribute.getValue()).notNull().notEmpty().valueIsAllowed(attribute.asList());
		return builder.attribute(attribute).defaultValue(defaultValue).nullable(nullable).build();
	}

}

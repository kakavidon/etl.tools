package org.etl.tools.data.generation.model.validators;

import static org.etl.tools.data.generation.model.validators.Utils.with;

import org.etl.tools.data.generation.model.CompanyModel;
import org.etl.tools.data.generation.model.types.CompanyField;
import org.etl.tools.data.generation.model.types.CompanyField.Attribute;
import org.etl.tools.data.generation.model.types.CompanyField.CompanyBuilder;
import org.etl.tools.data.generation.model.types.FieldFactory;

public class CompanyValidator extends Validator<CompanyModel, org.etl.tools.data.generation.model.types.CompanyField> {

	public CompanyValidator(CompanyModel model) {
		super(model);
	}

	@Override
	public CompanyField validate() {
		final CompanyModel validatedModel = (CompanyModel) with(model).notNull().validate();

		final String validName = with(validatedModel.getName()).notNull().notEmpty().longerThan(1).validate();

		final String constantValue = with(validatedModel.getConstantValue()).allowNull().notEmpty().longerThan(1)
				.validate();
		final String defaultValue = with(validatedModel.getDefaultValue()).allowNull().notEmpty().longerThan(1)
				.validate();
		final java.lang.Boolean nullable = with(validatedModel.isNullable()).allowNull().isValid().validate();

		final CompanyBuilder builder = FieldFactory.COMPANY.create(validName);

		if (constantValue != null) {
			return builder.constantValue(constantValue).build();
		}

		final CompanyField.Attribute attribute = (Attribute) with(validatedModel.getAttribute()).notNull().validate();
		with(attribute.getValue()).notNull().notEmpty().valueIsAllowed(attribute.asList());
		return builder.attribute(attribute).defaultValue(defaultValue).nullable(nullable).build();
	}

}

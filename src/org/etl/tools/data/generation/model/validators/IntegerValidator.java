package org.etl.tools.data.generation.model.validators;

import static org.etl.tools.data.generation.model.validators.Utils.range;
import static org.etl.tools.data.generation.model.validators.Utils.with;

import org.etl.tools.data.generation.model.IntegerModel;
import org.etl.tools.data.generation.model.types.FieldFactory;
import org.etl.tools.data.generation.model.types.IntegerField;
import org.etl.tools.data.generation.model.types.IntegerField.IntegerBuilder;


public class IntegerValidator extends Validator<IntegerModel,IntegerField> {

	public IntegerValidator(IntegerModel model) {
		super(model);
	}

	@Override
	public IntegerField validate() {
		final IntegerModel validatedModel = (IntegerModel) with(model).notNull().validate();
		final String validName = with(validatedModel.getName()).notNull().notEmpty().longerThan(1).validate();
		final java.lang.Integer constantValue = with(validatedModel.getConstantValue()).allowNull().isValid()
				.validate();
		final java.lang.Integer defaultValue = with(validatedModel.getDefaultValue()).allowNull().isValid().validate();
		final java.lang.Boolean nullable = with(validatedModel.isNullable()).allowNull().isValid().validate();
		final IntegerBuilder builder = FieldFactory.INTEGER.create(validName);

		if (constantValue != null) {
			return builder.constantValue(constantValue).build();
		}

		builder.defaultValue(defaultValue).nullable(nullable);
		final java.lang.Integer from = with(validatedModel.getFrom()).allowNull().isValid().validate();
		final java.lang.Integer to = with(validatedModel.getTo()).allowNull().isValid().validate();
		final java.lang.Boolean signed = with(validatedModel.isSigned()).allowNull().isValid().validate();
		
		if (from != null && to != null) {
			range(from, to).check();
		}
		
		builder.from(from).to(to).signed(signed);
		return builder.build();
	}
}

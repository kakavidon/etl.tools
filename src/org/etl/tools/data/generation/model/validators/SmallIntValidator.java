package org.etl.tools.data.generation.model.validators;

import static org.etl.tools.data.generation.model.validators.Utils.with;

import org.etl.tools.data.generation.model.SmallIntModel;
import org.etl.tools.data.generation.model.types.FieldFactory;
import org.etl.tools.data.generation.model.types.SmallIntField;
import org.etl.tools.data.generation.model.types.SmallIntField.SmallIntBuilder;

public class SmallIntValidator extends Validator<SmallIntModel, SmallIntField> {

	public SmallIntValidator(SmallIntModel model) {
		super(model);
	}

	@Override
	public SmallIntField validate() {

		final SmallIntModel validatedModel = (SmallIntModel) with(model).notNull().validate();
		final String validName = with(validatedModel.getName()).notNull().notEmpty().longerThan(1).validate();
		final Short constantValue = with(validatedModel.getConstantValue()).allowNull().isValid().validate();
		final Short defaultValue = with(validatedModel.getDefaultValue()).allowNull().isValid().validate();
		final Boolean nullable = with(validatedModel.isNullable()).allowNull().isValid().validate();
		final SmallIntBuilder builder = FieldFactory.SMALLINT.create(validName);

		if (constantValue != null) {
			return builder.constantValue(constantValue).build();
		}

		builder.defaultValue(defaultValue).nullable(nullable);
		final Short from = with(validatedModel.getFrom()).allowNull().isValid().validate();
		final Short to = with(validatedModel.getTo()).allowNull().isValid().validate();
		final Boolean signed = with(validatedModel.isSigned()).allowNull().isValid().validate();

		if (from != null && to != null) {
			with(from).lessThan(to).validate();
			with(to).greaterThan(from).validate();
		}

		builder.from(from).to(to).signed(signed);
		return builder.build();
	}

}

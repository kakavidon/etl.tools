package org.etl.tools.data.generation.model.validators;

import static org.etl.tools.data.generation.model.validators.Utils.with;

import org.etl.tools.data.generation.model.BooleanModel;
import org.etl.tools.data.generation.model.generators.ValueFactory;
import org.etl.tools.data.generation.model.types.BooleanField;
import org.etl.tools.data.generation.model.types.BooleanField.BooleanBuilder;
import org.etl.tools.data.generation.model.types.FieldFactory;

public class BooleanValidator extends Validator<BooleanModel, BooleanField> {

	public BooleanValidator(BooleanModel model) {
		super(model);

	}

	@Override
	public BooleanField validate() {

		final BooleanModel validatedModel = (BooleanModel) with(model).notNull().validate();
		final String validName = with(validatedModel.getName()).notNull().notEmpty().longerThan(1).validate();
		final java.lang.Boolean constantValue = with(validatedModel.getConstantValue()).allowNull().isValid()
				.validate();
		final java.lang.Boolean defaultValue = with(validatedModel.getDefaultValue()).allowNull().isValid().validate();
		final java.lang.Boolean nullable = with(validatedModel.isNullable()).allowNull().isValid().validate();

		final BooleanBuilder builder = FieldFactory.BOOLEAN.create(validName);

		if (constantValue != null) {
			return builder.constantValue(constantValue).build();
		}

		return builder.defaultValue(defaultValue).nullable(nullable).build();

	}

	public static void main(String[] args) {
		ValueFactory.init();
		BooleanModel model = new BooleanModel(" a");
		//model.setConstantValue(false);
		model.setNullable(false);
		model.setDefaultValue(true);

		final BooleanValidator booleanValidator = new BooleanValidator(model);
		final BooleanField bool = booleanValidator.validate();

		for (int i = 0; i < 1000; i++) {
			System.out.println(bool.getValue());
		}

	}
}

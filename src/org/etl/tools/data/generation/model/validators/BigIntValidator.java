package org.etl.tools.data.generation.model.validators;

import static org.etl.tools.data.generation.model.validators.Utils.with;

import java.math.BigInteger;

import org.etl.tools.data.generation.model.BigIntModel;
import org.etl.tools.data.generation.model.types.BigIntField;
import org.etl.tools.data.generation.model.types.BigIntField.BigIntBuilder;
import org.etl.tools.data.generation.model.types.FieldFactory;

public class BigIntValidator extends Validator<BigIntModel, BigIntField> {

	public BigIntValidator(BigIntModel model) {
		super(model);
	}

	@Override
	public BigIntField validate() {


		final BigIntModel validatedModel = (BigIntModel) with(model).notNull().validate();
		final String validName = with(validatedModel.getName()).notNull().notEmpty().longerThan(1).validate();
		final BigInteger constantValue = with(validatedModel.getConstantValue()).allowNull().isValid()
				.validate();
		final BigInteger defaultValue = with(validatedModel.getDefaultValue()).allowNull().isValid()
			.validate();
		final java.lang.Boolean nullable = with(validatedModel.isNullable()).allowNull().isValid().validate();
		final BigIntBuilder builder = FieldFactory.BIGINT.create(validName);

		if (constantValue != null) {
			return builder.constantValue(constantValue).build();
		}

		builder.defaultValue(defaultValue).nullable(nullable);
		final BigInteger from = with(validatedModel.getFrom()).allowNull().isValid().validate();
		final BigInteger to = with(validatedModel.getTo()).allowNull().isValid().validate();
		final Boolean signed = with(validatedModel.isSigned()).allowNull().isValid().validate();
		
		if (from != null && to != null) {
			with(from).lessThan(to).validate();
			with(to).greaterThan(from).validate();
		}
		
		builder.from(from).to(to).signed(signed);
		return builder.build();
	}

}

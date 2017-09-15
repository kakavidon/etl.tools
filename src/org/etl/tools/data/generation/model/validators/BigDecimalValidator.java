package org.etl.tools.data.generation.model.validators;

import static org.etl.tools.data.generation.model.validators.Utils.with;

import java.math.BigDecimal;

import org.etl.tools.data.generation.model.DecimalModedl;
import org.etl.tools.data.generation.model.types.DecimalField;
import org.etl.tools.data.generation.model.types.DecimalField.DecimalBuilder;
import org.etl.tools.data.generation.model.types.FieldFactory;

public class BigDecimalValidator extends Validator<DecimalModedl, DecimalField> {

	public BigDecimalValidator(DecimalModedl model) {
		super(model);
	}

	@Override
	public DecimalField validate() {
		final DecimalModedl validatedModel = (DecimalModedl) with(model).notNull().validate();
		final String validName = with(validatedModel.getName()).notNull().notEmpty().longerThan(1).validate();
		final BigDecimal constantValue = with(validatedModel.getConstantValue()).allowNull().isValid().validate();
		final BigDecimal defaultValue = with(validatedModel.getDefaultValue()).allowNull().isValid().validate();
		final java.lang.Boolean nullable = with(validatedModel.isNullable()).allowNull().isValid().validate();
		final DecimalBuilder builder = FieldFactory.DECIMAL.create(validName);

		if (constantValue != null) {
			return builder.constantValue(constantValue).build();
		}
		
		builder.defaultValue(defaultValue).nullable(nullable);
		final BigDecimal from = with(validatedModel.getFrom()).allowNull().isValid().validate();
		final BigDecimal to = with(validatedModel.getTo()).allowNull().isValid().validate();
		final Boolean signed = with(validatedModel.getSigned()).allowNull().isValid().validate();

		if (from != null && to != null) {
			with(from).lessThan(to).validate();
			with(to).greaterThan(from).validate();
		}
		
	
		final Integer precision = with(validatedModel.getPrecision()).allowNull().isValid().validate();
		if (precision != null ) {
		//TODO fix Util class - make intelligent check	
		//	with(precision).greaterThan(BigDecimal.ZERO).validate();
		}
		builder.from(from).to(to).signed(signed).precision(precision);
		return builder.build();
	}

}

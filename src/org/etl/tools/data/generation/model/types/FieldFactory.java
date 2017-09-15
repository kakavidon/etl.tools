package org.etl.tools.data.generation.model.types;

import org.etl.tools.data.generation.model.types.AddressField.AddressBuilder;
import org.etl.tools.data.generation.model.types.BigIntField.BigIntBuilder;
import org.etl.tools.data.generation.model.types.BooleanField.BooleanBuilder;
import org.etl.tools.data.generation.model.types.CompanyField.CompanyBuilder;
import org.etl.tools.data.generation.model.types.DecimalField.DecimalBuilder;
import org.etl.tools.data.generation.model.types.DelimitedField.Builder;
import org.etl.tools.data.generation.model.types.IntegerField.IntegerBuilder;
import org.etl.tools.data.generation.model.types.PersonField.PersonBuilder;
import org.etl.tools.data.generation.model.types.SmallIntField.SmallIntBuilder;;

public abstract class FieldFactory<T> {
	public interface FieldBuilder<T> {
		public abstract DelimitedField.Builder<?> create(Field<?> field);
	}

	public abstract T create(String name);

	public static final FieldFactory<BigIntBuilder> BIGINT = new FieldFactory<BigIntBuilder>() {
		@Override
		public BigIntBuilder create(String name) {
			return new BigIntField.BigIntBuilder(name);
		}
	};
	public static final FieldFactory<DecimalBuilder> DECIMAL = new FieldFactory<DecimalBuilder>() {
		@Override
		public DecimalBuilder create(String name) {
			return new DecimalField.DecimalBuilder(name);
		}
	};

	public static final FieldFactory<BooleanBuilder> BOOLEAN = new FieldFactory<BooleanBuilder>() {
		@Override
		public BooleanBuilder create(String name) {
			return new BooleanField.BooleanBuilder(name);
		}
	};

	public static final FieldFactory<IntegerBuilder> INTEGER = new FieldFactory<IntegerBuilder>() {
		@Override
		public IntegerBuilder create(String name) {
			return new IntegerField.IntegerBuilder(name);
		}
	};
	public static final FieldFactory<SmallIntBuilder> SMALLINT = new FieldFactory<SmallIntBuilder>() {
		@Override
		public SmallIntBuilder create(String name) {
			return new SmallIntField.SmallIntBuilder(name);
		}
	};
	public static final FieldFactory<PersonBuilder> PERSON = new FieldFactory<PersonBuilder>() {
		@Override
		public PersonBuilder create(String name) {
			return new PersonField.PersonBuilder(name);
		}
	};

	public static final FieldBuilder<Field<?>> DELIMITED_FIELD = new FieldBuilder<Field<?>>() {
		@Override
		public Builder<?> create(Field<?> field) {
			return new Builder<>(field);
		}
	};

	public static final FieldFactory<AddressBuilder> ADDRESS = new FieldFactory<AddressBuilder>() {
		@Override
		public AddressBuilder create(String name) {
			return new AddressField.AddressBuilder(name);
		}
	};

	public static final FieldFactory<CompanyBuilder> COMPANY = new FieldFactory<CompanyBuilder>() {

		@Override
		public CompanyBuilder create(String name) {
			return new CompanyField.CompanyBuilder(name);
		}
	};

}

package org.etl.tools.data.generation.model.generators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import org.etl.tools.data.generation.model.types.AddressField;
import org.etl.tools.data.generation.model.types.BigIntField;
import org.etl.tools.data.generation.model.types.BooleanField;
import org.etl.tools.data.generation.model.types.CompanyField;
import org.etl.tools.data.generation.model.types.DecimalField;
import org.etl.tools.data.generation.model.types.IntegerField;
import org.etl.tools.data.generation.model.types.PersonField;
import org.etl.tools.data.generation.model.types.SmallIntField;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;

public abstract class ValueFactory<T, V> {

	public abstract T create(V field);

	private final static Random random = new Random();
	private final static BaseProducer baseProducer = Fairy.create().baseProducer();
	// private final static PersonProperty withAddress =
	// PersonProperties.withAddress(null);
	private final static Fairy fairy = Fairy.create();

	public static ValueFactory<Boolean,BooleanField> BOOLEAN = new ValueFactory<Boolean, BooleanField>() {
		private final Boolean[] trueOrFalse = { Boolean.valueOf(true), Boolean.valueOf(false) };
		private final Boolean[] trueFalseOrNull = { Boolean.valueOf(true), Boolean.valueOf(false), null };

		@Override
		public Boolean create(BooleanField field) {

			final java.lang.Boolean constantValue = field.getConstantValue();

			if (constantValue != null) {
				return constantValue;
			}
			return field.isNullable() ? trueFalseOrNull[random.nextInt(3)] : trueOrFalse[random.nextInt(2)];

		}
	};

	public static ValueFactory<Integer, IntegerField> INTEGER = new ValueFactory<Integer, IntegerField>() {
		private final Integer[] positiveOrNegative = { Integer.valueOf(1), Integer.valueOf(-1) };
		private final Integer[] notNullOrNul = { Integer.valueOf(1), null };

		private int setSign(IntegerField field, Integer sign, final int randomValue) {
			return field.isSigned() ? sign * randomValue : randomValue;
		}

		@Override
		public java.lang.Integer create(IntegerField field) {

			final Integer constantValue = field.getConstantValue();
			if (constantValue != null) {
				return constantValue;
			}
			if (field.isNullable()) {
				Integer maybeNull = notNullOrNul[random.nextInt(2)];
				if (maybeNull == null) {
					return null;
				}
			}
			Integer sign = positiveOrNegative[random.nextInt(2)];
			Integer from = field.getFrom();
			Integer to = field.getTo();

			if (from == null || (field.isSigned() && from < 0)) {
				from = 0;
			}

			if (to == null) {
				to = Integer.MAX_VALUE - 1;
			}

			final int randomValue = baseProducer.randomBetween(from, to);
			return setSign(field, sign, randomValue);

		}

	};

	public static ValueFactory<String, AddressField> ADDRESS = new ValueFactory<String, AddressField>() {
		@Override
		public String create(AddressField field) {
			Person person = fairy.person();
			final Address address = person.getAddress();
			final AddressField.Attribute attribute = field.getAttribute();
			switch (attribute) {
			case STREET:
				return address.getStreet();
			case STREET_NUMBER:
				return address.getStreetNumber();
			case APARTMENT_NUMBER:
				return address.getApartmentNumber();
			case POSTAL_CODE:
				return address.getPostalCode();
			case CITY:
				return address.getCity();
			case ADDRESS_LINE1:
				return address.getAddressLine1();
			case ADDRESS_LINE2:
				return address.getAddressLine2();
			default:
				throw new IllegalArgumentException("Invalid address attribute:" + attribute.getValue());
			}

		}
	};

	public static ValueFactory<String, CompanyField> COMPANY = new ValueFactory<String, CompanyField>() {
		@Override
		public String create(CompanyField field) {
			Person person = fairy.person();
			final Company company = person.getCompany();
			final CompanyField.Attribute attribute = field.getAttribute();
			switch (attribute) {
			case NAME:
				return company.getName();
			case DOMAIN:
				return company.getDomain();
			case EMAIL:
				return company.getEmail();
			case VAT_IDENTIFICATION_NUMBER:
				return company.getVatIdentificationNumber();
			default:
				throw new IllegalArgumentException("Invalid company attribute:" + attribute.getValue());
			}
		}
	};

	public static ValueFactory<String, PersonField> PERSON = new ValueFactory<String, PersonField>() {

		@Override
		public String create(PersonField field) {
			Person person = fairy.person();
			final PersonField.Attribute attribute = field.getAttribute();

			switch (attribute) {
			case FIRST_NAME:
				return person.getFirstName();
			case MIDDLE_NAME:
				return person.getMiddleName();
			case LAST_NAME:
				return person.getLastName();
			case EMAIL:
				return person.getEmail();
			case USER_NAME:
				return person.getUsername();
			case PASSWORD:
				return person.getPassword();
			case TELEPHONE_NUMBER:
				return person.getTelephoneNumber();
			case DATE_OF_BIRTH:
				return person.getDateOfBirth().toString();
			case AGE:
				return String.valueOf(person.getAge());
			case COMPANY_EMAIL:
				return person.getCompanyEmail();
			case NATIONAL_IDENTITY_CARD_NUMBER:
				return person.getNationalIdentityCardNumber();
			case NATIONAL_IDENTIFICATION_NUMBER:
				return person.getNationalIdentificationNumber();
			case PASSPORT_NUMBER:
				return person.getPassportNumber();
			default:
				throw new IllegalArgumentException("Invalid person attribute:" + attribute.getValue());
			}

		}
	};

	public static ValueFactory<Short, SmallIntField> SMALLINT = new ValueFactory<Short, SmallIntField>() {
		private final short[] positiveOrNegative = { 1, -1 };
		private final Short[] notNullOrNul = { 1, null };

		private Short setSign(SmallIntField field, Short sign, final Short randomValue) {
			return (short) (field.isSigned() ? sign * randomValue : randomValue);
		}

		@Override
		public Short create(SmallIntField field) {

			final Short constantValue = field.getConstantValue();
			if (constantValue != null) {
				return constantValue;
			}
			if (field.isNullable()) {
				Short maybeNull = notNullOrNul[random.nextInt(2)];
				if (maybeNull == null) {
					return null;
				}
			}
			Short sign = positiveOrNegative[random.nextInt(2)];
			Short from = field.getFrom();
			Short to = field.getTo();

			if (from == null || (field.isSigned() && from < 0)) {
				from = 0;
			}

			if (to == null) {
				to = Short.MAX_VALUE - 1;
			}

			final short randomValue = (short) baseProducer.randomBetween(from, to);
			return setSign(field, sign, randomValue);

		}

	};

	public static ValueFactory<BigInteger, BigIntField> BIGINT = new ValueFactory<BigInteger, BigIntField>() {
		private final BigInteger[] positiveOrNegative = { BigInteger.valueOf(1), BigInteger.valueOf(-1) };
		private final BigInteger[] notNullOrNul = { BigInteger.valueOf(1), null };

		private BigInteger setSign(BigIntField field, BigInteger sign,
				final BigInteger randomValue) {
			return field.isSigned() ? randomValue.multiply(sign) : randomValue;
		}

		@Override
		public BigInteger create(BigIntField field) {
			final BigInteger constantValue = field.getConstantValue();
			if (constantValue != null) {
				return constantValue;
			}
			if (field.isNullable()) {

				BigInteger maybeNull = notNullOrNul[random.nextInt(2)];
				if (maybeNull == null) {
					return null;
				}
			}
			BigInteger sign = positiveOrNegative[random.nextInt(2)];
			BigInteger from = field.getFrom();
			BigInteger to = field.getTo();

			if (from == null || (field.isSigned() && from.compareTo(BigInteger.valueOf(0)) == -1)) {
				from = BigInteger.valueOf(0);
			}

			if (to == null) {
				to = BigInteger.valueOf(Integer.MAX_VALUE * Integer.MAX_VALUE);
			}

			final BigInteger randomValue = random(from, to);
			return setSign(field, sign, randomValue);

		}

		private BigInteger random(BigInteger from, BigInteger to) {
			BigInteger r;
			do {
				r = new BigInteger(from.bitLength(), random);
			} while (r.compareTo(from) >= 0);
			return r;
		}
	};
	public static ValueFactory<BigDecimal, DecimalField> DECIMAL = new ValueFactory<BigDecimal, DecimalField>() {
		private final BigDecimal[] positiveOrNegative = { BigDecimal.ONE, BigDecimal.valueOf(-1) };
		private final BigDecimal[] notNullOrNul = { BigDecimal.ONE, null };

		private BigDecimal setSign(DecimalField field, BigDecimal sign, final BigDecimal randomValue) {
			return field.isSigned() ? sign.multiply(randomValue) : randomValue;
		}

		@Override
		public BigDecimal create(DecimalField field) {
			final BigDecimal constantValue = field.getConstantValue();
			if (constantValue != null) {
				return constantValue;
			}
			if (field.isNullable()) {
				BigDecimal maybeNull = notNullOrNul[random.nextInt(2)];
				if (maybeNull == null) {
					return null;
				}
			}
			BigDecimal sign = positiveOrNegative[random.nextInt(2)];
			BigDecimal from = field.getFrom();
			BigDecimal to = field.getTo();

			if (from == null || (field.isSigned() && from.compareTo(BigDecimal.ZERO) < 0)) {
				from = BigDecimal.valueOf(0);
			}

			if (to == null) {
				to = BigDecimal.valueOf(Double.MAX_VALUE * Double.MAX_VALUE);
			}

			final BigDecimal randomValue = random(from, to);
			return setSign(field, sign, randomValue);
		}

		public BigDecimal random(BigDecimal min, BigDecimal max) {
	
			BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
			return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		}

	};

	public static void init() {
		// Nothing to do. Just to initialize static reference of Fairy that may
		// be slow...
	}

}

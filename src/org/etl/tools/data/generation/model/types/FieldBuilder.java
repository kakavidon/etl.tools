package org.etl.tools.data.generation.model.types;

/**
 * 
 * @author Lachezar.Nedelchev
 *
 * @param <S>
 *            Builder that is used to create method chaining
 * @param <T>
 *            Actual value type that fields is i.e. numeric - Integer, String
 *            Date etc.
 */
public interface FieldBuilder<S, T> {
	/**
	 * Constants holding the names of the fields. May be used in databinvding / bean
	 * properties.
	 */
	public static final String FIELD_CONSTANT_VALUE = "constantValue";
	public static final String FIELD_DEFAULTVALUE = "defaultValue";
	public static final String FIELD_NULLABLE = "nullable";

	/**
	 * 
	 * If a constant value is set (not null) no further generation will be
	 * performed. Instead all rows will be populated with that value for this field
	 * 
	 * @param constantValue
	 *            - the value of type T
	 * @return The Builder<T>
	 */
	public S constantValue(T constantValue);

	/**
	 * 
	 * @param defaultValue
	 *            default value of the field of type T
	 * @return The Builder<T>
	 */
	public S defaultValue(T defaultValue);

	/**
	 * 
	 * @param nullable
	 *            boolean indicator denoting if the field is nullable or not.
	 *            Default false.
	 * @return The Builder<T>
	 */
	public S nullable(boolean nullable);

}

package org.etl.tools.data.generation.model.types;

public enum Types {

	 ADDRESS("Address"), BIG_INT("BigInt"), BOOLEAN("Boolean"), COMPANY("Company"), DECIMAL("Decimal"), INTEGER("Integer"), PERSON(
			"Person"), SMALL_INT("SmallInt");

	private final String name;

	Types(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static String[] names() {
		final Types[] values = values();
		String[] result = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].getName();
		}
		return result;

	}

}

package org.etl.tools.data.generation.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.etl.tools.data.generation.settings.Settings;



public final class PersistenceFactory {
	private PersistenceFactory() {
	}

	public static enum PersistenceType {
		JSON(JSON_FORMAT), YAML(YAML_FORMAT);

		private final FormatBuilder formatConstructor;

		private PersistenceType(FormatBuilder formatConstructor) {
			this.formatConstructor = formatConstructor;
		}

		public FormatBuilder getFormatConstructor() {
			return formatConstructor;
		}

	}

	private final static FormatBuilder JSON_FORMAT = new FormatBuilder() {

		@Override
		public <T> JsonProvider<T> create(String fileName) {
			return new JsonProvider<T>(fileName);
		}

		@Override
		public String getClassName() {
			return JsonProvider.class.getName();
		}

		@Override
		public String toString() {
			return getClassName();
		}

	};

	private final static FormatBuilder YAML_FORMAT = new FormatBuilder() {

		@Override
		public <T> YamlProvider<T> create(String fileName) {
			return new YamlProvider<T>(fileName);
		}

		@Override
		public String getClassName() {
			return YamlProvider.class.getName();
		}

		@Override
		public String toString() {
			return getClassName();
		}
	};

	public static <T> PersistenceProvider<T> create(PersistenceType type, String fileName) {
		return type.getFormatConstructor().create(fileName);
	}

	public static final PersistenceProvider<?> getDefault(String fileName) throws PersistenceException {
		final String providerClass = Settings.getPersistenceProvider();
		final FormatBuilder constructor = constructors.get(providerClass);
		if (Objects.isNull(constructor)) {
			throw new PersistenceException(
					new StringBuilder("Could not find a provider of type [").append(providerClass)
							.append("] . Currently registered providers are ").append(constructors.values())
							.append(". Please, check your configuration poroperties. ").toString());
		}
		return constructor.create(fileName);
	}

	private static final Map<String, FormatBuilder> constructors = new HashMap<>();
	static {
		constructors.put(JSON_FORMAT.getClassName(), JSON_FORMAT);
		constructors.put(YAML_FORMAT.getClassName(), YAML_FORMAT);
	}
}

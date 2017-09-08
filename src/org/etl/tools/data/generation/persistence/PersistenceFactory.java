package org.etl.tools.data.generation.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.etl.tools.data.generation.settings.Settings;

/**
 * 
 * Factory class capable to produce new instance of PersistenceProviders.
 * 
 * @author Lachezar.Nedelchev
 *
 */
public final class PersistenceFactory {
	private PersistenceFactory() {
	}

	/**
	 * Enum that exposes the available PersistenceProviders
	 * 
	 * @author Lachezar.Nedelchev
	 *
	 */
	public static enum PersistenceType {
		JSON(JSON_FORMAT), YAML(YAML_FORMAT);

		private final PersistenceFormat format;

		private PersistenceType(PersistenceFormat format) {
			this.format = format;
		}

		public PersistenceFormat getFormatConstructor() {
			return format;
		}

	}

	/**
	 * JSON persistent format.
	 */
	private final static PersistenceFormat JSON_FORMAT = new PersistenceFormat() {

		@Override
		public <T> JsonProvider<T> create(String fileName, Class<T> type) {
			return new JsonProvider<T>(fileName, type);
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

	/**
	 * YAML persistent format.
	 */
	private final static PersistenceFormat YAML_FORMAT = new PersistenceFormat() {

		@Override
		public <T> YamlProvider<T> create(String fileName, Class<T> type) {
			return new YamlProvider<T>(fileName, type);
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

	/**
	 * 
	 * Tries to return a new instance of supported/registered PersistentProviders.
	 * 
	 * @param type
	 *            of the supported persistence formats declared in
	 *            {@link PersistenceFactory#PersistenceFormat} and registered in
	 *            <code>providers</code> static Map.
	 * 
	 * @param fileName
	 *            the name of the file where object will be persisted
	 * @param objectType
	 *            type of the persistent object
	 * @return new instance of default PersistentProvider
	 */
	public static <T> PersistenceProvider<T> create(PersistenceType type, String fileName, Class<T> objectType) {
		return type.getFormatConstructor().create(fileName, objectType);
	}

	/**
	 * 
	 * Tries to return a new instance of default PersistentProvider entry defined in
	 * the application settings.
	 * 
	 * @param fileName
	 *            the name of the file where object will be persisted
	 * @param objectType
	 *            type of the persistent object
	 * @return new instance of default PersistentProvider
	 * @throws PersistenceException
	 *             there is no entry for default PersistentProvider in the
	 *             application settings defined or the entry refers to unsupported
	 *             or invalid provider.
	 */
	public static final <T> PersistenceProvider<T> getDefault(String fileName, Class<T> objectType)
			throws PersistenceException {
		final String providerClass = Settings.getPersistenceProvider();
		final PersistenceFormat constructor = providers.get(providerClass);
		if (Objects.isNull(constructor)) {
			throw new PersistenceException(new StringBuilder("Could not find a provider of type [")
					.append(providerClass).append("] . Currently registered providers are ").append(printProviders())
					.append(". Please, check your configuration poroperties. ").toString());
		}
		return constructor.create(fileName, objectType);
	}

	/**
	 * Map storage where supported PersistentProviders are registered.
	 */
	private static final Map<String, PersistenceFormat> providers = new HashMap<>();
	static {
		providers.put(JSON_FORMAT.getClassName(), JSON_FORMAT);
		providers.put(YAML_FORMAT.getClassName(), YAML_FORMAT);
	}

	public static String printProviders() {
		StringBuilder stringBuilder = new StringBuilder("{");
		for (String className : providers.keySet()) {
			stringBuilder.append(className).append(", ");
		}
		return stringBuilder.append("}").toString();
	}
}

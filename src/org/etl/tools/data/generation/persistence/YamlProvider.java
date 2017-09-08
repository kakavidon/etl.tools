package org.etl.tools.data.generation.persistence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

/**
 * Concrete implementation of <code>{@link AbstractPersistenceProvider}</code>
 * that supports persistence operations in YAML file format.
 * 
 * @author Lachezar.Nedelchev
 *
 * @param <T>
 *            the type of the persistent object.
 */
public class YamlProvider<T> extends AbstractPersistenceProvider<T> {
	private final Yaml yaml;

	/**
	 * Constructor that initializes all necessary things regarding persistent
	 * operations to a file in YAML format.
	 * 
	 * @param fileName
	 *            the name of the file
	 * @param type
	 *            the type of the persistent object.
	 */
	public YamlProvider(String fileName, Class<T> type) {
		super(fileName, type);
		DumperOptions options = new DumperOptions();
		options.setAllowReadOnlyProperties(true);
		options.setDefaultFlowStyle(FlowStyle.BLOCK);
		yaml = new Yaml(options);

	}

	/**
	 * 
	 * Reads an object of an type <T> from a file in YAML format.
	 * 
	 * @return object of an type <T>
	 * @throws PersistenceException
	 *             if the object cannot be read for various reasons ( i.e. no such
	 *             file exists, permission issues etc.)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T read() throws PersistenceException {
		final Path path = Paths.get(getFileName());
		InputStream in;
		try {
			in = Files.newInputStream(path, StandardOpenOption.READ);
			return (T) yaml.load(in);
		} catch (IOException | ClassCastException e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * 
	 * Writes an object of an type <T> to a file in YAML format.
	 * 
	 * @param object
	 *            of an type <T>
	 * @throws PersistenceException
	 *             if the object cannot be written for various reasons ( i.e. no
	 *             such file exists, permission issues etc.)
	 */	
	@Override
	public void write(T object) throws PersistenceException {
		final Path path = Paths.get(getFileName());
		BufferedWriter writer;
		try {
			writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
			yaml.dump(object, writer);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

}

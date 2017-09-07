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

public class YamlProvider<T> extends AbstractPersistenceProvider<T> {
	private Yaml yaml;

	public YamlProvider(String fileName) {
		super(fileName);
		DumperOptions options = new DumperOptions();
		options.setAllowReadOnlyProperties(true);
		options.setDefaultFlowStyle(FlowStyle.BLOCK);
		yaml = new Yaml(options);

	}

	@SuppressWarnings("unchecked")
	@Override
	public T read(Class<T> type) throws PersistenceException {
		final Path path = Paths.get(getFileName());
		InputStream in;
		try {
			in = Files.newInputStream(path, StandardOpenOption.READ);
			return (T) yaml.load(in);
		} catch (IOException | ClassCastException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public void write(T object) throws PersistenceException {
		final Path path = Paths.get(getFileName());
		BufferedWriter writer;
		try {
			writer = Files.newBufferedWriter(path,  StandardCharsets.UTF_8);
			yaml.dump(object, writer);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}

	}

}

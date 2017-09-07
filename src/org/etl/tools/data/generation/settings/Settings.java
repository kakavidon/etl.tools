package  org.etl.tools.data.generation.settings;

public class Settings {

	public static String getPersistenceProvider() {
		// TODO Retractor : Some of the base settings should be in properties file
		
		return "org.etl.tools.data.generation.persistence.JsonProvider";
	}

}

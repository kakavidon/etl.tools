package org.etl.tools.data.generation.settings;

public final class Settings {
	
	private static final String SETTINGS_UI_MAIN_CALSS = "Settings.ui.main.calss";
	private static final String SETTINGS_APPLICATION_TYPE = "Settings.application.type";
	private static final String SETTINGS_PERSISTENCE_PROVIDER = "Settings.persistence.provider";
	
	private static final String persistenceProvider = SettingsRecources.getString(SETTINGS_PERSISTENCE_PROVIDER);
	private static final String applicationType = SettingsRecources.getString(SETTINGS_APPLICATION_TYPE);
	private static final String applicationWindowClass = SettingsRecources.getString(SETTINGS_UI_MAIN_CALSS);

	public static String getPersistenceProvider() {
		return persistenceProvider;
	}

	public static String getApplicationType() {
		return applicationType;
	}

	public static String getApplicationWindowClass() {
		return applicationWindowClass;
	}

}

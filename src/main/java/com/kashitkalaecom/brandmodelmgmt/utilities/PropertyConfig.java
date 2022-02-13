package com.kashitkalaecom.brandmodelmgmt.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfig {

	private PropertyConfig() {
		throw new IllegalStateException("Utility class");
	}

	public static Properties loadProperties() throws IOException {
		Properties configuration = new Properties();
		InputStream inputStream = PropertyConfig.class.getClassLoader().getResourceAsStream("application.properties");
		configuration.load(inputStream);
		inputStream.close();
		return configuration;

	}
}

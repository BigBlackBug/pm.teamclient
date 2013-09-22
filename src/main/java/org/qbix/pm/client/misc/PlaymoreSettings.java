package org.qbix.pm.client.misc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.qbix.pm.client.misc.exceptions.UnableToInitializeApplication;

public class PlaymoreSettings {
	private static final String NOTIFICATION_SERVER_PORT = "notification_server_port";
	private static final String NOTIFICATION_SERVER_HOSTNAME = "notification_server_hostname";
	private static final String MASTER_SERVER_HOSTNAME = "master_server_hostname";

	private static final String PROPERTIES_FILE = "config/pm.properties";

	private static final Properties PROPERTIES = new Properties();

	public static String getMasterServerHostname() {
		return PROPERTIES.get(MASTER_SERVER_HOSTNAME).toString();
	}

	public static String getNotificationServerHostname() {
		return PROPERTIES.get(NOTIFICATION_SERVER_HOSTNAME).toString();
	}

	public static Integer getNotificationServerPort() {
		String portString = PROPERTIES.get(NOTIFICATION_SERVER_PORT).toString();
		return Integer.parseInt(portString);
	}

	static {
		try {
			FileInputStream fileInputStream = new FileInputStream(
					PROPERTIES_FILE);
			PROPERTIES.load(fileInputStream);
		} catch (Exception e) {
			throw new UnableToInitializeApplication(
					"Error reading properties file from pm.properties", e);
		}
	}

}

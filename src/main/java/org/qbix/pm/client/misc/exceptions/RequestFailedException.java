package org.qbix.pm.client.misc.exceptions;

import java.text.MessageFormat;

import org.qbix.pm.client.misc.PlaymoreSettings;

public class RequestFailedException extends RuntimeException {
	private final static String MASTER_SERVER_ADDRESS = PlaymoreSettings
			.getMasterServerHostname();

	private final static String MESSAGE_SKELETON = "A request to the Master Server at {0} has failed. Status code: '{1}'";

	public RequestFailedException() {
		super(MessageFormat.format(MESSAGE_SKELETON, MASTER_SERVER_ADDRESS,
				"not specified"));
	}

	public RequestFailedException(int statusCode) {
		super(MessageFormat.format(MESSAGE_SKELETON, MASTER_SERVER_ADDRESS,
				statusCode));
	}

}

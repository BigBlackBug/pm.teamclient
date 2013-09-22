package org.qbix.pm.client.misc.exceptions;

import java.io.IOException;

public class UnableToInitializeApplication extends RuntimeException {

	public UnableToInitializeApplication() {
		super();
	}

	public UnableToInitializeApplication(Throwable e) {
		super(e);
	}

	public UnableToInitializeApplication(String message) {
		super(message);
	}

	public UnableToInitializeApplication(String string, Throwable e) {
		super(string, e);
	}

}
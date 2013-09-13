package org.qbix.pm.client.misc;

import org.qbix.pm.client.misc.exceptions.InvalidDTOFormatException;

public enum GameState {
	TEAM_SELECT("TEAM_SELECT"),

	CHAMP_SELECT("CHAMP_SELECT"),

	START_REQUESTED("START_REQUESTED"),

	TERMINATED("TERMINATED"),

	TERMINATED_IN_ERROR("TERMINATED_IN_ERROR");

	private String value;

	private GameState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static GameState get(String value)
			throws InvalidDTOFormatException {
		for (GameState state : values()) {
			if (state.getValue().equals(value)) {
				return state;
			}
		}
		throw new InvalidDTOFormatException(
				"No GameState enum value for value = " + value);
	}
}
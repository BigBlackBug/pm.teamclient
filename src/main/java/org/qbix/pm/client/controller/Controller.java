package org.qbix.pm.client.controller;

import com.google.gson.JsonObject;

public interface Controller {
	public void handleSessionParametersChange(JsonObject json);

	public void handleDTOUpdate(Long sessionID);

	public void handlePlayerDisconnect(Long accountID);

	public void handleStakeConfirmation(Long accountID);

	public void handleStakeCancellation(Long accountID);

	public void handleSessionStarting();
}

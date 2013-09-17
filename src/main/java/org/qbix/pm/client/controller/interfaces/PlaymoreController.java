package org.qbix.pm.client.controller.interfaces;

import org.qbix.pm.client.notifications.ParticipantsReturnInfo;

import com.google.gson.JsonObject;

public interface PlaymoreController extends PlaymoreClient{

	public void handlePlayerDisconnect(Long accountID);

	public void handleStakeConfirmation(Long accountID);

	public void handleStakeCancellation(Long accountID);
	
	public void handleUpdateWithDTO(ParticipantsReturnInfo info);
	
	public void handleSessionParametersChange(JsonObject json);

	public void handleSessionStarting();
	
	public void disconnect();

}

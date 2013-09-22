package org.qbix.pm.client.controller.interfaces;

import org.qbix.pm.client.model.pm.GameDTO;

public interface PlaymoreController extends PlaymoreClient{

	public void handlePlayerDisconnect(Long accountID);

	public void handleStakeConfirmation(Long accountID);

	public void handleStakeCancellation(Long accountID);
	
	public void handleGameSettingsChanged(GameDTO gameDTO);
	
//	public void handleUpdateWithDTO(ParticipantsReturnInfo info);
	
//	public void handleSessionParametersChange(JsonObject json);

	public void handleGameStarting();
	
	public void disconnect();

}

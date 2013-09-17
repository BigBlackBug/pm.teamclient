package org.qbix.pm.client.view.interfaces;

import org.qbix.pm.client.model.PlayerParticipant;

public interface PlayerEntryPanelView {
	
	public void setConfirmedStake(boolean confirmedStake);
	
	//TODO make it a listener
	public void unexpectedlyDisconnected();
	
	public void fill(PlayerParticipant participant, Long pmAccountID,
			String pmNickname);
	
}

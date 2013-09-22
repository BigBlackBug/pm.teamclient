package org.qbix.pm.client.view.interfaces;

import org.qbix.pm.client.model.lol.PlayerParticipant;
import org.qbix.pm.client.model.pm.PlayerEntryDTO;

public interface PlayerEntryPanelView {
	
	public void setConfirmedParticipation(boolean confirmedStake);
	
	public void playerDisconnected();
	
	public void fill(PlayerParticipant playerParticipant, PlayerEntryDTO playerEntry);
	
}

package org.qbix.pm.client.view.interfaces;

import java.util.List;
import java.util.Map;

import org.qbix.pm.client.model.PlayerParticipant;
import org.qbix.pm.client.notifications.ParticipantsReturnInfo.Entry;

public interface TeamPanelView {
	public PlayerEntryPanelView getPlayerEntryPanelView(
			Long pmAccountID);

	public PlayerEntryPanelView getPlayer1Panel();

	public PlayerEntryPanelView getPlayer2Panel();

	public PlayerEntryPanelView getPlayer3Panel();

	public PlayerEntryPanelView getPlayer4Panel();

	public PlayerEntryPanelView getPlayer5Panel();
	
	public void fill(List<PlayerParticipant> players, Map<Long, Entry> pmEntries); 
}

package org.qbix.pm.client.view.interfaces;

import java.util.List;

import org.qbix.pm.client.model.lol.PlayerParticipant;
import org.qbix.pm.client.model.pm.GameDTO;

public interface TeamPanelView {
	public PlayerEntryPanelView getPlayerEntryPanelView(
			Long pmAccountID);

	public PlayerEntryPanelView getPlayer1Panel();

	public PlayerEntryPanelView getPlayer2Panel();

	public PlayerEntryPanelView getPlayer3Panel();

	public PlayerEntryPanelView getPlayer4Panel();

	public PlayerEntryPanelView getPlayer5Panel();
	
	public void fill(List<PlayerParticipant> players, GameDTO gameDTO); 
}

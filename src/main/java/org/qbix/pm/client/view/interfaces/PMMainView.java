package org.qbix.pm.client.view.interfaces;

import org.qbix.pm.client.misc.PlaymoreGameStatus;
import org.qbix.pm.client.model.lol.LoLGameDTO;
import org.qbix.pm.client.model.pm.GameDTO;

public interface PMMainView extends View {

	public void setSessionStatus(PlaymoreGameStatus status);
	
	public void fill(LoLGameDTO lolGameDTO, GameDTO gameDTO);
	
	public PlayerEntryPanelView getPlayerEntryPanelView(Long accountID);
	
	public GameSettingsPanelView getGameSettingsView();
}

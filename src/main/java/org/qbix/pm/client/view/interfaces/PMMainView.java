package org.qbix.pm.client.view.interfaces;

import org.qbix.pm.client.misc.PlaymoreSessionStatus;
import org.qbix.pm.client.model.GameDTO;
import org.qbix.pm.client.notifications.ParticipantsReturnInfo;

public interface PMMainView extends View {

	public void setSessionStatus(PlaymoreSessionStatus status);
	
	public void fill(GameDTO gameDTO, ParticipantsReturnInfo info);
	
	public PlayerEntryPanelView getPlayerEntryPanelView(Long accountID);
	
	public SessionSettingsPanelView getSessionSettingsView();
}

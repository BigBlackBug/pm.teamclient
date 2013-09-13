package org.qbix.pm.client.view.interfaces;

import org.qbix.pm.client.controller.interfaces.PlayerEntryPanelController;
import org.qbix.pm.client.controller.interfaces.SessionSettingsPanelController;
import org.qbix.pm.client.misc.PlaymoreSessionStatus;

public interface PMTeamView extends View {
	
	public PlayerEntryPanelController getPlayerEntryPanelController(Long accountID);
	
	public SessionSettingsPanelController getSessionSettingsController();
	
	public void setSessionStatus(PlaymoreSessionStatus status);
}

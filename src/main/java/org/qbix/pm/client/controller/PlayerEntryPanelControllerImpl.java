package org.qbix.pm.client.controller;

import org.qbix.pm.client.controller.interfaces.PlayerEntryPanelController;
import org.qbix.pm.client.view.PlayerEntryPanel;

public class PlayerEntryPanelControllerImpl implements PlayerEntryPanelController{
	
	private final PlayerEntryPanel panel;

	public PlayerEntryPanelControllerImpl(PlayerEntryPanel panel) {
		this.panel = panel;
	}

	@Override
	public void setConfirmedStake(boolean confirmedStake) {
		//TODO confirm
	}
}

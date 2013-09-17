package org.qbix.pm.client.controller;

import java.io.IOException;

import org.qbix.pm.client.controller.interfaces.AbstractPlaymoreController;
import org.qbix.pm.client.controller.interfaces.PlaymoreController;
import org.qbix.pm.client.controller.interfaces.RestController;
import org.qbix.pm.client.misc.PlaymoreSessionStatus;
import org.qbix.pm.client.model.GameDTO;
import org.qbix.pm.client.notifications.ParticipantsReturnInfo;
import org.qbix.pm.client.notifications.NotificationListener;
import org.qbix.pm.client.view.interfaces.PMEndGameView;
import org.qbix.pm.client.view.interfaces.PMMainView;
import org.qbix.pm.client.view.interfaces.SessionSettingsPanelView;

import com.google.gson.JsonObject;

public class PlayerPlaymoreController extends AbstractPlaymoreController{

	private final RestController restController;

	public PlayerPlaymoreController(PMMainView teamView,
			PMEndGameView endGameView, long accountID) throws IOException {
		super(teamView, endGameView, accountID);
		this.restController = new RestControllerImpl();
		this.notificationListener = new NotificationListener(this);
	}

	@Override
	public void handleUpdateWithDTO(ParticipantsReturnInfo info) {
		if (this.sessionID == -1) {
			this.sessionID = info.getSessionId();
		}
		synchronized (dtoLock) {
			mainView.fill(gameDTO, info);
			mainView.showView();
		}
	}
}

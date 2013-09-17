package org.qbix.pm.client.controller;

import java.io.IOException;

import org.qbix.pm.client.controller.interfaces.AbstractPlaymoreController;
import org.qbix.pm.client.controller.interfaces.RestController;
import org.qbix.pm.client.model.GameDTO;
import org.qbix.pm.client.notifications.NotificationListener;
import org.qbix.pm.client.notifications.ParticipantsReturnInfo;
import org.qbix.pm.client.view.interfaces.PMEndGameView;
import org.qbix.pm.client.view.interfaces.PMMainView;

public class HostPlaymoreController extends AbstractPlaymoreController {

	private final RestController restController;

	public HostPlaymoreController(PMMainView teamView,
			PMEndGameView endGameView, long accountID) throws IOException {
		super(teamView, endGameView, accountID);
		// teamView.fill(initialDTO);
		this.restController = new RestControllerImpl();
		this.notificationListener = new NotificationListener(this);
	}

	@Override
	public void handleTeamSelectionDTO(GameDTO gameDTO){
		if(gameDTO == null){
			//TODO  sessionID = restController.registerSession();
		}else{
			// TODO restController.updateParticipants();
		}
	}

	@Override
	public void handleUpdateWithDTO(ParticipantsReturnInfo info) {
		synchronized (dtoLock) {
			mainView.fill(gameDTO, info);
			mainView.showView();
		}
	}

}

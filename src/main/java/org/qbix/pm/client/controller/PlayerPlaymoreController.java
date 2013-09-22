package org.qbix.pm.client.controller;

import java.io.IOException;

import org.qbix.pm.client.model.lol.LoLGameDTO;
import org.qbix.pm.client.model.pm.GameDTO;
import org.qbix.pm.client.notifications.NotificationListener;
import org.qbix.pm.client.view.interfaces.PMEndGameView;
import org.qbix.pm.client.view.interfaces.PMMainView;

public class PlayerPlaymoreController extends AbstractPlaymoreController {

	public PlayerPlaymoreController(PMMainView teamView,
			PMEndGameView endGameView, LoLGameDTO initialGameDTO, long pmAccountID)
			throws IOException {
		super(teamView, endGameView, initialGameDTO, pmAccountID);
		this.notificationListener = new NotificationListener(this);
		handleLoLGameDTO(initialGameDTO);
	}


	@Override
	public void handleGameSettingsChanged(GameDTO gameDTO) {
		if (this.gameID == -1) {
			this.gameID = gameDTO.getID();
		}
		synchronized (dtoLock) {
			mainView.fill(lolGameDTO, gameDTO);
			mainView.showView();
		}		
	}

//	@Override
//	public void handleUpdateWithDTO(ParticipantsReturnInfo info) {
//		if (this.sessionID == -1) {
//			this.sessionID = info.getSessionId();
//		}
//		synchronized (dtoLock) {
//			mainView.fill(gameDTO, info);
//			mainView.showView();
//		}
//	}
}

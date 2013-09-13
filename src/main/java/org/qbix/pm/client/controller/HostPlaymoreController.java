package org.qbix.pm.client.controller;

import java.io.IOException;

import org.qbix.pm.client.controller.interfaces.AbstractPlaymoreController;
import org.qbix.pm.client.controller.interfaces.HostController;
import org.qbix.pm.client.controller.interfaces.PlayerEntryPanelController;
import org.qbix.pm.client.controller.interfaces.RestController;
import org.qbix.pm.client.controller.interfaces.StartupClientController;
import org.qbix.pm.client.listener.LoLClientListener;
import org.qbix.pm.client.misc.PlaymoreSessionStatus;
import org.qbix.pm.client.misc.exceptions.InvalidDTOFormatException;
import org.qbix.pm.client.model.EndOfGameDTO;
import org.qbix.pm.client.model.GameDTO;
import org.qbix.pm.client.notifications.HostNotificationListener;
import org.qbix.pm.client.notifications.NotificationListener;
import org.qbix.pm.client.view.interfaces.PMEndGameView;
import org.qbix.pm.client.view.interfaces.PMTeamView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class HostPlaymoreController extends AbstractPlaymoreController {

	private final RestController restController;

	public HostPlaymoreController(PMTeamView teamView,
			PMEndGameView endGameView, long accountID, GameDTO initialDTO) throws IOException {
		super(teamView, endGameView, accountID);
		// teamView.fill(initialDTO);
		this.restController = new RestControllerImpl();
		this.notificationListener = new HostNotificationListener(this);
	}

	@Override
	public void handlePlayerDisconnect(Long accountID) {
		// TODO session inconsistent
	}

	@Override
	public void handleTeamSelectionDTO(GameDTO gameDTO){
		// restController.updateParticipants();
		// view.fill(gameDTO)
	}

	@Override
	public void startPlaymoreClient() {
		this.notificationListener.start();
		this.lolClientListener.start();
		// sessionID = restController.registerSession();
		this.teamView.showView();
	}

}

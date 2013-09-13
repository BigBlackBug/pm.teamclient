package org.qbix.pm.client.controller;

import java.io.IOException;

import org.qbix.pm.client.controller.interfaces.AbstractPlaymoreController;
import org.qbix.pm.client.controller.interfaces.PlayerController;
import org.qbix.pm.client.controller.interfaces.PlayerEntryPanelController;
import org.qbix.pm.client.controller.interfaces.RestController;
import org.qbix.pm.client.controller.interfaces.SessionSettingsPanelController;
import org.qbix.pm.client.controller.interfaces.StartupClientController;
import org.qbix.pm.client.listener.LoLClientListener;
import org.qbix.pm.client.misc.PlaymoreSessionStatus;
import org.qbix.pm.client.misc.exceptions.InvalidDTOFormatException;
import org.qbix.pm.client.model.EndOfGameDTO;
import org.qbix.pm.client.model.GameDTO;
import org.qbix.pm.client.notifications.NotificationListener;
import org.qbix.pm.client.notifications.PlayerNotificationListener;
import org.qbix.pm.client.view.interfaces.PMEndGameView;
import org.qbix.pm.client.view.interfaces.PMTeamView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class PlayerPlaymoreController extends AbstractPlaymoreController
		implements PlayerController {

	private final RestController restController;
	private volatile GameDTO gameDTO;

	public PlayerPlaymoreController(PMTeamView teamView,
			PMEndGameView endGameView, long accountID, GameDTO initialDTO) throws IOException {
		super(teamView, endGameView, accountID);
		this.restController = new RestControllerImpl();
		this.notificationListener = new PlayerNotificationListener(this);
		this.gameDTO = initialDTO;
	}

	@Override
	public void handleSessionParametersChange(JsonObject json) {
		SessionSettingsPanelController ssController = teamView
				.getSessionSettingsController();
		// TODO parseJson / setValues
	}

	@Override
	public void handleUpdateWithDTO(Long sessionID) {
		if (this.sessionID == -1) {
			this.sessionID = sessionID;
		}
		synchronized (dtoLock) {
			// TODO view.fill(gameDTO)
		}
	}

	@Override
	public void handleSessionStarting() {
		teamView.setSessionStatus(PlaymoreSessionStatus.STARTED);
	}

	@Override
	public void handlePlayerDisconnect(Long accountID) {
		// TODO session inconsistent
	}

	@Override
	public void handleTeamSelectionDTO(GameDTO gameDTO){
		// this.gameDTO = gameDTOJson.toDTO();
		// waiting for 'updatewithdto' to come
	}

	@Override
	public void startPlaymoreClient() {
		this.notificationListener.start();
		this.lolClientListener.start();
		handleGameDTO(gameDTO);
	}

}

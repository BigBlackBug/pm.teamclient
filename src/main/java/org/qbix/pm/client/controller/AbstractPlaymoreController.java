package org.qbix.pm.client.controller;

import java.io.IOException;

import org.qbix.pm.client.controller.interfaces.PlaymoreController;
import org.qbix.pm.client.controller.interfaces.StartupClientController;
import org.qbix.pm.client.listener.LoLClientListener;
import org.qbix.pm.client.misc.PlaymoreGameStatus;
import org.qbix.pm.client.misc.enums.GameState;
import org.qbix.pm.client.misc.exceptions.InvalidDTOFormatException;
import org.qbix.pm.client.misc.exceptions.RequestFailedException;
import org.qbix.pm.client.model.lol.EndOfGameDTO;
import org.qbix.pm.client.model.lol.LoLGameDTO;
import org.qbix.pm.client.model.pm.GameDTO;
import org.qbix.pm.client.notifications.NotificationListener;
import org.qbix.pm.client.view.interfaces.PMEndGameView;
import org.qbix.pm.client.view.interfaces.PMMainView;
import org.qbix.pm.client.view.interfaces.PlayerEntryPanelView;
import org.qbix.pm.client.view.interfaces.GameSettingsPanelView;

import com.google.gson.JsonObject;

public abstract class AbstractPlaymoreController implements
		StartupClientController, PlaymoreController {
	protected final PMMainView mainView;
	protected final PMEndGameView endGameView;
	protected final LoLClientListener lolClientListener;
	
	protected final GeneralRestController restController;

	protected NotificationListener notificationListener;
	protected long gameID = -1;
	protected long pmAccountID = -1;
	protected volatile LoLGameDTO lolGameDTO;

	protected final Object dtoLock = new Object();

	protected AbstractPlaymoreController(PMMainView mainView,
			PMEndGameView endGameView, LoLGameDTO initialGameDTO, long pmAccountID)
			throws IOException {
		this.mainView = mainView;
		this.endGameView = endGameView;
		this.pmAccountID = pmAccountID;
		this.restController = new GeneralRestController();
		this.lolClientListener = new LoLClientListener(this);
		this.notificationListener = new NotificationListener(this);
	}

	@Override
	public void handleStakeConfirmation(Long accountID) {
		PlayerEntryPanelView panelController = mainView
				.getPlayerEntryPanelView(accountID);
		panelController.setConfirmedParticipation(true);
	}

	@Override
	public void handleStakeCancellation(Long accountID) {
		PlayerEntryPanelView panelController = mainView
				.getPlayerEntryPanelView(accountID);
		panelController.setConfirmedParticipation(false);
	}

	@Override
	public void handleEndOfGameDTO(EndOfGameDTO endOfGameDTO)
			throws InvalidDTOFormatException {
		mainView.setSessionStatus(PlaymoreGameStatus.FINISHED);
		endGameView.fill(endOfGameDTO);
		endGameView.showView();
		restController.resolveResult(gameID);
	}

	@Override
	public void startPlaymoreClient() {
		this.notificationListener.start();
		this.lolClientListener.start();
	}
	
	@Override
	public void disconnect() {
		try {
			restController.sendDisconnect(gameID, pmAccountID);
		} catch (RequestFailedException e) {
			mainView.setSessionStatus(PlaymoreGameStatus.SESSION_INCONSISTENT);
			//TODO display or whatever String message = e.getMessage();
		} catch (IOException e) {
			//TODO display GG or whatever
		}
	}

	@Override
	public void handlePlayerDisconnect(Long accountID) {
		mainView.setSessionStatus(PlaymoreGameStatus.SESSION_INCONSISTENT);
		PlayerEntryPanelView v = mainView.getPlayerEntryPanelView(accountID);
		v.playerDisconnected();
	}

	@Override
	public void handleGameStarting() {
		mainView.setSessionStatus(PlaymoreGameStatus.STARTED);
	}
	
	@Override
	public void handleLoLGameDTO(LoLGameDTO lolGameDTO) throws InvalidDTOFormatException {
		if (!isLoLDTOValid(lolGameDTO)) {
			throw new InvalidDTOFormatException("received invalid dto");
		}

		GameState state = GameState.get(lolGameDTO.getGameState());
		switch (state) {
		case CHAMP_SELECT: {
			// team lined up.update champs
			mainView.setSessionStatus(PlaymoreGameStatus.SELECTING_CHAMPIONS);
			break;
		}
		case START_REQUESTED: {
			// print "starting" or smth
			mainView.setSessionStatus(PlaymoreGameStatus.GAME_IN_PROGRESS);
			break;
		}
		case TERMINATED: {
			// print "hooray terminted" or smth
			// endgamestats should have arrived by now
			mainView.setSessionStatus(PlaymoreGameStatus.FINISHED);
			break;
		}
		case TERMINATED_IN_ERROR: {
			// game wasn't finished. happened when everybody left
			mainView.setSessionStatus(PlaymoreGameStatus.FINISHED_WITH_ERROR);
			break;
		}
		case TEAM_SELECT: {
			// update team lineup
			synchronized (dtoLock) {
				handleTeamLineupChanged(lolGameDTO);
				this.lolGameDTO = lolGameDTO;
			}
		}
		}
	}

	protected void handleTeamLineupChanged(LoLGameDTO gameDTO){
	}

	protected boolean isInTeamSelect(LoLGameDTO gameDTO) {
		String gameState = gameDTO.getGameState();
		return isLoLDTOValid(gameDTO)
				&& (GameState.get(gameState) == GameState.TEAM_SELECT);
	}

	protected boolean isLoLDTOValid(LoLGameDTO gameDTO) {
		String gameMode = gameDTO.getGameMode();
		String gameType = gameDTO.getGameType();
		String queueType = gameDTO.getQueueTypeName();
		return queueType.equals("NONE") && gameMode.equals("CLASSIC")
				&& gameType.equals("PRACTICE_GAME");
	}
}

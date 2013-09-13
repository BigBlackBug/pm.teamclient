package org.qbix.pm.client.controller.interfaces;

import java.io.IOException;

import org.qbix.pm.client.listener.LoLClientListener;
import org.qbix.pm.client.misc.GameState;
import org.qbix.pm.client.misc.PlaymoreSessionStatus;
import org.qbix.pm.client.misc.exceptions.InvalidDTOFormatException;
import org.qbix.pm.client.model.EndOfGameDTO;
import org.qbix.pm.client.model.GameDTO;
import org.qbix.pm.client.notifications.NotificationListener;
import org.qbix.pm.client.view.interfaces.PMEndGameView;
import org.qbix.pm.client.view.interfaces.PMTeamView;

public abstract class AbstractPlaymoreController implements
		StartupClientController, HostController {
	protected final PMTeamView teamView;
	protected final PMEndGameView endGameView;
	protected final LoLClientListener lolClientListener;

	protected NotificationListener notificationListener;
	protected long sessionID = -1;
	protected long accountID = -1;

	protected final Object dtoLock = new Object();

	protected AbstractPlaymoreController(PMTeamView teamView,
			PMEndGameView endGameView, long accountID) throws IOException {
		this.teamView = teamView;
		this.endGameView = endGameView;
		this.accountID = accountID;
		this.lolClientListener = new LoLClientListener(this);
	}

	@Override
	public void handleStakeConfirmation(Long accountID) {
		PlayerEntryPanelController panelController = teamView
				.getPlayerEntryPanelController(accountID);
		panelController.setConfirmedStake(true);
	}

	@Override
	public void handleStakeCancellation(Long accountID) {
		PlayerEntryPanelController panelController = teamView
				.getPlayerEntryPanelController(accountID);
		panelController.setConfirmedStake(false);
	}

	@Override
	public void handleEndOfGameDTO(EndOfGameDTO endOfGameDTO)
			throws InvalidDTOFormatException {
		teamView.setSessionStatus(PlaymoreSessionStatus.FINISHED);
		endGameView.fill(endOfGameDTO);
		endGameView.showView();
	}

	@Override
	public void disconnect() {
		// TODO restController.sendDisconnect()
	}
	
	@Override
	public void handleGameDTO(GameDTO gameDTO) throws InvalidDTOFormatException {
		if (!isGameDTOValid(gameDTO)) {
			throw new InvalidDTOFormatException("dto values are not supported");
		}

		GameState state = GameState.get(gameDTO.getGameState());
		switch (state) {
		case CHAMP_SELECT: {
			// team lined up.update champs
			teamView.setSessionStatus(PlaymoreSessionStatus.SELECTING_CHAMPIONS);
			break;
		}
		case START_REQUESTED: {
			// print "starting" or smth
			teamView.setSessionStatus(PlaymoreSessionStatus.GAME_IN_PROGRESS);
			break;
		}
		case TERMINATED: {
			// print "hooray terminted" or smth
			// endgamestats should have arrived by now
			teamView.setSessionStatus(PlaymoreSessionStatus.FINISHED);
			break;
		}
		case TERMINATED_IN_ERROR: {
			// game wasn't finished. happened when everybody left
			teamView.setSessionStatus(PlaymoreSessionStatus.FINISHED_WITH_ERROR);
			break;
		}
		case TEAM_SELECT: {
			// update team lineup
			synchronized (dtoLock) {
				handleTeamSelectionDTO(gameDTO);
			}
		}
		}
	}

	protected abstract void handleTeamSelectionDTO(GameDTO gameDTO);

	protected boolean isInTeamSelect(GameDTO gameDTO) {
		String gameState = gameDTO.getGameState();
		return isGameDTOValid(gameDTO)
				&& (GameState.get(gameState) == GameState.TEAM_SELECT);
	}

	protected boolean isGameDTOValid(GameDTO gameDTO) {
		String gameMode = gameDTO.getGameMode();
		String gameType = gameDTO.getGameType();
		String queueType = gameDTO.getQueueTypeName();
		return queueType.equals("NONE") && gameMode.equals("CLASSIC")
				&& gameType.equals("PRACTICE_GAME");
	}
}

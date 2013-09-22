package org.qbix.pm.client.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.qbix.pm.client.misc.exceptions.RequestFailedException;
import org.qbix.pm.client.model.lol.LoLGameDTO;
import org.qbix.pm.client.model.pm.GameDTO;
import org.qbix.pm.client.view.interfaces.PMEndGameView;
import org.qbix.pm.client.view.interfaces.PMMainView;

public class HostPlaymoreController extends AbstractPlaymoreController {

	private static final BigDecimal DEFAULT_STAKE = new BigDecimal(100);
	
	private final HostRestController restController;

	public HostPlaymoreController(PMMainView teamView,
			PMEndGameView endGameView, LoLGameDTO initialGameDTO, long pmAccountID)
			throws IOException {
		super(teamView, endGameView, initialGameDTO, pmAccountID);
		this.restController = new HostRestController();
		handleLoLGameDTO(initialGameDTO);
	}

	@Override
	public void handleTeamLineupChanged(LoLGameDTO lolGameDTO) {
		if (this.lolGameDTO == null) {
			try {
				gameID = restController.registerSession(DEFAULT_STAKE,
						pmAccountID);
			} catch (RequestFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				//TODO fill
				restController.updateGame(gameID, lolGameDTO, null, null);
			} catch (RequestFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handleGameSettingsChanged(GameDTO gameDTO) {
		synchronized (dtoLock) {
			mainView.fill(lolGameDTO, gameDTO);
			mainView.showView();
		}		
	}

	
//	@Override
//	public void handleUpdateWithDTO(ParticipantsReturnInfo info) {

//	}

}

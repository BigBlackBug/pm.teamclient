package org.qbix.pm.client.controller;

import java.io.IOException;

import org.qbix.pm.client.listener.LoLClientListener;
import org.qbix.pm.client.notifications.NotificationListener;
import org.qbix.pm.client.view.View;

import com.google.gson.JsonObject;

public class PlaymoreController implements Controller, StartupClientController {

	private final View view;
	private final LoLClientListener lolClientListener;
	private final NotificationListener notificationListener;

	private final Object dtoLock = new Object();

	public PlaymoreController(View view) throws IOException {
		this.view = view;
		this.lolClientListener = new LoLClientListener(this);
		this.lolClientListener.start();
		this.notificationListener = new NotificationListener(this);
		this.notificationListener.start();
	}

	@Override
	public void handleSessionParametersChange(JsonObject json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleDTOUpdate(Long sessionID) {
		synchronized (dtoLock) {

		}
	}

	@Override
	public void handlePlayerDisconnect(Long accountID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleStakeConfirmation(Long accountID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleStakeCancellation(Long accountID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleSessionStarting() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleLolClientDTO(String dtoJson) {
		synchronized (dtoLock) {

		}
	}

}

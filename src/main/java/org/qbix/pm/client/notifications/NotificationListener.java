package org.qbix.pm.client.notifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.qbix.pm.client.controller.interfaces.PlaymoreController;
import org.qbix.pm.client.misc.PlaymoreSettings;
import org.qbix.pm.client.model.pm.GameDTO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class NotificationListener {
	private static final String NOTIFICATION_SERVER_HOSTNAME = PlaymoreSettings
			.getNotificationServerHostname();
	private static final int NOTIFICATION_SERVER_PORT = PlaymoreSettings
			.getNotificationServerPort();

	private final Gson gson;
	private final PlaymoreController controller;
	private final Socket socket;

	public NotificationListener(PlaymoreController controller) throws IOException {
		this.gson = new Gson();
		this.controller = controller;
		this.socket = new Socket(NOTIFICATION_SERVER_HOSTNAME,
				NOTIFICATION_SERVER_PORT);
	}

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				BufferedReader in;
				try {
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
				String notificationJson;
				try {
					while ((notificationJson = in.readLine()) != null) {
						Notification n = gson.fromJson(notificationJson,
								Notification.class);
						String jsonData = n.getJsonData();
						JsonObject json = gson.fromJson(jsonData,
								new TypeToken<JsonObject>() {
								}.getType());
						switch (n.getNotificationType()) {
						case PLAYER_CANCELLED_STAKE: {
							controller.handleStakeCancellation(json.get(
									"account_id").getAsLong());
							break;
						}
						case PLAYER_CONFIRMED_STAKE: {
							controller.handleStakeConfirmation(json.get(
									"account_id").getAsLong());
							break;
						}
						case PLAYER_DISCONNECTED: {
							controller.handlePlayerDisconnect(json.get(
									"account_id").getAsLong());
							break;
						}
						case GAME_PARAMETERS_CHANGED: {
							controller.handleGameSettingsChanged(gson.fromJson(json,
									GameDTO.class));
							break;
						}
						case GAME_STARTED: {
							controller.handleGameStarting();
							break;
						}case GAME_CANCELLED:{
							//TODO handle
						}
							break;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

}
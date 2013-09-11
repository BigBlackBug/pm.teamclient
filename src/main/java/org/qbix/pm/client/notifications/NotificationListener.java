package org.qbix.pm.client.notifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import org.qbix.pm.client.controller.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class NotificationListener {
	//TODO move this to configuration file
	private static final String NOTIFICATION_SERVER_HOSTNAME = "localhost";
	private static final int NOTIFICATION_SERVER_PORT = 4444;

	private final Gson gson;
	private final Controller controller;
	private final Socket socket;

	public NotificationListener(Controller controller) throws IOException {
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
						case SESSION_PARAMETERS_CHANGED: {
							controller.handleSessionParametersChange(json);
							break;
						}
						case SESSION_STARTED: {
							controller.handleSessionStarting();
							break;
						}
						case UPDATE_WITH_GAMEDTO: {
							controller.handleDTOUpdate(json.get("session_id")
									.getAsLong());
							break;
						}
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
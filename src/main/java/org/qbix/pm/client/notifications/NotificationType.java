package org.qbix.pm.client.notifications;

import java.io.Serializable;

public enum NotificationType implements Serializable{

	GAME_PARAMETERS_CHANGED,

	GAME_STARTED,

	PLAYER_CONFIRMED_STAKE,

	PLAYER_CANCELLED_STAKE,

	PLAYER_DISCONNECTED,
	
	GAME_CANCELLED;
}

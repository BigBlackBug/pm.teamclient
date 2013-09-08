package org.qbix.pm.client.notifications;


public class NotificationListener {

	private void doStuff(String queueType, String gameMode, String gameType,
			String gameState) {
		if (queueType.equals("NONE") && gameMode.equals("CLASSIC")
				&& gameType.equals("PRACTICE_GAME")) {
			if (gameState.equals("TEAM_SELECT")) {
				// update team lineup
			} else if (gameState.equals("CHAMP_SELECT")) {
				// team lined up.update champs
			} else if (gameState.equals("START_REQUESTED")) {
				// print "starting" or smth
			} else if (gameState.equals("TERMINATED")) {
				// print "hooray terminted" or smth
				// endgamestats should have arrived by now
			} else if (gameState.equals("TERMINATED_IN_ERROR")) {
				// game wasn't finished. happened when everybody left
			}
		} else {
			// unknown GameDTO
		}
	}
}
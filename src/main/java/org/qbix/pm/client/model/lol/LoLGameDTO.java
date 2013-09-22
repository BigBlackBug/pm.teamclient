/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qbix.pm.client.model.lol;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author BigBlackBug
 */
public class LoLGameDTO {

	private Date TimeStamp;
	private String ObjectType;
	private String gameState; // "TEAM_SELECT"
	private String gameMode; // "CLASSIC"
	private String gameType; // "PRACTICE_GAME"
	private Integer mapId;
	private Double id;
	private Integer maxNumPlayers;
	private String name; // "Jabe's game"
	private Integer pickTurn; //check but I think 0 = not time to pick, 1 = pick, 2 = champion locked
	private List<PlayerChampionSelectionDTO> playerChampionSelections; // our team selection(why?)
	private String queueTypeName; // cf. QueueType enum
	private List<PlayerParticipant> teamOne;
	private List<PlayerParticipant> teamTwo;

	public Date getTimeStamp() {
		return TimeStamp;
	}

	public String getObjectType() {
		return ObjectType;
	}

	public String getGameState() {
		return gameState;
	}

	public String getGameMode() {
		return gameMode;
	}

	public String getGameType() {
		return gameType;
	}

	public Integer getMapId() {
		return mapId;
	}

	public Double getId() {
		return id;
	}

	public Integer getMaxNumPlayers() {
		return maxNumPlayers;
	}

	public String getName() {
		return name;
	}

	public Integer getPickTurn() {
		return pickTurn;
	}

	public List<PlayerChampionSelectionDTO> getPlayerChampionSelections() {
		return playerChampionSelections;
	}

	public String getQueueTypeName() {
		return queueTypeName;
	}

	public List<PlayerParticipant> getTeamOne() {
		return teamOne;
	}

	public List<PlayerParticipant> getTeamTwo() {
		return teamTwo;
	}

}

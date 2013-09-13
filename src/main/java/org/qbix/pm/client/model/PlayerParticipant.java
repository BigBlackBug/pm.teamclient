package org.qbix.pm.client.model;

public class PlayerParticipant  {
	
	private Integer accountId;
	private Integer profileIconId;
	private Integer summonerId;
	private Integer teamParticipantId;
	private Integer pickMode;
	private String summonerName;
	private String summonerInternalName;
	private Integer pickTurn;

	public Integer getAccountId() {
		return accountId;
	}

	public Integer getProfileIconId() {
		return profileIconId;
	}

	public Integer getSummonerId() {
		return summonerId;
	}

	public Integer getTeamParticipantId() {
		return teamParticipantId;
	}

	public Integer getPickMode() {
		return pickMode;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public String getSummonerInternalName() {
		return summonerInternalName;
	}

	public Integer getPickTurn() {
		return pickTurn;
	}

}

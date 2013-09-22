package org.qbix.pm.client.model.lol;

public class PlayerParticipant  {
	//TODO refactor to long?
	private Long accountId;
	private Long profileIconId;
	private Long summonerId;
	private Long teamParticipantId;
	private Long pickMode;
	private String summonerName;
	private String summonerInternalName;
	private Long pickTurn;

	public Long getAccountId() {
		return accountId;
	}

	public Long getProfileIconId() {
		return profileIconId;
	}

	public Long getSummonerId() {
		return summonerId;
	}

	public Long getTeamParticipantId() {
		return teamParticipantId;
	}

	public Long getPickMode() {
		return pickMode;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public String getSummonerInternalName() {
		return summonerInternalName;
	}

	public Long getPickTurn() {
		return pickTurn;
	}

}

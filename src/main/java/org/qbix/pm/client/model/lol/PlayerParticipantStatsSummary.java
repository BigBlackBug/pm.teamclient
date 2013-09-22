package org.qbix.pm.client.model.lol;

import java.util.List;

public class PlayerParticipantStatsSummary {

	private Double userId;
	private String summonerName;
	private Double level;
	private Integer profileIconId;
	private Double gameId;
	private String skinName;
	private Integer elo;
	private Boolean leaver;
	private Double leaves;
	private Double teamId;
	private Integer eloChange;
	private List<RawStatDTO> statistics;
	private Boolean botPlayer;
	private Double spell1Id;
	private Double spell2Id;
	private Double wins;
	private Double losses;

	public Double getUserId() {
		return userId;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public Double getLevel() {
		return level;
	}

	public Integer getProfileIconId() {
		return profileIconId;
	}

	public Double getGameId() {
		return gameId;
	}

	public String getSkinName() {
		return skinName;
	}

	public Integer getElo() {
		return elo;
	}

	public Boolean getLeaver() {
		return leaver;
	}

	public Double getLeaves() {
		return leaves;
	}

	public Double getTeamId() {
		return teamId;
	}

	public Integer getEloChange() {
		return eloChange;
	}

	public List<RawStatDTO> getStatistics() {
		return statistics;
	}

	public Boolean getBotPlayer() {
		return botPlayer;
	}

	public Double getSpell1Id() {
		return spell1Id;
	}

	public Double getSpell2Id() {
		return spell2Id;
	}

	public Double getWins() {
		return wins;
	}

	public Double getLosses() {
		return losses;
	}

}

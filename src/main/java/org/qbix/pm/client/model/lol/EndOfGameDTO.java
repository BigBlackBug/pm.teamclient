package org.qbix.pm.client.model.lol;

import java.util.List;

public class EndOfGameDTO {

	private Integer basePoints;
	private Double boostIpEarned;
	private Double boostXpEarned;
	private Integer completionBonusPoints;
	private Integer elo;
	private String difficulty;
	private Double experienceEarned;
	private Integer eloChange;
	private Double experienceTotal;
	private Double firstWinBonus;
	private Double gameId;
	private Double gameLength;
	private String gameType;
	private String gameMode;
	private Boolean imbalancedTeamsNoPoints;
	private Double ipEarned;
	private Double ipTotal;
	private Boolean leveledUp;
	private Double loyaltyBoostIpEarned;
	private Double loyaltyBoostXpEarned;
	private Integer odinBonusIp;
	private List<PlayerParticipantStatsSummary> otherTeamPlayerParticipantStats;
	private Integer queueBonusEarned;
	private String queueType;
	private Boolean ranked;
	private Integer skinIndex;
	private Integer talentPointsGained;
	private List<PlayerParticipantStatsSummary> teamPlayerParticipantStats;
	private Double timeUntilNextFirstWinBonus;
	private Double userId;
	
	public Integer getBasePoints() {
		return basePoints;
	}

	public Double getBoostIpEarned() {
		return boostIpEarned;
	}

	public Double getBoostXpEarned() {
		return boostXpEarned;
	}

	public Integer getCompletionBonusPoints() {
		return completionBonusPoints;
	}

	public Integer getElo() {
		return elo;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public Double getExperienceEarned() {
		return experienceEarned;
	}

	public Integer getEloChange() {
		return eloChange;
	}

	public Double getExperienceTotal() {
		return experienceTotal;
	}

	public Double getFirstWinBonus() {
		return firstWinBonus;
	}

	public Double getGameId() {
		return gameId;
	}

	public Double getGameLength() {
		return gameLength;
	}

	public String getGameType() {
		return gameType;
	}

	public String getGameMode() {
		return gameMode;
	}

	public Boolean getImbalancedTeamsNoPoints() {
		return imbalancedTeamsNoPoints;
	}

	public Double getIpEarned() {
		return ipEarned;
	}

	public Double getIpTotal() {
		return ipTotal;
	}

	public Boolean getLeveledUp() {
		return leveledUp;
	}

	public Double getLoyaltyBoostIpEarned() {
		return loyaltyBoostIpEarned;
	}

	public Double getLoyaltyBoostXpEarned() {
		return loyaltyBoostXpEarned;
	}

	public Integer getOdinBonusIp() {
		return odinBonusIp;
	}

	public List<PlayerParticipantStatsSummary> getOtherTeamPlayerParticipantStats() {
		return otherTeamPlayerParticipantStats;
	}

	public Integer getQueueBonusEarned() {
		return queueBonusEarned;
	}

	public String getQueueType() {
		return queueType;
	}

	public Boolean getRanked() {
		return ranked;
	}

	public Integer getSkinIndex() {
		return skinIndex;
	}

	public Integer getTalentPointsGained() {
		return talentPointsGained;
	}

	public List<PlayerParticipantStatsSummary> getTeamPlayerParticipantStats() {
		return teamPlayerParticipantStats;
	}

	public Double getTimeUntilNextFirstWinBonus() {
		return timeUntilNextFirstWinBonus;
	}

	public Double getUserId() {
		return userId;
	}
}

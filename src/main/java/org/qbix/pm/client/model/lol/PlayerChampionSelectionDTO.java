package org.qbix.pm.client.model.lol;

public class PlayerChampionSelectionDTO {

	private String summonerInternalName;
	private Integer championId;
	private Integer spell1Id; // -1 when no selection
	private Integer spell2Id; // -1 when no selection
	private Integer selectedSkinIndex;

	public String getSummonerInternalName() {
		return summonerInternalName;
	}

	public Integer getChampionId() {
		return championId;
	}

	public Integer getSpell1Id() {
		return spell1Id;
	}

	public Integer getSpell2Id() {
		return spell2Id;
	}

	public Integer getSelectedSkinIndex() {
		return selectedSkinIndex;
	}

}

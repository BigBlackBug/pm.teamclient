package org.qbix.pm.client.model.pm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GameDTO implements Serializable{

	private static final long serialVersionUID = 3478578098981496431L;

	private Long ID;

	/** "LOL" , "HON" */
	private String type;

	private VictoryCriteriaDTO vc;

	private BigDecimal stake;

	private List<PlayerEntryDTO> playerEntries;

	private Integer status;

	public GameDTO() {
	}

	public GameDTO(Long gameId) {
		ID = gameId;
	}

	public void setID(Long gameId) {
		this.ID = gameId;
	}

	public Long getID() {
		return ID;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setVc(VictoryCriteriaDTO vc) {
		this.vc = vc;
	}

	public VictoryCriteriaDTO getVc() {
		return vc;
	}

	public void setStake(BigDecimal stake) {
		this.stake = stake;
	}

	public BigDecimal getStake() {
		return stake;
	}

	public void setPlayerEntries(List<PlayerEntryDTO> playerEntries) {
		this.playerEntries = playerEntries;
	}

	public List<PlayerEntryDTO> getPlayerEntries() {
		return playerEntries;
	}

}

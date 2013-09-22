package org.qbix.pm.client.model.pm;

import java.io.Serializable;

public class PlayerEntryDTO implements Serializable{

	private static final long serialVersionUID = -1957412849571608071L;

	/** 0/1 */
	private int team;
	
	private Long accountId;
	
	/** read only for client */
	private String nick;
	
	/** read only for client */
	private Long lolAccountId;
	
	/** read only for client */
	private String summonersNick;

	public PlayerEntryDTO() {
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public int getTeam() {
		return team;
	}
	
	public void setTeam(int team) {
		this.team = team;
	}
	
	public Long getLolAccountId() {
		return lolAccountId;
	}
	
	public void setLolAccountId(Long lolAccountId) {
		this.lolAccountId = lolAccountId;
	}
	
	public String getSummonersNick() {
		return summonersNick;
	}
	
	public void setSummonersNick(String summonersNick) {
		this.summonersNick = summonersNick;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}

}

package org.qbix.pm.client.model.pm;

import java.io.Serializable;

public class UserJoinDTO implements Serializable {

	private static final long serialVersionUID = 7025917910203033553L;

	private Long gameId;

	private Long accountId;

	public UserJoinDTO() {
	}

	public void setGameId(Long gameID) {
		this.gameId = gameID;
	}

	public Long getGameId() {
		return gameId;
	}
	
	public void setAccountId(Long accountid) {
		this.accountId = accountid;
	}
	
	public Long getAccountid() {
		return accountId;
	}
	
}

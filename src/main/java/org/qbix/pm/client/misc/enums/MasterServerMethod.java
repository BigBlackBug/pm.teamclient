package org.qbix.pm.client.misc.enums;

public enum MasterServerMethod {

	PLAYER_DISCONNECTED("player_disconnected"),

	REGISTER_SESSION("register_game"),

	UPDATE_GAME("update_game"), 
	
	CONFIRM_PARTICIPATION("confpart"), 
	
	CANCEL_PARTICIPATION("cancelpart"), 
	
	START_GAME("start_game"), 
	
	RESOLVE_RESULT("resolve_result"),
	
	CANCEL_GAME("cancel_game"),
	
	LOL_ACCOUNT_ID_TO_PM_ACCOUNT_ID("get_pmid_by_lolid");

	private final String method;

	private MasterServerMethod(String method) {
		this.method = method;
	}

	public String getMethodName() {
		return method;
	}

}

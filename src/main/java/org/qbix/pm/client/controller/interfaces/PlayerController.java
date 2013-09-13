package org.qbix.pm.client.controller.interfaces;

import com.google.gson.JsonObject;

public interface PlayerController extends HostController{

	public void handleSessionParametersChange(JsonObject json);

	public void handleUpdateWithDTO(Long sessionID);

	public void handleSessionStarting();
	
}

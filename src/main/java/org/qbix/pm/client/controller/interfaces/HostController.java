package org.qbix.pm.client.controller.interfaces;

public interface HostController extends PlaymoreController{

	public void handlePlayerDisconnect(Long accountID);

	public void handleStakeConfirmation(Long accountID);

	public void handleStakeCancellation(Long accountID);
	
	public void disconnect();

}

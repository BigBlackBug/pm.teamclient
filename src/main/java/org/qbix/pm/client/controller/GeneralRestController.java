package org.qbix.pm.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.qbix.pm.client.misc.PlaymoreSettings;
import org.qbix.pm.client.misc.enums.MasterServerMethod;
import org.qbix.pm.client.misc.exceptions.RequestFailedException;
import org.qbix.pm.client.model.pm.UserJoinDTO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class GeneralRestController {

	private static final String MASTER_SERVER_ADDRESS = PlaymoreSettings
			.getMasterServerHostname();

	protected final Gson gson;

	public GeneralRestController() {
		gson = new Gson();
	}

	protected String sendRequest(MasterServerMethod method, String json)
			throws IOException, RequestFailedException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(MASTER_SERVER_ADDRESS + "/"
				+ method.getMethodName());
		StringEntity input = new StringEntity(json);
		post.setEntity(input);
		HttpResponse response = client.execute(post);
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw new RequestFailedException(statusCode);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		return in.readLine();
	}

	protected String sendRequest(MasterServerMethod method, JsonObject json)
			throws IOException, RequestFailedException {
		return sendRequest(method, json.toString());
	}

	protected Map<Long, Long> getPmAccountIDs(List<Long> lolaccountIDs)
			throws IOException, RequestFailedException {
		String json = sendRequest(
				MasterServerMethod.LOL_ACCOUNT_ID_TO_PM_ACCOUNT_ID,
				gson.toJson(lolaccountIDs, new TypeToken<List<Long>>() {
				}.getType()));
		return gson.fromJson(json, new TypeToken<Map<Long, Long>>() {
		}.getType());
	}

	public void sendDisconnect(long gameID, long pmAccountID)
			throws IOException, RequestFailedException {
		UserJoinDTO dto = new UserJoinDTO();
		dto.setAccountId(pmAccountID);
		dto.setGameId(gameID);
		sendRequest(MasterServerMethod.PLAYER_DISCONNECTED, gson.toJson(dto));
	}

	public void resolveResult(long gameID) {
		//TODO implement
	}

}

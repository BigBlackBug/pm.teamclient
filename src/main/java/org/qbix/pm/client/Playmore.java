package org.qbix.pm.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.qbix.pm.client.controller.HostPlaymoreController;
import org.qbix.pm.client.controller.PlayerPlaymoreController;
import org.qbix.pm.client.controller.interfaces.PlaymoreClient;
import org.qbix.pm.client.misc.PlaymoreSettings;
import org.qbix.pm.client.misc.exceptions.InvalidDTOFormatException;
import org.qbix.pm.client.model.lol.LoLGameDTO;
import org.qbix.pm.client.model.lol.PlayerParticipant;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class Playmore {

	public void start() throws IOException {
		PlaymoreClient playmore = initializeClient();
		playmore.startPlaymoreClient();
	}

	private PlaymoreClient initializeClient() throws IOException {
		ServerSocket listener = new ServerSocket(4545);
		try {
			Socket socket = listener.accept();
			try {
				System.out.println("accepted " + socket.getInetAddress());
				InputStream in = socket.getInputStream();
				System.out.println("-------PMAccountID-------");
				String pmAccountIdString = readString(in);
				long pmAccountID = Long.parseLong(pmAccountIdString);
				System.out.println("-------JSON-------");
				String json = readString(in);
				JsonParser parser = new JsonParser();
				JsonObject asJsonObject = parser.parse(json).getAsJsonObject();
				if (asJsonObject.get("ObjectType").getAsString()
						.equals("GameDTO")) {
					System.out.println("received gameDTO");
					LoLGameDTO gameDTO = new Gson().fromJson(asJsonObject,
							new TypeToken<LoLGameDTO>() {
							}.getType());
					return getController(gameDTO, pmAccountID);
				} else {
					throw new InvalidDTOFormatException();
				}
			} finally {
				socket.close();
				System.out.println("closed ");
			}
		} finally {
			listener.close();
		}
	}

	private byte[] read(InputStream in) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buffer[] = new byte[1024];
		for (int s; (s = in.read(buffer)) != -1;) {
			baos.write(buffer, 0, s);
		}
		return baos.toByteArray();
	}

	private String readString(InputStream in) throws IOException {
		byte[] read = read(in);
		return new String(read, "UTF-8");
	}

	private PlaymoreClient getController(LoLGameDTO initialGameDTO, long pmAccountID)
			throws IOException {
		List<PlayerParticipant> teamOne = initialGameDTO.getTeamOne();
		List<PlayerParticipant> teamTwo = initialGameDTO.getTeamTwo();
		if (teamOne.size() + teamTwo.size() == 1) {// i am the host
			return new HostPlaymoreController(null, null, initialGameDTO,
					pmAccountID);
		} else {
			return new PlayerPlaymoreController(null, null, initialGameDTO,
					pmAccountID);
		}
	}

}
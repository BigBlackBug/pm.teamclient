package main;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PlaymoreClient {

	/**
	 * Runs the server.
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(4545);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					
					System.out.println("accepted " + socket.getInetAddress());
					InputStream in = socket.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte buffer[] = new byte[1024];
					for (int s; (s = in.read(buffer)) != -1;) {
						baos.write(buffer, 0, s);
					}
					byte result[] = baos.toByteArray();
					System.out.println("-------JSON-------");
					String json = new String(result, "UTF-8");
					JsonParser parser = new JsonParser();
					JsonObject asJsonObject = parser.parse(json).getAsJsonObject();
					if(asJsonObject.get("ObjectType").getAsString().equals("GameDTO")){
						System.out.println("received gameDTO");
					}
					System.out.println(json);
					System.out.println("-------JSON_END-------");
					// PrintWriter out =
					// new PrintWriter(socket.getOutputStream(), true);
					// out.println(new Date().toString());
				} finally {
					socket.close();
					System.out.println("closed ");
				}
			}
		} finally {
			listener.close();
		}

	}

	private void doStuff(String queueType,String gameMode,String gameType,String gameState) {
		if (queueType.equals("NONE")
				&&gameMode.equals("CLASSIC")
				&&gameType.equals("PRACTICE_GAME")) {
			if (gameState.equals("TEAM_SELECT")) {
				// update team lineup
			} else if (gameState.equals("CHAMP_SELECT")) {
				// team lined up.update champs
			} else if (gameState.equals("START_REQUESTED")) {
				// print "starting" or smth
			} else if (gameState.equals("TERMINATED")) {
				// print "hooray terminted" or smth
				// endgamestats should have arrived by now
			} else if (gameState.equals("TERMINATED_IN_ERROR")) {
				// game wasn't finished. happened when everybody left
			}
		} else {
			// unknown GameDTO
		}
	}
}
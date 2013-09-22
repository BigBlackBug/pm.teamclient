package org.qbix.pm.client.listener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.qbix.pm.client.controller.interfaces.StartupClientController;
import org.qbix.pm.client.model.lol.EndOfGameDTO;
import org.qbix.pm.client.model.lol.LoLGameDTO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class LoLClientListener {
	private static final int DEFAULT_BLOCK_SIZE = 1024;
	private static final int STARTUP_CLIENT_PORT = 4545;
	private final StartupClientController controller;
	private final ServerSocket listener;
	private final Gson gson;

	public LoLClientListener(StartupClientController controller)
			throws IOException {
		this.controller = controller;
		this.gson = new Gson();
		this.listener = new ServerSocket(STARTUP_CLIENT_PORT);
	}

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						Socket socket = listener.accept();
						try {
							System.out.println("accepted "
									+ socket.getInetAddress());
							String dtoJson = readDTOFromClient(socket
									.getInputStream());
							System.out.println("-------JSON-------");
							System.out.println(dtoJson);
							JsonObject fromJson = gson.fromJson(dtoJson,new TypeToken<JsonObject>() {
							}.getType());
							String ele = fromJson.get("ObjectType").getAsString();
							if(ele.equals("EndOfGameStats")){
								EndOfGameDTO endOfGameDTO = gson.fromJson(fromJson,
									new TypeToken<EndOfGameDTO>() {
									}.getType());
								controller.handleEndOfGameDTO(endOfGameDTO);
							}else if(ele.equals("GameDTO")){
								LoLGameDTO gameDTO = gson.fromJson(fromJson,
									new TypeToken<LoLGameDTO>() {
									}.getType());
								controller.handleLoLGameDTO(gameDTO);
							}else{
								throw new UnsupportedOperationException("unsupported ObjectType");
							}
							
							System.out.println("-------JSON_END-------");
						} finally {
							socket.close();
							System.out.println("closed ");
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						listener.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

	private String readDTOFromClient(InputStream in) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buffer[] = new byte[DEFAULT_BLOCK_SIZE];
		for (int s; (s = in.read(buffer)) != -1;) {
			baos.write(buffer, 0, s);
		}
		byte result[] = baos.toByteArray();
		return new String(result, "UTF-8");
	}

}
package org.qbix.pm.client;

import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		Playmore client = new Playmore();
		try {
			client.start();
		} catch (IOException e) {
			// TODO show a dialog or smth
			System.out.println("unable to start client");
		}
	}

}

package wordHunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SocketServer {
	int port = 3002;
	public SocketServer() {
	}
	private void start(int port) {
		this.port = port;
		System.out.println("Starting game...");
		try (ServerSocket serverSocket = new ServerSocket(port);
				Socket client = serverSocket.accept();
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));) {
			
			System.out.println("Client connected");
			String fromClient = "";
			String toClient = "";
			while ((fromClient = in.readLine()) != null) {
				if ("Exit game".equalsIgnoreCase(fromClient) || ("Quit".equalsIgnoreCase(fromClient))){
					System.out.println("Game shutting down");
					break;
				}
				/*else {
					System.out.println("Ending game");
				}*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("Closing Connection");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] arg) {
		System.out.println("Starting Server. Waiting for user to connect to game.");
		SocketServer server = new SocketServer();
		server.start(3002);
		System.out.println("Server Stopped");
	}
}
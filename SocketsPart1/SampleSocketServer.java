package com.example.sockets;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleSocketServer {
	//creating public function on this class
	//it will take a integer and will name integer port
	
	public void start(int port) {
		System.out.println("Waiting for client connection...");
		try (
		//creating new server socket which will register it on the port we created
				ServerSocket serverSocket = new ServerSocket(port);
				Socket clientSocket = serverSocket.accept();
				
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				){
			System.out.println("Client Connected, waiting for message");
			String fromClient = "", toClient = "";
			while((fromClient = in.readLine()) != null) {
				System.out.println("Message from client: " + fromClient);
				if("kill server".equalsIgnoreCase(fromClient)) {
					System.out.println("Client killed server process");
					break;
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting server");
		SampleSocketServer server = new SampleSocketServer();
		server.start(3001);
		System.out.println("Server stopped");
		
	}

}

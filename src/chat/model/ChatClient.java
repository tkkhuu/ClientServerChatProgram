package chat.model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import chat.gui.ChatApplication;
import chat.gui.WhisperChatView;


/**
 * A class that represents a client
 * @author Tri
 *
 */
public class ChatClient implements Runnable {
	/** The user name of the client.*/
	String name;
	
	/** The list that stores all the available clients on the server.*/
	static DefaultListModel<String> clientListModel = new DefaultListModel<String>();
	
	/** An array of string to stores all the messages of the chat room.*/
	static ArrayList<String> messages = new ArrayList<String>();
	
	/** The input stream, used to read messages sent by the server.*/
	BufferedReader input;
	
	/** The output stream, used to send messages to the server.*/
	PrintWriter output;
	
	/** The IP address of the server.*/
	String ipAddress = null;
	
	/** Check whether it has connected to the server.*/
	boolean isConnected;
	
	/** The highest class that stores all the GUIs.*/
	static ChatApplication chatapp;

	/**
	 * An executable method that runs the functionalities of the client.
	 */
	@Override
	public void run() {

		try {
			// Create a socket to connect to the server with the IP address typed in by the user.
			Socket socket = new Socket(ipAddress, 9001);
			
			// Initiate the input, output streams
			input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
			
			// Send a message to the server to request connection.
			sendOutput("NEW_USER" + name);
			String welcomeline = input.readLine();
			
			// Prompts the user if the name has been chosen
			while (welcomeline.startsWith("CLIENT_ALREADY_EXIST")) {
				chatapp.getWelcomeWindowView()
						.getStatus()
						.setText(
								"This name is already chosen, please pick another name");
				sendOutput("NEW_USER" + name);
				welcomeline = input.readLine();
			}
			
			// Once connected display the Main chat window
			isConnected = true;
			clientListModel.addElement(name);
			chatapp.getMainChatWindowView().getClientDisplayList()
					.setModel(clientListModel);
			Point p = chatapp.getWelcomeWindowView().getLocation();
			chatapp.getWelcomeWindowView().setVisible(false);
			chatapp.getMainChatWindowView().setTitle(name);
			chatapp.getMainChatWindowView().setLocation(p);
			chatapp.getMainChatWindowView().setVisible(true);
		} catch (Exception e) {
			// If any exceptions are encountered, prompts in a pop up window and request the user to restart.
			isConnected = false;
			System.out.println("Error connecting");
			String error = e.toString();
			chatapp.getPopUpWindow().getErrorMessage().setText(error);
			chatapp.getPopUpWindow().setLocationRelativeTo(null);
			chatapp.getPopUpWindow().setVisible(true);
		}
		// ===============================================================================
		// ===============================================================================
		// After everything is set up, run the main loop
		// The main loop, runs if the client is still connected.
		while (isConnected) {
			try {
				// Keep reading inputs
				String line = input.readLine();
				// ===============================================================================
				// Return if nothing is read
				if (line == null) {
					return;
				} 
				// ===============================================================================
				// If UPDATE_CLIENT_LIST is received, update the list of clients for both the data and GUI.
				else if (line.startsWith("UPDATE_CLIENT_LIST")) {
					if (!clientListModel.contains(line.substring(18))) {
						clientListModel.addElement(line.substring(18));
					}
				} 
				// ===============================================================================
				// If CLIENT_ALREADY_EXIST is received, prompt the user to pick a different name
				else if (line.startsWith("CLIENT_ALREADY_EXIST")) {
					chatapp.getWelcomeWindowView()
							.getStatus()
							.setText(
									"This name is already chosen, pick another name");
				} 
				// ===============================================================================
				// If WELCOME is received, connect the user to the server, update the list of clients
				else if (line.startsWith("WELCOME")) {
					clientListModel.addElement(line.substring(7));
					chatapp.getMainChatWindowView().getClientDisplayList()
							.setModel(clientListModel);
					Point p = chatapp.getWelcomeWindowView().getLocation();
					chatapp.getWelcomeWindowView().setVisible(false);
					name = line.substring(7);
					chatapp.getMainChatWindowView().setTitle(name);
					chatapp.getMainChatWindowView().setLocation(p);
					chatapp.getMainChatWindowView().setVisible(true);
				}
				// ===============================================================================
				// If WHISPER is received, display the whisper windows and show all the messages.
				else if (line.startsWith("WHISPER")) {
					String sender = line.split(" ")[2];
					Point p = chatapp.getMainChatWindowView().getLocation();
					if (!chatapp.getWhisperChatTable().containsKey(sender)) {
						chatapp.getWhisperChatTable().put(sender,
								new WhisperChatView(this, chatapp, sender));
						chatapp.getWhisperChatTable().get(sender).setLocation(p);
					}
					chatapp.getWhisperChatTable().get(sender).setVisible(true);
					chatapp.getWhisperChatTable()
							.get(sender)
							.getWhisperArea()
							.append(sender + ": " + line.split(" ", 6)[5]
									+ "\n");

				} 
				// ===============================================================================
				// If BROADCAST is received, append the message to the message are in the GUI and add it to the list of messages.
				else if (line.startsWith("BROADCAST")) {
					chatapp.getMainChatWindowView().getBroadcastTextArea()
							.append(line.substring(9) + "\n");
				} 
				// ===============================================================================
				// If LOGOUT is received, remove any streams connecting with the leaving client and update the list of clients.
				else if (line.startsWith("LOGOUT")) {
					if (chatapp.getWhisperChatTable().get(line.substring(6)) != null) {
						chatapp.getWhisperChatTable().get(line.substring(6))
								.getWhisperArea()
								.append(line.substring(6) + " has logged out");
					}
					clientListModel.removeElement(line.substring(6));
					chatapp.getWhisperChatTable().remove(line.substring(6));
				}
			} catch (IOException e) {
				// If exceptions are encountered, print it
				System.out.println(e.toString());
			}

		}

	}

	/** 
	 * Function to send messages to the server through the output stream
	 * @param message Message to be sent.
	 */
	public void sendOutput(String message) {
		output.println(message);
	}

	/**
	 * Getter for the list of clients
	 * @return the list of clients
	 */
	public DefaultListModel<String> getClientListModel() {
		return clientListModel;
	}

	/**
	 * Getter for the list of messages
	 * @return the list of messages
	 */
	public ArrayList<String> getMessages() {
		return messages;
	}

	/** Setter for the chat application GUI class
	 * @param chatapp Chatapp to be set to
	 */
	public void setChatApp(ChatApplication chatapp) {
		this.chatapp = chatapp;
	}

	/**
	 * Setter for setting the user name of the client
	 * @param name Name to be set to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the user name
	 * @return user name of the client
	 */
	public String getName() {
		return this.name;
	}

	/** 
	 * Getter for the IP address
	 * @return The IP address of the server
	 */
	public String getIPAddress() {
		return ipAddress;
	}

	/**
	 * Setter for the IP address
	 * @param ipAddress IP address to be set to.
	 */
	public void setIPAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}

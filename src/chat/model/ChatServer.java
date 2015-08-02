package chat.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * This class model the main server of the chat program.
 * 
 * @author Tri
 *
 */
public class ChatServer {

	/**
	 * A private class that listen to incoming clients.
	 * 
	 * @author Tri
	 *
	 */
	private static class ClientListener extends Thread {
		/** Name of the client. */
		String name;

		/** Socket of the client. */
		Socket socket;

		/** Input stream to receive message from the client. */
		BufferedReader input;

		/** Output stream to send message to the client. */
		PrintWriter output;

		/** Constructor for ClientListerner. */
		public ClientListener(Socket socket) {
			this.socket = socket;
		}

		/** Main method that gives the functionality of the server. */
		public void run() {
			try {
				// Create an input and an output stream for each client
				// connected.
				input = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream(), true);

				// The set up loop
				while (true) {
					// Read the name of the client from the input line.
					name = input.readLine();
					// ===============================================================================
					// Return if nothing is read
					if ((name == null) || (name.isEmpty())) {
						return;
					}
					// ===============================================================================
					// If name already exist, prompt the user.
					if (listOfNames.contains(name.substring(8))
							|| name.contains(" ")) {
						output.println("CLIENT_ALREADY_EXIST");
					}
					// ===============================================================================
					// If not, connect the client to the server by sending
					// WELCOME message
					else if (name.startsWith("NEW_USER")) {
						output.println("WELCOME" + name.substring(8));
					}
					// ===============================================================================
					// Update the list of clients, and whisper tables
					synchronized (listOfNames) {
						if (!listOfNames.contains(name.substring(8))) {
							listOfNames.add(name.substring(8));
							whisperTable.put(name.substring(8), output);
							break;
						}

					}
				}
				// Add the output stream used for this client to a the message
				// broadcast list.
				messageBroadcast.add(output);
				// The main loop
				// ===============================================================================
				// ===============================================================================
				while (true) {
					// Keep sending updates about the list of clients.
					for (PrintWriter writer : messageBroadcast) {
						for (String name : listOfNames) {
							writer.println("UPDATE_CLIENT_LIST" + name);
						}
					}

					// Read from the input stream
					String readMessage = input.readLine();
					// If nothing is read, return.
					if (readMessage == null) {
						return;
					}
					// ===============================================================================
					// If BROADCAST is read, process the message and send to all clients
					else if (readMessage.startsWith("BROADCAST")) {
						for (PrintWriter writer : messageBroadcast) {
							writer.println("BROADCAST" + name.substring(8)
									+ ": " + readMessage.substring(9));
						}
					} 
					// ===============================================================================
					// If WHISPER is read, process message and send to the receiver.
					else if (readMessage.startsWith("WHISPER")) {
						String receiver = readMessage.split(" ", 4)[1];
						if (whisperTable.containsKey(receiver)) {
							whisperTable.get(receiver).println(
									"WHISPER from: " + name.substring(8)
											+ " to " + receiver + ": "
											+ readMessage.split(" ", 3)[2]);
						}
					} 
					// ===============================================================================
					// If LOGOUT is read, remove the streams of the leaving client and notify the other clients
					else if (readMessage.startsWith("LOGOUT")) {
						listOfNames.remove(readMessage.substring(6));
						messageBroadcast.remove(output);
						whisperTable.remove(readMessage.substring(6));
						for (PrintWriter writer : messageBroadcast) {
							writer.println(readMessage);
						}
					}

				}
			} 
			// If any exceptions are encoutered, print it
			catch (IOException e) {
				System.out.println(e);
			} 
			// Close the socket of the client.
			finally {
				if (name != null) {
					listOfNames.remove(name);
				}
				if (output != null) {
					messageBroadcast.remove(output);
				}
				try {
					socket.close();
				} catch (IOException e) {
					System.out.print(e.toString());
				}
			}
		}
	}

	/** The port that the server listens on. */
	private static final int PORT = 9001;

	/** The set of all names of clients. */
	static HashSet<String> listOfNames = new HashSet<String>();

	/** List of client broadcast message. */
	static HashSet<PrintWriter> messageBroadcast = new HashSet<PrintWriter>();

	/** Hash table to keep track of pairs of whispering clients.*/
	static Hashtable<String, PrintWriter> whisperTable = new Hashtable<String, PrintWriter>();

	/** The main function to execute the server.*/
	public static void main(String[] args) throws Exception {
		System.out.println("The chat server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while (true) {
				new ClientListener(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}
}

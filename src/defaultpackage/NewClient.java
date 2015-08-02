package defaultpackage;

import chat.gui.ChatApplication;
import chat.model.ChatClient;
/**
 * This class is an executable used to create a new client
 * @author Tri
 *
 */
public class NewClient {

	/** The highest GUI class that contains all the GUI.*/
	private static ChatApplication chatapp;

	/** The main function.*/
	public static void main(String[] args) throws Exception {
		// Create a new chat client
		ChatClient client = new ChatClient();

		// Create the chatapp for the client
		chatapp = new ChatApplication(client);
		// Set the chatapp for the client
		client.setChatApp(chatapp);
		
		// Open the welcome window GUI
		chatapp.getWelcomeWindowView().setLocationRelativeTo(null);
		chatapp.getWelcomeWindowView().setVisible(true);
		
		// Prompt to pick user name
		while (!chatapp.getIsGoodToRun()) {
			System.out.println("Pick user name and IP");
		}
		System.out.println("Connecting to server ...");
		client.run();

	}
}

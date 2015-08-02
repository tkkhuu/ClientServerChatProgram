package chat.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.gui.ChatApplication;
import chat.model.ChatClient;

/**
 * Controller to request update list of clients
 * @author Tri
 *
 */
public class RefreshAvailableClient implements ActionListener {
	ChatClient client;
	ChatApplication chatapp;

	public RefreshAvailableClient(ChatClient client, ChatApplication chatapp) {
		this.client = client;
		this.chatapp = chatapp;
	}
	/**
	 * When the refresh button is clicked, send a request to update list of client to the server.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		client.sendOutput("UPDATE_USER_LIST");
		

	}
}

package chat.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.gui.ChatApplication;
import chat.model.ChatClient;
/**
 * The controller for loging out the client
 * @author Tri
 *
 */
public class ClientLogOut implements ActionListener{
	ChatClient client;
	ChatApplication chatapp;
	
	public ClientLogOut(ChatClient client, ChatApplication chatapp) {
		this.client = client;
		this.chatapp = chatapp;
	}
	/**
	 * When the logout button is clicked, send a request to logout to the server
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		client.sendOutput("LOGOUT"+client.getName());
		chatapp.close();
	}

}

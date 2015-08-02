package chat.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.gui.ChatApplication;
import chat.model.ChatClient;

/**
 * Controller for connecting the client to the server
 * @author Tri
 *
 */
public class ConnectClientToServer implements ActionListener {
	ChatClient client;
	ChatApplication chatapp;

	public ConnectClientToServer(ChatClient client, ChatApplication chatapp) {
		this.client = client;
		this.chatapp = chatapp;
	}
	/**
	 * When the user click the connect button, request to connect to the server
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = chatapp.getWelcomeWindowView().getUsernameTextField()
				.getText();
		
		String ipAddr = chatapp.getWelcomeWindowView()
				.getIPAddressTextField().getText();
		if ((!(username.isEmpty()))&&(username!=null)&&(ipAddr != null)&&(!ipAddr.isEmpty())) {
			username = username.replaceAll("\\s", "");
			client.setIPAddress(ipAddr);
			client.setName(username);
			chatapp.setIsGoodToRun(true);
			
		}

	}

}

package chat.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.gui.ChatApplication;
import chat.model.ChatClient;
/**
 * Controller to send messages to chatroom
 * @author Tri
 *
 */
public class SendMessageToServer implements ActionListener {
	ChatClient client;
	ChatApplication chatapp;

	public SendMessageToServer(ChatClient client, ChatApplication chatapp) {
		this.client = client;
		this.chatapp = chatapp;
	}

	/**
	 * When the send message from the main chat window is clicked, send a BROADCAST message to the server.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String messageToSend = chatapp.getMainChatWindowView()
				.getMessageTextField().getText();
		if (messageToSend != null) {
			client.sendOutput("BROADCAST" + messageToSend);
			chatapp.getMainChatWindowView().getMessageTextField().setText("");
		}

	}

}

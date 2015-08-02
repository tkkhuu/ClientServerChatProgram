package chat.control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.gui.ChatApplication;
import chat.gui.WhisperChatView;
import chat.model.ChatClient;
/**
 * Controller to connect to whisper to another client
 * @author Tri
 *
 */
public class ConnectWhisperWindow implements ActionListener {
	ChatClient client;
	ChatApplication chatapp;

	public ConnectWhisperWindow(ChatClient client, ChatApplication chatapp) {
		this.client = client;
		this.chatapp = chatapp;
	}

	/**
	 * When the whisper button is clicked, send a WHISPER request to the server
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String title = chatapp.getMainChatWindowView().getClientDisplayList()
				.getSelectedValue();
		if (title != null) {
			Point p = chatapp.getMainChatWindowView().getLocation();
			if (!chatapp.getWhisperChatTable().contains(title)) {
				chatapp.getWhisperChatTable().put(title,
						new WhisperChatView(client, chatapp));
			}
			chatapp.getWhisperChatTable().get(title).setLocation(p);
			chatapp.getWhisperChatTable().get(title).setVisible(true);
			chatapp.getWhisperChatTable().get(title).setName(title);
		}

	}
}

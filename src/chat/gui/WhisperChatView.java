package chat.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.model.ChatClient;
/**
 * The GUI for whisper window view.
 * @author Tri
 *
 */
@SuppressWarnings("serial")
public class WhisperChatView extends JFrame {
	String name;
	ChatApplication chatapp;
	ChatClient client;
	JTextArea broadCastArea = new JTextArea();
	JTextField textField;
	JButton sendButton;

	public WhisperChatView(ChatClient client, ChatApplication chatapp) {
		this.chatapp = chatapp;
		this.client = client;
		getContentPane().setLayout(null);
		setSize(500, 400);
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 488, 245);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(broadCastArea);
		broadCastArea.setEditable(false);
		scrollPane.setBounds(0, 0, 488, 278);
		panel.add(scrollPane);

		textField = new JTextField();
		textField.setBounds(6, 294, 389, 78);
		getContentPane().add(textField);
		textField.setColumns(10);

		sendButton = new JButton("Send");
		sendButton.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		sendButton.setBounds(400, 294, 94, 78);
		getContentPane().add(sendButton);
		
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String messageToSend = textField.getText();
				if (messageToSend != null) {
					client.sendOutput("WHISPER " + name+" " + messageToSend);
					broadCastArea.append(client.getClientListModel().getElementAt(0)+": "+messageToSend+"\n");
					textField.setText("");
				}
			}
		});
		
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String messageToSend = textField.getText();
				if (messageToSend != null) {
					client.sendOutput("WHISPER " + name+" " + messageToSend);
					broadCastArea.append(client.getClientListModel().getElementAt(0)+": "+messageToSend+"\n");
					textField.setText("");
				}
			}
		});

		
	}

	
	public WhisperChatView(ChatClient client, ChatApplication chatapp, String name) {
		this.name = name;
		this.chatapp = chatapp;
		this.client = client;
		getContentPane().setLayout(null);
		setSize(500, 400);
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 488, 245);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(broadCastArea);
		broadCastArea.setEditable(false);
		scrollPane.setBounds(0, 0, 488, 278);
		panel.add(scrollPane);

		textField = new JTextField();
		textField.setBounds(6, 294, 389, 78);
		getContentPane().add(textField);
		textField.setColumns(10);

		sendButton = new JButton("Send");
		sendButton.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		sendButton.setBounds(400, 294, 94, 78);
		getContentPane().add(sendButton);
		
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String messageToSend = textField.getText();
				if (messageToSend != null) {
					client.sendOutput("WHISPER " + name+" " + messageToSend);
					broadCastArea.append(client.getClientListModel().getElementAt(0)+": "+messageToSend+"\n");
					textField.setText("");
				}
			}
		});
		
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String messageToSend = textField.getText();
				if (messageToSend != null) {
					client.sendOutput("WHISPER " + name+" " + messageToSend);
					broadCastArea.append(client.getClientListModel().getElementAt(0)+": "+messageToSend+"\n");
					textField.setText("");
				}
			}
		});

	}
	
	
	public JTextField getTextField() {
		return textField;
	}

	public JTextArea getWhisperArea() {
		return broadCastArea;
	}
	public void setName(String name){
		this.name = name;
	}
}

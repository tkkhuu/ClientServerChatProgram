package chat.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import chat.control.ClientLogOut;
import chat.control.ConnectWhisperWindow;
import chat.control.RefreshAvailableClient;
import chat.control.SendMessageToServer;
import chat.model.ChatClient;

import javax.swing.JScrollPane;
/**
 * The GUI for main chat window view
 * @author Tri
 *
 */
@SuppressWarnings("serial")
public class MainChatWindowView extends JFrame {
	ChatApplication chatapp;
	ChatClient client;
	JTextField messageTextField;
	JTextArea broadcastTextArea = new JTextArea(8, 40);
	JButton sendMessageButton;
	JButton whisperButton;
	JButton logoutButton;
	JButton refreshButton;
	JList<String> clientDisplayList = new JList<String>();
	JPanel clientDisplayPanel;

	public MainChatWindowView(ChatClient client, ChatApplication chatapp) {

		this.client = client;
		this.chatapp = chatapp;

		setSize(600, 600);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		broadcastTextArea.setEditable(false);
		JScrollPane scrollPane_1 = new JScrollPane(broadcastTextArea);
		panel.add(scrollPane_1);
		
				

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		messageTextField = new JTextField();
		messageTextField.setBounds(6, 6, 207, 74);
		panel_1.add(messageTextField);
		messageTextField.setColumns(10);

		sendMessageButton = new JButton("Send");
		sendMessageButton.setBounds(212, 6, 82, 74);
		panel_1.add(sendMessageButton);

		clientDisplayPanel = new JPanel();
		clientDisplayPanel.setBounds(6, 92, 288, 399);
		panel_1.add(clientDisplayPanel);
		clientDisplayPanel.setLayout(null);

		clientDisplayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clientDisplayList.setVisibleRowCount(10);
		clientDisplayList.setBounds(6, 557, 288, -466);

		JScrollPane scrollPane = new JScrollPane(clientDisplayList);
		scrollPane.setBounds(0, 0, 288, 480);
		clientDisplayPanel.add(scrollPane);

		whisperButton = new JButton("Whisper");
		whisperButton.setBounds(149, 491, 145, 35);
		panel_1.add(whisperButton);

		refreshButton = new JButton("Refresh");
		refreshButton.setBounds(6, 491, 145, 35);
		panel_1.add(refreshButton);
		
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(86, 537, 136, 35);
		panel_1.add(logoutButton);

		sendMessageButton.addActionListener(new SendMessageToServer(client,
				chatapp));
		
		messageTextField.addActionListener(new SendMessageToServer(client,
				chatapp));
		
		refreshButton.addActionListener(new RefreshAvailableClient(client,
				chatapp));
		whisperButton.addActionListener(new ConnectWhisperWindow(client, chatapp));
		
		logoutButton.addActionListener(new ClientLogOut(client, chatapp));
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				if (client.getName() != null) {
					client.sendOutput("LOGOUT" + client.getName());
				}
				System.exit(0);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	public JTextField getMessageTextField() {
		return messageTextField;
	}

	public JTextArea getBroadcastTextArea() {
		return broadcastTextArea;
	}

	public JList<String> getClientDisplayList() {
		return clientDisplayList;
	}

	public JPanel getClientDisplayPanel() {
		return clientDisplayPanel;
	}
	
	
}

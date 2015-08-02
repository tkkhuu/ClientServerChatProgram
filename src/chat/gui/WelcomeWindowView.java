package chat.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chat.control.ConnectClientToServer;
import chat.model.ChatClient;
/**
 * The GUI for welcome window view
 * @author Tri
 *
 */
@SuppressWarnings("serial")
public class WelcomeWindowView extends JFrame {
	ChatClient client;
	ChatApplication chatapp;
	JButton connectButton;
	JTextField usernameTextField;
	JLabel status;
	JTextField ipAddressTextField;

	public WelcomeWindowView(ChatClient client, ChatApplication chatapp) {

		this.client = client;
		this.chatapp = chatapp;

		setSize(500, 350);
		setResizable(false);

		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		JLabel lblWelcomeToChat = new JLabel("Welcome To Chat Application");
		lblWelcomeToChat.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblWelcomeToChat);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		connectButton = new JButton("Connect");
		connectButton.setBounds(189, 61, 134, 29);
		panel_1.add(connectButton);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUsername.setBounds(91, 11, 86, 16);
		panel_1.add(lblUsername);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(189, 6, 134, 28);
		panel_1.add(usernameTextField);
		usernameTextField.setColumns(10);

		ipAddressTextField = new JTextField();
		ipAddressTextField.setBounds(189, 34, 134, 28);
		ipAddressTextField.setText(null);
		panel_1.add(ipAddressTextField);
		ipAddressTextField.setColumns(10);

		JLabel lblIpAddress = new JLabel("IP Address:");
		lblIpAddress.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblIpAddress.setBounds(91, 40, 86, 16);
		panel_1.add(lblIpAddress);
		usernameTextField.addActionListener(new ConnectClientToServer(client,
				chatapp));
		connectButton.addActionListener(new ConnectClientToServer(client,
				chatapp));
		ipAddressTextField.addActionListener(new ConnectClientToServer(client,
				chatapp));
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);

		status = new JLabel(" ");
		panel_2.add(status);

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

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JLabel getStatus() {
		return status;
	}

	public JTextField getIPAddressTextField() {
		return ipAddressTextField;
	}

}

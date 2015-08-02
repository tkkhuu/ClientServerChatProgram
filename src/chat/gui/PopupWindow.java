package chat.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import chat.model.ChatClient;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
/**
 * The GUI for pop up window view
 * @author Tri
 *
 */
@SuppressWarnings("serial")
public class PopupWindow extends JFrame {
	ChatApplication chatapp;
	ChatClient client;
	JLabel errorMessage;

	public PopupWindow(ChatClient client, ChatApplication chatapp) {
		this.client = client;
		this.chatapp = chatapp;
		getContentPane().setLayout(null);
		setSize(500, 300);
		errorMessage = new JLabel("New label");
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setVerticalAlignment(SwingConstants.TOP);
		errorMessage.setBounds(6, 6, 488, 181);
		getContentPane().add(errorMessage);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(191, 243, 117, 29);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chatapp.close();
				
			}
		});
		
		JLabel lblPleaseRestartAnd = new JLabel("Please restart and try again");
		lblPleaseRestartAnd.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPleaseRestartAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseRestartAnd.setBounds(6, 215, 488, 16);
		getContentPane().add(lblPleaseRestartAnd);
		
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

	public JLabel getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(JLabel errorLabel){
		this.errorMessage = errorLabel;
	}
}

package chat.gui;

import java.util.Hashtable;
import java.util.Set;

import chat.model.ChatClient;

/**
 * This is the highest GUI class that contains all other GUIs.
 * @author Tri
 *
 */
public class ChatApplication {

	/** The client that this GUI represents.*/
	ChatClient client;

	/** The main chat window GUI.*/
	MainChatWindowView mcwv;

	/** The Welcome window GUI.*/
	WelcomeWindowView wwv;
	
	/** The popup window GUI.*/
	PopupWindow pw;
	
	/** A Boolean to see if the application is ready to launch.*/
	boolean isGoodToRun;
	
	/** A Hash Table to keep track of whispering partners.*/
	Hashtable<String, WhisperChatView> whisperTable = new Hashtable<String, WhisperChatView>();

	/** Constructor for ChatApplication.*/
	public ChatApplication(ChatClient client) {
		isGoodToRun = false;
		this.client = client;
		this.mcwv = new MainChatWindowView(client, this);
		this.wwv = new WelcomeWindowView(client, this);
		this.pw = new PopupWindow(client,this);
	}

	/**
	 * Getter for ChatClient
	 * @return the client
	 */
	public ChatClient getClient() {
		return client;
	}

	/**
	 * Getter for the main chat window
	 * @return the mcwv
	 */
	public MainChatWindowView getMainChatWindowView() {
		return mcwv;
	}
	/**
	 * Getter for the welcome window
	 * @return wwv
	 */
	public WelcomeWindowView getWelcomeWindowView() {
		return wwv;
	}
	/** Getter for the whisper table
	 * @return the whisper table
	 */
	public Hashtable<String, WhisperChatView> getWhisperChatTable(){
		return whisperTable;
	}
	/**
	 * getter for isgoodtorun
	 * @return isGoodToRun
	 */
	public boolean getIsGoodToRun(){
		
		return isGoodToRun;
	}
	/**
	 * setter for is good to run
	 * @param b Boolean to set to
	 */
	public void setIsGoodToRun(boolean b){
		this.isGoodToRun = b;
	}
	/**
	 * Close all the windows.
	 */
	public void close(){
		System.exit(0);
		this.mcwv.dispose();
		this.wwv.dispose();
		pw.dispose();
		Set<String> key = whisperTable.keySet();
		for(String whisperkey : key){
			whisperTable.get(whisperkey).dispose();
			whisperTable.remove(whisperkey);
		}
	
	}
	/**
	 * getter for pop up window
	 * @return pw
	 */
	public PopupWindow getPopUpWindow(){
		return pw;
	}
}

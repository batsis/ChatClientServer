package gui_package;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

import client.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;




public class GUI {

	private JFrame frame;
	
	private String user;
	private String output, s = "";
	
	private Client client;
	
	private JTextArea chatOutput;
	private JTextArea userView;
	
	JTextPane userInput;
	
	
	/**
	 * Create the application.
	 * @param username 
	 */
	public GUI() {
		
		initialize(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(KeyEvent e) {
		frame = new JFrame("Chatroom");
		frame.setBounds(100, 100, 600, 550);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JScrollPane scroll_chatOutput = new JScrollPane();
		scroll_chatOutput.setBounds(10, 11, 369, 241);
		frame.getContentPane().add(scroll_chatOutput);
		// Fönster för chaten
		chatOutput = new JTextArea();
		chatOutput.setEditable(false);
//		chatOutput.setText(user + " has joined the room!" + System.lineSeparator());
		scroll_chatOutput.setViewportView(chatOutput);
		DefaultCaret caret = (DefaultCaret)chatOutput.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		
		JScrollPane scroll_userView = new JScrollPane();
		scroll_userView.setBounds(408, 11, 166, 362);
		frame.getContentPane().add(scroll_userView);
		
		JTextArea userView = new JTextArea();
		userView.setEditable(false);
		scroll_userView.setViewportView(userView);
		// Fönster för din text
		final JTextPane userInput = new JTextPane();
		userInput.setBounds(10, 263, 369, 110);
		frame.getContentPane().add(userInput);
		
		//Skicka meddelande
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userInput.getText().equals("")){
					chatOutput.append("Writh something pls! // Bob\n");
				}else {
				client.send(userInput.getText());
				userInput.setText("");
				}
			}
		});
		btnSend.setBounds(10, 403, 89, 23);
		frame.getContentPane().add(btnSend);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
		
			}
		});
		btnLogout.setBounds(485, 477, 89, 23);
		frame.getContentPane().add(btnLogout);

		frame.repaint();
	}
	
	public void addToChatOutput(String input) {
		if (input != null){
			chatOutput.append(input + System.lineSeparator());	
		}
		
	}

	public void setClient(Client client) {
		this.client = client;
		
	}
	
	private int close(){
		client.send("/quit");
		System.exit(1);
		return 0;
	}
	

}

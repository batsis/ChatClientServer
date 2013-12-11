import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;


public class GUI {

	private JFrame frame;
	
	private String bob = "Bob";
	private String user;
	private String output, s = "";

	
	
	MessSendRecive Mess;
	
	Smile smile;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI window = new GUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	

	/**
	 * Create the application.
	 * @param username 
	 */
	public GUI(String username) {
		
		user = username;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Chatroom");
		frame.setBounds(100, 100, 600, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JScrollPane scroll_chatOutput = new JScrollPane();
		scroll_chatOutput.setBounds(10, 11, 369, 241);
		frame.getContentPane().add(scroll_chatOutput);
		// Fönster för chaten
		final JTextPane chatOutput = new JTextPane();
		chatOutput.setEditable(false);
		chatOutput.setText(user + " has joined the room!" + System.lineSeparator());
		scroll_chatOutput.setViewportView(chatOutput);
		
		
		JScrollPane scroll_userView = new JScrollPane();
		scroll_userView.setBounds(408, 11, 166, 362);
		frame.getContentPane().add(scroll_userView);
		
		JTextPane userView = new JTextPane();
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
				output = userInput.getText();
				s += user + ": " + userInput.getText() + System.lineSeparator();
				

				
				chatOutput.setText(s);
				userInput.setText("");
			}
		});
		btnSend.setBounds(10, 403, 89, 23);
		frame.getContentPane().add(btnSend);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserNameGUI logout = new UserNameGUI();
				frame.setVisible(false);
			}
		});
		btnLogout.setBounds(485, 477, 89, 23);
		frame.getContentPane().add(btnLogout);
		
	}

}

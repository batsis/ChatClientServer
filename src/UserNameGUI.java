import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class UserNameGUI {
	
	private String username;
	
	private String user;

	private JFrame frame;
	private JTextField textField;
	
	File list = new File("username.txt");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserNameGUI window = new UserNameGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserNameGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Användarnamn");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		//Välj ditt användarnamn
		JLabel lblVljDittSexiga = new JLabel("V\u00E4lj ditt sexiga anv\u00E4ndarnamn!");
		lblVljDittSexiga.setBounds(1, 45, 171, 14);
		frame.getContentPane().add(lblVljDittSexiga);
		
		textField = new JTextField();
		textField.setBounds(13, 84, 147, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//En box för att kom ihåg användarnamnet
		
		final JCheckBox chckbxKomIhgMig = new JCheckBox("Kom ih\u00E5g mig");
		chckbxKomIhgMig.setBounds(6, 231, 133, 23);
		frame.getContentPane().add(chckbxKomIhgMig);
		
		//Anslut till chaten 
		JButton btnMagicsToHapen = new JButton("magicz to happen");
		btnMagicsToHapen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = textField.getText();
				
				if(chckbxKomIhgMig.isSelected()) {
					
				}
				
				GUI gui = new GUI(username);
				frame.setVisible(false);
			}
		});
		btnMagicsToHapen.setBounds(6, 131, 161, 23);
		frame.getContentPane().add(btnMagicsToHapen);
		
		//Avsluta programmet
		JButton btnEggshit = new JButton("Eggshit");
		btnEggshit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnEggshit.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnEggshit);
	}
	
	public String getUsername(){
		return username;
	}
}

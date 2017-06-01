import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class SocialDummy {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SocialDummy window = new SocialDummy();
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
	public SocialDummy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSocialDummyApplication = new JLabel("Social Application");
		lblSocialDummyApplication.setForeground(Color.RED);
		lblSocialDummyApplication.setFont(new Font("Viner Hand ITC", Font.BOLD, 11));
		lblSocialDummyApplication.setBounds(172, 42, 136, 14);
		frame.getContentPane().add(lblSocialDummyApplication);
		
		JLabel lblUsername = new JLabel("User:");
		lblUsername.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 11));
		lblUsername.setBounds(32, 94, 52, 14);
		frame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(70, 91, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Pass:");
		lblPassword.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 11));
		lblPassword.setBounds(203, 94, 52, 14);
		frame.getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(239, 91, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnGo = new JButton("Go");
		btnGo.setForeground(Color.BLACK);
		btnGo.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		btnGo.setBounds(372, 90, 52, 23);
		frame.getContentPane().add(btnGo);
		
		btnGo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s","root","");
					Statement st  = con.createStatement();
					st.executeUpdate("insert into users values ('"+textField.getText()+"','"+textField_1.getText()+"')");
					JOptionPane.showMessageDialog(null, "Successfully Inserted", "Hello", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		 	
		});
	}
}

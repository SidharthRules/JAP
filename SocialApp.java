import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SocialApp{
	
	JFrame f;
	JButton login, register, search;
	JTextField user,pass,search_user;
	JLabel greet,username,password;
	
	SocialApp()
	{
		f = new JFrame();
		login = new JButton("Login");
		register =  new JButton("Register");
		search = new JButton("Search");
		greet = new JLabel("Welcome to the Social App");
		user = new JTextField();
		pass = new JTextField();
		search_user = new JTextField();
		buildLogin(f);
	}
	
	public void buildLogin(JFrame jf)
	{
		JLabel lname,lpass;
		JPanel p1 = new JPanel(new BoxLayout(jf, BoxLayout.Y_AXIS));
		JPanel p2 = new JPanel(new GridLayout(3,2));
		lname = new JLabel("Username: ");
		lpass = new JLabel("Password: ");
		p2.add(lname);
		p2.add(user);
		p2.add(lpass);
		p2.add(pass);
		p1.add(p2);
		jf.add(p1);
		jf.setSize(800,600);
		jf.setLocation(200, 100);
		jf.setVisible(true);
	}
	public static void main(String as[])
	{
		new SocialApp();
	}
}

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentGrade extends JFrame implements ActionListener{

	JLabel name, roll, dept;
	JTextField tname,troll,tdept;
	JButton b1,b2;
	
	StudentGrade()
	{
		name = new JLabel("Name: ");
		roll = new JLabel("Rollnumber: ");
		dept = new JLabel("Department: ");
		b1 = new JButton("Save !");
		b2 = new JButton("Show All Data !");
		tname = new JTextField();
		troll = new JTextField();
		tdept = new JTextField();
		
		setLayout(new GridLayout(4,2));
		add(name);
		add(tname);
		add(roll);
		add(troll);
		add(dept);
		add(tdept);
		add(b1);
		add(b2);
		setSize(300,200);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			{
				dispose();
			}
		});
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jap","root","");
					Statement st  = con.createStatement();
					st.executeUpdate("insert into student values ('"+tname.getText()+"',"+Integer.parseInt(troll.getText())+",'"+tdept.getText()+"')");
					String temp_name = tname.getText();
					String temp_roll = troll.getText();
					tname.setText(" ");
					troll.setText(" ");
					tdept.setText(" ");
					String ass_id = JOptionPane.showInputDialog(null, "Its Oki, Enter the ID off the Assignment ", "Assignemnt ID", JOptionPane.QUESTION_MESSAGE);
					if(ass_id.equals(""))
						JOptionPane.showMessageDialog(null, "Uh-oh!", "Error", JOptionPane.ERROR_MESSAGE);
					else
					{
						String ass_grade = JOptionPane.showInputDialog(null, "Fine, Now give the Grade for "+temp_name+" for assignment #"+ass_id, "Grade For Student", JOptionPane.QUESTION_MESSAGE);
						if(ass_grade.equals(""))
							JOptionPane.showMessageDialog(null, "Uh-oh!", "Error", JOptionPane.ERROR_MESSAGE);
						else
						{
							try {
								st.executeUpdate("insert into ass values ("+Integer.parseInt(ass_id)+","+Integer.parseInt(temp_roll)+",'"+ass_grade+"')");
								JOptionPane.showMessageDialog(null, "Sucess");
							}
							catch(Exception E){}
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// TODO Auto-generated method stub
				
			}
		});
	}
	public static void main(String asp[])
	{
		new StudentGrade();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

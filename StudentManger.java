import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class StudentManger extends JFrame implements ActionListener{
	
	JPanel center,nav;
	JLabel name, roll, dept;
	JTextField tname,troll,tdept;
	JButton b1,b2,b3,b4;
	JDialog jd;
	StudentManger()
	{
		center=new JPanel();
		nav = new JPanel();
		name = new JLabel("Name: ");
		roll = new JLabel("Rollnumber: ");
		dept = new JLabel("Department: ");
		b1 = new JButton("Add Student");
		b2 = new JButton("Add Assignment");
		b3 = new JButton("Update Record");
		b4 = new JButton("Show All");
		tname = new JTextField();
		troll = new JTextField();
		tdept = new JTextField();
		jd = new JDialog(this, "Showing Details", true);
		jd.setLayout(new FlowLayout());
		
		center.setLayout(new GridLayout(3,2));
		center.add(name);
		center.add(tname);
		center.add(roll);
		center.add(troll);
		center.add(dept);
		center.add(tdept);
		
		nav.setLayout(new FlowLayout());
		nav.add(b1);
		nav.add(b2);
		nav.add(b3);
		nav.add(b4);
		
		setLayout(new BorderLayout());
		setSize(500,180);
		setLocation(450, 280);
		setVisible(true);
		add(center,BorderLayout.CENTER);
		add(nav,BorderLayout.SOUTH);
		setTitle("Student Manager");
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			{
				dispose();
			}
		});
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
	}
	
	public static void main(String []as){
		new StudentManger();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jap","root","");
			Statement st = con.createStatement();
			if(e.getSource()==b1)
			{
				st.executeUpdate("insert into student values ('"+tname.getText()+"',"+Integer.parseInt(troll.getText())+",'"+tdept.getText()+"')");
				JOptionPane.showMessageDialog(null, "Successfully Added the Student Record !");
				tname.setText(" ");
				troll.setText(" ");
				tdept.setText(" ");
			}
			else if(e.getSource()==b2)
			{
				ResultSet rs = st.executeQuery("select name,roll from student");
				ArrayList<String> arr_name = new ArrayList<String>();
				ArrayList<String> arr_roll = new ArrayList<String>();
				while(rs.next())
				{
					arr_name.add(rs.getString(1));
					arr_roll.add(rs.getString(2));
				}
				rs.close();
				String id = JOptionPane.showInputDialog(null, "Enter the ID of the Assignment ", "Assignemnt ID", JOptionPane.QUESTION_MESSAGE);
				
				for(int i=0;i<arr_name.size();i++)
				{
					String grade = JOptionPane.showInputDialog(null, "Enter the Grade of "+arr_name.get(i)+", for the Assignment #"+id, "Enter Grade", JOptionPane.QUESTION_MESSAGE);
					st.executeUpdate("insert into ass values ("+Integer.parseInt(id)+","+Integer.parseInt(arr_roll.get(i))+",'"+grade+"')");
				}
				JOptionPane.showMessageDialog(null, "Successfully Added the Assignemnt Details !");
			}
			else if(e.getSource()==b3)
			{
				String id = JOptionPane.showInputDialog(null, "Enter the ID of the Assignment ", "Update Assignemnt", JOptionPane.QUESTION_MESSAGE);
				String roll = JOptionPane.showInputDialog(null, "Enter the Rollnumber of the Student ", "Update Assignemnt", JOptionPane.QUESTION_MESSAGE);
				ResultSet rs=st.executeQuery("select name from student where roll = '"+roll+"'");
				rs.next();
				String grade = JOptionPane.showInputDialog(null, "Enter the new grade of "+rs.getString(1), "Update Assignment", JOptionPane.QUESTION_MESSAGE);
				rs.close();
				st.executeUpdate("update ass set grade = '"+grade+"' where id ="+id+" and roll = "+roll);
				JOptionPane.showMessageDialog(null, "Successfully updated the Assignemnt Details !");
			}
			else if(e.getSource()==b4)
			{
				JTable shortcutKeysTable;
				
				ResultSet rs = st.executeQuery("select * from student join ass using(roll) ");
				ArrayList <ArrayList <String>> data= new ArrayList<ArrayList <String>>();
				System.out.println("f"+rs.getRow());
				while(rs.next())
				{
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(rs.getString(1));
					temp.add(rs.getString(2));
					temp.add(rs.getString(3));
					temp.add(rs.getString(4));
					temp.add(rs.getString(5));
					data.add(temp);
				}
				System.out.println("l"+rs.getRow());
				String [][]rowData = new String [data.size()][5];
				for (int i=0;i<data.size();i++)
				{
					rowData[i][0]=data.get(i).get(0);
					rowData[i][1]=data.get(i).get(1);
					rowData[i][2]=data.get(i).get(2);
					rowData[i][3]=data.get(i).get(3);
					rowData[i][4]=data.get(i).get(4);
				}
				Object columnNames[] = { "RollNumber", "Name", "Department", "Ass ID", "Grade" };
			    shortcutKeysTable = new JTable(rowData,columnNames);
			    JScrollPane scrollPane = new JScrollPane(shortcutKeysTable);
			    jd.add(scrollPane);
			    jd.setSize(500,300);
			    jd.setLocation(400,250);
			    jd.setVisible(true);
			    jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}
		
		
		// TODO Auto-generated method stub
	}
}

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PaintFx extends Frame implements ActionListener, MouseMotionListener, MouseListener{
	Color cg;
	MenuBar mb;
	int shape=0,size=20;
	Canvas cnv;
	//MenuItem 
	
	PaintFx()
	{
		mb = new MenuBar();
		cg=Color.black;
		Menu m1 = new Menu("Save");
		Menu m2 = new Menu("Color");
		Menu m3 = new Menu("Shape");
		Menu m4 = new Menu("Size");
		
		MenuItem red,green,blue,rect,oval,inc,dec,save;
		
		red = new MenuItem("Red");
		green = new MenuItem("Green");
		blue = new MenuItem("Blue");
		rect = new MenuItem("Rectangle");
		oval = new MenuItem("Circle");
		inc = new MenuItem("Increase");
		dec = new MenuItem("Decrease");
		save = new MenuItem("Save it !");
		
		m1.add(save);
		
		m2.add(red);
		m2.add(green);
		m2.add(blue);
		
		m3.add(rect);
		m3.add(oval);
		
		m4.add(inc);
		m4.add(dec);
		
		red.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cg=Color.red;
			}
		});
		
		green.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cg=Color.green;
			}
		});
		
		blue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cg=Color.blue;
			}
		});
		
		oval.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					shape=0;
			}
		});
		
		rect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					shape=1;
			}
		});
		
		inc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					size+=5;
			}
		});
		dec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					size-=5;
			}
		});
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try
		        {
		            BufferedImage image = new BufferedImage(cnv.getWidth(), cnv.getHeight(), BufferedImage.TYPE_INT_RGB);
		            Graphics2D g = (Graphics2D)image.getGraphics();
		            cnv.paint(g);
		            ImageIO.write(image,"jpeg", new File("C:/Users/SiD/Desktop/image2.jpeg"));
		        }
		        catch(Exception exception)
		        {
		            System.out.println(exception);
		        }
			}
		});
		
		
		
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		cnv = new Canvas();
		setMenuBar(mb);
		setSize(720,720);
		cnv.setSize(getWidth(),getHeight());
		add(cnv);
		setLayout(null);
		setVisible(true);
		cnv.addMouseListener(this);
		cnv.addMouseMotionListener(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			{
				dispose();
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Graphics2D g =(Graphics2D) cnv.getGraphics();
		g.setColor(cg);
		if(shape ==0)
			g.fillOval(e.getX(), e.getY(), size,size);
		else
			g.fillRect(e.getX(), e.getY(), size,size);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Graphics2D g =(Graphics2D) cnv.getGraphics();
		g.setColor(cg);
		if(shape ==0)
			g.fillOval(e.getX(), e.getY(), size,size);
		else
			g.fillRect(e.getX(), e.getY(), size,size);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String as[])
	{
		new PaintFx();
	}
}

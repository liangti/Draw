package Draw;
import java.net.*;

import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class Drawingboard extends JFrame implements Runnable{
	
//**********************************
public JButton rubber=new JButton("rubber"),pen=new JButton("pen");
public JMenuBar jm = new JMenuBar();
public int rp = -1; 
public final JToolBar jtb= new JToolBar();
public BufferedImage img = new BufferedImage(800,800,BufferedImage.TYPE_INT_BGR);
Graphics gs = img.getGraphics();
Graphics2D g = (Graphics2D)gs;
Drawcanvas dc = new Drawcanvas();
public Color c = Color.black;
public int x=-1,y=-1;
public M1 m1 = new M1();
public M2 m2 = new M2();
public byte[]b;
public JFileChooser jfc = new JFileChooser();
boolean judge = true;
Socket so;
BufferedOutputStream pr;
//*********************************************


public Drawingboard(){
	this.setBounds(0,0 ,800,600);
	this.add(jtb);
	this.setJMenuBar(jm);
	initialize();
	clear();
	this.getContentPane().add(dc);
	dc.setBounds(0, 0, 800, 600);
	dc.addMouseMotionListener(m1);
	dc.addMouseListener(m2);
	setjm();
	setcanvas();
	setjtoolbar();
	
	this.setLayout(null);
}

public void clear(){
	
	g.setColor(Color.WHITE);
	g.fillRect(0, 0, 800, 600);
	
}

public void setcanvas(){
	dc.setBounds(0, 0, 800, 600);
	dc.addMouseMotionListener(new M1());
	dc.addMouseListener(new M2());
}
public void setjm(){
	JMenu jm2 = new JMenu("Menu");
	JMenu jm1 = new JMenu("tool");
	JMenu jm3 = new JMenu("set");
	JMenuItem jmii1 = new JMenuItem("save");
	JMenuItem jmii2 = new JMenuItem("open");
	JMenuItem jmii3 = new JMenuItem("send");
	JMenuItem jmii4 = new JMenuItem("exit");
	JMenuItem jmi1 = new JMenuItem("pen");
	JMenuItem jmi2 = new JMenuItem("rubber");
	JMenuItem jmi3 = new JMenuItem("lines");
	JMenuItem jmi5 = new JMenuItem("circle");
	JMenuItem jmi6 = new JMenuItem("Rect");
	JMenuItem jmi4 = new JMenuItem("clear");
     
    jmii1.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
		jfc.showOpenDialog(getContentPane());
		File file = jfc.getSelectedFile();
			try{
				if(file.exists())
					file.delete();
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", bao);
			b = bao.toByteArray();
			
			//Can't save in C
			FileOutputStream fs = new FileOutputStream(file);
			fs.write(b);
			fs.close();
			}
			catch(Exception h){
				System.out.println(h);
			}
		}
    	
    });
    jmii2.addActionListener(new ActionListener(){

		
		public void actionPerformed(ActionEvent e) {
			jfc.showOpenDialog(getContentPane());
			File file = jfc.getSelectedFile();
			
			
			try
			{
				img = ImageIO.read(file);
			}
			catch(Exception ee){
				System.out.println(ee);
			}
			dc.setimg(img);
			dc.repaint();
			gs=img.getGraphics();
			g=(Graphics2D)gs;
			//Here you have to reset the Graphics again so that you can continue to draw 
		}
    	
    });
    jmii3.addActionListener(new ActionListener(){

	
		public void actionPerformed(ActionEvent e) {
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			
			try{
				ImageIO.write(img, "jpg", bao);
				b = bao.toByteArray();
			pr.write(b);
			System.out.println(b[3]+"code");
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
			
		}
    });
    	
    jmii4.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			judge = false;
			System.exit(0);
		}
    	
    });
	jmi1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    g.setColor(c);
		    rp=1;
		}
		
	});
	jmi2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			g.setColor(Color.WHITE);
			rp=2;
		}
		
	});
	jmi3.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			g.setColor(c);
			rp=3;
			
		}
		
	});
	jmi4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 800, 600);
			dc.repaint();
		}
		
	});
	jmi5.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			g.setColor(c);
			rp=4;
		}
		
	});
	jmi6.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			g.setColor(c);
			rp=5;
		}
		
	});
	jm3.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
		    
		}
		
	});
	jm1.add(jmi1);
	jm1.add(jmi2);
	jm1.add(jmi3);
	jm1.add(jmi5);
	jm1.add(jmi6);
	jm1.add(jmi4);
	jm2.add(jmii1);
	jm2.add(jmii2);
	jm2.add(jmii3);
	jm2.add(jmii4);
	jm.add(jm2);
	jm.add(jm1);
	jm.add(jm3);
}
public void setjtoolbar(){
	 final Choice jccolor = new Choice();
	
	jccolor.add("1");
	jccolor.add("2");
	jccolor.add("3");
	jccolor.add("4");
	jccolor.add("5");
	jccolor.add("6");
	jccolor.add("7");
	jccolor.addItemListener(new ItemListener(){

		
		public void itemStateChanged(ItemEvent e) {
			int i=3;
			if(e.getSource()==jccolor)
			{
				String size = jccolor.getSelectedItem();
				i = Integer.valueOf(size);
			}
			BasicStroke bs = new BasicStroke(i);
			g.setStroke(bs);
		}
		
	});
	jtb.setBounds(0, 0, 100, 600);
	final Choice jcsize = new Choice();
	jcsize.add("red");
	jcsize.add("blue");
	jcsize.add("black");
	jcsize.add("green");
	jcsize.add("yellow");
	jcsize.add("orange");
	jcsize.add("cyan");
	jcsize.add("self-defined");
	jcsize.addItemListener(new ItemListener(){

		public void itemStateChanged(ItemEvent e) {
			if(e.getSource()==jcsize){
				String color = jcsize.getSelectedItem();
				if(color.equals("red"))
					c=Color.red;
				if(color.equals("black"))
					c=Color.black;
				if(color.equals("green"))
				    c=Color.green;
				if(color.equals("blue"))
				    c=Color.blue;
				if(color.equals("yellow"))
				    c=Color.yellow;
				if(color.equals("orange"))
				    c=Color.orange;
				if(color.equals("cyan"))
				    c=Color.cyan;
				if(color.equals("self-defined")){
					Color cc=new Color(0,0,0);
					c = JColorChooser.showDialog(new Panel(), "ColorChooser", cc);
					g.setColor(c);
				}
			}
			g.setColor(c);
		}
		
	});
	
	JLabel jl1 = new JLabel("pensize");
	JLabel jl2 = new JLabel("pencolor");
	jtb.add(jl1);
	jtb.add(jccolor);
	jtb.add(jl2);
	jtb.add(jcsize);
	jtb.setLayout(new GridLayout(25,0));
}
	
public void initialize(){
	g.setColor(Color.red);
	dc.setimg(img);
}

public class M1 implements MouseMotionListener{

	public void mouseDragged(MouseEvent e) {
		switch(rp)
		{
		case 1:if(x>0&&y>0)
			g.drawLine(x,y,e.getX(), e.getY());
		x=e.getX();
		y=e.getY();
		dc.repaint();
		break;
	
		case 2:	if(x>0&&y>0)
				g.fillRect(x, y, 30, 30);
		x=e.getX();
		y=e.getY();
		dc.repaint();
			break;
		
		}	
		
	}
	public void mouseMoved(MouseEvent e) {
		switch(rp){
		case 1:dc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		break;
		case 2:dc.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		break;
		case 3:dc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		break;
		case 4:dc.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		break;
		case 5:dc.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		break;
			
		}
	}
	
}
public class M2 implements MouseListener{
public int xi=0,yi=0;
public boolean judge = false;	
	public void mouseClicked(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		 if(rp==3){	
		if(!judge){
	xi=e.getX();
	yi=e.getY();
	judge=true;}
		else{
			g.drawLine(xi, yi, e.getX(), e.getY());
			dc.repaint();
			judge=false;
			
		}
	}
	if(rp==4){
		if(!judge){
			xi=e.getX();
			yi=e.getY();
			judge=true;}
				else{
					int a = e.getX();
					int b = e.getY();
					int height=yi-b;
					int width=xi-a;
					if(height<0)
						height*=-1;
					if(width<0)
						width*=-1;
					g.drawOval(xi, yi, width, height);
					dc.repaint();
					judge=false;
				}
	}
	if(rp==5){
		if(!judge){
			xi=e.getX();
			yi=e.getY();
			judge=true;}
				else{
					int a = e.getX();
					int b = e.getY();
					int height=yi-b;
					int width=xi-a;
					if(height<0)
						height*=-1;
					if(width<0)
						width*=-1;
					g.drawRect(xi, yi, width, height);
					dc.repaint();
					judge=false;
				}
	}
	
	}
	public void mouseReleased(MouseEvent e) {
		
		if(rp==1||rp==2){
		x=-1;
		y=-1;
		
		}
		
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
public class Drawcanvas extends Canvas{
		private Image img = null;
		public Drawcanvas(){
			super();
		}
		public void setimg(Image ig){
		   img = ig;
		}
		public void paint(Graphics g){
			g.drawImage(img, 0,0,null);
		}
		public void update(Graphics g){
			paint(g);
		}
	}

	public static void main(String[] args) {
		Drawingboard db = new Drawingboard();
		db.setVisible(true);
        db.getserver();
}


	public void run() {
	
	  
		try{
			
			pr = new BufferedOutputStream(so.getOutputStream());
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void getserver(){
		 try{
		  so = new Socket(InetAddress.getLocalHost(),7707);
		  System.out.println("finish");
		  new Thread(this).start();
	}
		 catch(Exception e){
			 
		 }
	}
}

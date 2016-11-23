package Char;
import java.net.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
public class Client extends JFrame implements Runnable{
	Socket so;
	JTextField jf2 =new JTextField();
	JTextArea jta = new JTextArea();
	JTextArea jf1 = new JTextArea(1,1);
	JTextArea pp = new JTextArea();
	JButton jb1 = new JButton("send");
	JButton jb2 = new JButton("close"); 
	Drawcanvas dc = new Drawcanvas();
	boolean judge = true;
public	boolean j=true;
	public BufferedImage img = new BufferedImage(800,500,BufferedImage.TYPE_INT_BGR);
	Graphics gs = img.getGraphics();
	Graphics2D g = (Graphics2D)gs;
	Times tim = new Times();
private PrintWriter pr = null;
private ObjectInputStream br = null;
		public Client(){
			pp.setBackground(Color.white);
			dc.setBounds(0, 0, 800, 500);
			dc.setimg(img);
			this.setTitle("char");
			this.setBounds(0, 0, 800, 700);
			this.setLayout(null);
			this.getContentPane().add(dc);
			
			this.setLayout(null);
			jb1.addActionListener(new R1());
			jb2.setBounds(200, 600, 80,30);
			jta.setBounds(280, 560, 80, 30);
			jb1.setBounds(0, 600, 80, 30);
			jb2.addActionListener(new R2());
			jf2.setBounds(0, 560, 200, 30);
			jf1.setBounds(700, 560, 80, 30);
			jf1.setEditable(false);
			jta.setEditable(false);
			
			g.setColor(Color.white);
			g.fillRect(0, 0, 800, 500);
			JLabel point = new JLabel("point");
			point.setBounds(400,560,80,30);
			this.add(point);
		    pp.setEditable(false);
		    pp.setBounds(500,560,80,30);
		    pp.setText("0");
		    this.add(pp);
		    this.add(jf2);
			this.add(jb1);
			this.add(jb2);
			this.add(jf1);
			this.add(jta);
		    this.validate();
		    this.setVisible(true);
		}
		private class R1 implements ActionListener{

			
			public void actionPerformed(ActionEvent e) {
			 String str = jf2.getText();
			
			 try{
			 pr.println(str);
			 
			 }
			 catch(Exception ee){
				 ee.printStackTrace();
			 }
			 jf2.setText("");
			}
			
		}
       private class R2 implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			judge=false;
			System.exit(0);
		}
    	   
       }
       public void run() {
    	
    	 
    	   while(judge){
    		   try{
    			 byte[] bb = (byte[])br.readObject();
    			 if(bb.length==1){
    				 if(bb[0]==1){
    				 int p = Integer.valueOf(pp.getText());
    				 p++;
    				 pp.setText(String.valueOf(p));
    				 jta.setText("Accept");
    				 }
    				 if(bb[0]==-1){
    					 jta.setText("Wronganswer");
    				 }
    				 if(bb[0]==2){
    					
    						tim.start();
    				 }
    				 if(bb[0]==3)
    					 j=false;
    			 }
    			 else{
    			ByteArrayInputStream in = new ByteArrayInputStream(bb);
    			BufferedImage imgs = ImageIO.read(in);
    			dc.setimg(imgs);
		        dc.repaint();
    			 }
    		   }
    		   
    		   catch(Exception e){
    			   e.printStackTrace();
    		   }
    	   } 
    	   
		
	}
 private void getserver(){
	 System.out.println("try to connect");
	 try{
		 so = new Socket("121.250.218.177",7703);
		 System.out.println("finish");
		 br = new ObjectInputStream(so.getInputStream());
		 pr = new PrintWriter(so.getOutputStream(),true);
		 new Thread(this).start();
		 
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
 }
	public static void main(String[] args) {
	Client cl = new Client();
	cl.setVisible(true);
	cl.getserver();
	
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
public class Times extends Thread{
int minute=2;
int second=60;
String m,s;
JLabel jl;
int record=0;
	public void run(){
		while(j){
			if(record==3){
				record=0;
				jta.setText("");
			}
			record++;
			if(second==0){
				minute--;
				second=60;
				}
			    m = String.valueOf(minute);
				s = String.valueOf(second);
				jf1.setText(minute+":"+second);
				try{
				Thread.sleep(1000);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			second--;
			if(minute==0&&second==0){
				jf1.setText("0:0");
				break;
			}
				
		}
		
	}
	
}
}

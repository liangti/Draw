package Test;

import java.awt.*;
import java.io.*;
import java.net.*;

import javax.swing.*;
import java.awt.event.*;


public class Charuser extends JFrame implements Runnable{
    Socket so;
	JTextArea jf1 =new JTextArea(8,8);
	JTextField jf2 =new JTextField();
	JButton jb1 = new JButton("send");
	JButton jb2 = new JButton("close");
	boolean judge = true;

		public Charuser(){
			
			this.setTitle("char");
			this.setBounds(0, 0, 600, 400);
			this.setLayout(null);
			this.add(jf1);
			this.add(jf2);
			this.add(jb1);
			this.add(jb2);
			jf1.setEditable(false);
			jb1.addActionListener(new R1());
			jb1.setBounds(0, 300, 80,30);
			jb2.setBounds(100, 300, 80, 30);
			jb2.addActionListener(new R2());
			jf1.setBounds(0, 10, 600, 200);
			jf2.setBounds(0, 230, 600, 30);
			this.validate();
			this.setVisible(true);
			
		}
		private class R1 implements ActionListener{

		
			public void actionPerformed(ActionEvent e) {
			 
			}
			
		}
       private class R2 implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			judge=false;
			
		}
    	   
       }
		public static void main(String[] args){
			Charuser ch = new Charuser();
			ch.setVisible(true);
            Thread th = new Thread(ch);
            th.start();

		}

		
		public void run() {
		Socket so;
		try
		{
			so =new Socket(InetAddress.getLocalHost(),7012);
			System.out.println("success");
			BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
	        String str = br.readLine();
	        System.out.println(str);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
}

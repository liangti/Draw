package Test;
import java.io.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;
public class Charman extends JFrame implements Runnable{
JButton jb = new JButton("close");
boolean judge = true;
public Charman(){
	this.setVisible(true);
	this.setBounds(0, 0, 100, 100);
	this.add(jb);
	jb.addActionListener(new ActionListener(){

		
		public void actionPerformed(ActionEvent e) {
			judge = false;
		}
		
	});
	
}
	public static void main(String[] args) {
		Charman cm = new Charman();
		Thread th = new Thread(cm);
		th.start();
		
}
	
	public void run() {
	ServerSocket ss; 
	try{
		ss=new ServerSocket(7012);
		System.out.println("yes");
		while(judge){
		Socket so = ss.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
		wr.write("fuck");
		}	
	}
	catch(Exception e){
		e.printStackTrace();
	}
	}
}

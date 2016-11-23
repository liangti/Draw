package Char;

import java.net.*;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
public class Serverpro {
private Socket so1,so2;
private String str=null;
private int command=0;
int commands=0;
JFrame ss = new JFrame("Manager");
JTextArea jta1 = new JTextArea();
JTextArea jta2 = new JTextArea();
JTextArea jta3 = new JTextArea();
PrintWriter pr = null;
Times tim = new Times();
Chars c;
Draw d;
Sql sql;
public boolean judge=true;
public void getserver(){
	try{
	ServerSocket ss = new ServerSocket(7703);
	System.out.println("server is already connect");
	so2 = ss.accept();
	so1 = ss.accept();

	Chars c =new Chars(so1);
	Draw d =new Draw(so2);
	c.start();
	d.start();
	
	
	
	
	    }catch(Exception e){
		e.printStackTrace();
	}

}
public void setinterface(){
    JLabel jl1 = new JLabel("point");
    jl1.setBounds(0,0,50,30);
    JLabel jl2 = new JLabel("task");
    jl2.setBounds(200,0,50,30); 
    JLabel jl3 = new JLabel("time");
    jl3.setBounds(0,50,50,30);  
	jta1.setBounds(250,0, 80, 30);
	jta1.setEditable(false);
	jta2.setBounds(50, 0, 80, 30);
	jta2.setEditable(false);
	jta3.setBounds(50,50,80,30);
	jta3.setEditable(false);
	jta2.setText("0");
	ss.setBounds(0, 0, 400, 300);
	JButton jb = new JButton("begin");
	ss.setLayout(null);
	jb.setBounds(0, 200, 400, 100);
	jb.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
			 pr.println("begin");
			 System.out.println("begin");
			 str=sql.operate("1");
			 jta1.setText(str);
			 pr.println("task");
			 pr.println(str);
			 tim.start();
			}
			catch(Exception ee){
				
			}
			 commands=2;
		}
		
	});
	ss.add(jb);
	ss.add(jta1);
	ss.add(jta2);
	ss.add(jl1);
	ss.add(jl2);
	ss.add(jl3);
	ss.add(jta3);
	ss.setVisible(true);
}
public void getsql(){
	sql = new Sql();
	sql.getcon();

}

private class Draw extends Thread{
	Socket so = null;
	private ObjectInputStream br;
    private ObjectOutputStream ops;
	
	public Draw(Socket so){
		this.so=so;
	}
	public void run(){
		try{
			br = new ObjectInputStream(so.getInputStream());
			pr = new PrintWriter(so.getOutputStream(),true);
			//Here you must only new one ObjectOutputStream,because one Thread can
			//only new one ObjectOutputStream,but other Stream is allow one or more.
			ops = new ObjectOutputStream(so1.getOutputStream());
			
			System.out.println(commands+"Draw");
			while(true){
			
			
			if(command==0){
			byte[] a =(byte[]) br.readObject();
			sendtoall(a);
			}
			if(command==-1){
				byte[] a = new byte[1];
				a[0]=-1;
				sendtoall(a);
				command=0;
			}
			if(command==1){
				byte[]a = new byte[1];
				a[0]=1;
				sendtoall(a);
				command=0;
			}
			if(command==2){
				byte[] a = new byte[1];
				a[0]=3;
				sendtoall(a);
				command=0;
			}
			if(commands==2){
				byte[]a = new byte[1];
				a[0]=2;
				sendtoall(a);
			}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		synchronized void sendtoall(byte[] a){
		
			
				try{
				
				
						ops.writeObject(a);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
	}
}
private class Chars extends Thread{
	Socket so = null;
	private BufferedReader br;

	
	public Chars(Socket so){
		this.so=so;
	}
	public void run(){
		try{
			br = new BufferedReader(new InputStreamReader(so.getInputStream()));
			String a;
			System.out.println(commands+"Chars");
			while(true){
			
			while((a = br.readLine())!=null){
			  	
			  pr.println("answer");
			  sendtoall(a);
			  if(a.equals(str))
			  {
				  sendtoall("point");
				  command=1;
				  int po = Integer.valueOf(jta2.getText());
				  po++;
				  if(po<10){
				  String s = String.valueOf(po+1);
				  str = sql.operate(s);
				  pr.println("task");
				  pr.println(str);
				  jta1.setText(str);
				  jta2.setText(String.valueOf(po));}
				  else
					  {
					  pr.println("stop");
					  command=2;
					  judge=false;
					  }
			  }
			  else
				  command=-1;
			}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	synchronized void sendtoall(String a){
		
		
			try{
				
			

					pr.println(a);
					
			
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
}
}

public static void main(String[] args) {
  Serverpro sp = new Serverpro();
   sp.setinterface();
   sp.getsql(); 
   sp.getserver();
   
	}
public class Times extends Thread{
	int minute=2;
	int second=60;
	String m,s;
		public void run(){
			while(judge){
			
			
				if(second==0){
					minute--;
					second=60;
					}
				    m = String.valueOf(minute);
					s = String.valueOf(second);
					jta3.setText(minute+":"+second);
					try{
					Thread.sleep(1000);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				second--;
				if(minute==0&&second==0){
					jta3.setText("0:0");
					break;
				}
				
			}
		}
		}	

}

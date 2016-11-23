package Draw;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import javax.swing.*;
public class User extends JFrame{
JTextArea jf1 =new JTextArea(8,8);
JTextField jf2 =new JTextField();
JButton jb1 = new JButton("send");
BufferedReader br; 
PrintWriter wr;

	public User(){
		
		this.setTitle("char");
		this.setBounds(0, 0, 600, 400);
		this.setLayout(null);
		this.add(jf1);
		this.add(jf2);
		this.add(jb1);
		jf1.setEditable(false);
		jb1.addActionListener(new R1());
		jb1.setBounds(0, 300, 80,30);
		jf1.setBounds(0, 10, 600, 200);
		jf2.setBounds(0, 230, 600, 30);
		this.validate();
		this.setVisible(true);
		
	}
	private class R1 implements ActionListener{

	
		public void actionPerformed(ActionEvent e) {
		  String str = jf2.getText();
		  jf1.append(str+"\n");
			
		}
		
	}
	public void getconnect(){
		try{
			Socket so = new Socket(InetAddress.getLocalHost(),7777);
	br = new BufferedReader(new InputStreamReader(so.getInputStream()));
	wr = new PrintWriter(so.getOutputStream(),true);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void getmsg(){
		try{
		jf1.append(br.readLine()+"\n");
	     }
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
	}

}

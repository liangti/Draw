package Char;
import javax.swing.*;
import java.awt.event.*;
public class Login extends JFrame{
JTextField id = new JTextField();
JPasswordField jp = new JPasswordField();
JButton jb1 = new JButton("Login");
JButton jb2 = new JButton("Register");
JFrame jc = new JFrame("choose");
public Login(String name){
	this.setTitle(name);
	this.setBounds(0,0,400,300);
	id.setBounds(100, 50, 150, 30);
	this.add(id);
	jp.setBounds(100, 100, 150, 30);
	this.add(jp);
	JLabel jl1 = new JLabel("user    ");
	JLabel jl2 = new JLabel("password");
	jl1.setBounds(30, 50, 70, 30);
	jl2.setBounds(30, 100, 70, 30);
	this.add(jl1);
	this.add(jl2);
	jb1.setBounds(80,180,90,30);
	jb2.setBounds(200, 180, 90, 30);
	this.add(jb1);
	this.add(jb2);
	this.setLayout(null);
	this.setVisible(true);
}
public void run(){
	jc.setBounds(0, 0, 300, 150);
	JLabel jl = new JLabel("Please choose one role");
	jl.setBounds(0, 0, 300, 30);
	jc.add(jl);
	jc.setLayout(null);
	jb1.addActionListener(new ActionListener(){

		
		public void actionPerformed(ActionEvent e) {
			jc.setVisible(true);
		}
		
	});
}
	public static void main(String[] args) {
		Login lo = new Login("Login");
		lo.run();
	}

}

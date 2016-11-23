package Draw;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class ActionListen extends JFrame{
 private JPanel pane = new JPanel();
 private JButton b = new JButton("button");	
 private JLabel l  = new JLabel("click 0 time");
 private int t =0;
 public ActionListen(){
	 pane.add(b);
	 pane.add(l);
	 this.add(pane);
	 this.setTitle("report the times you click the button");
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	  R listen = new R();
	  b.addActionListener(listen);
	  this.setBounds(0, 0,300,100);
	  this.setVisible(true);
	  this.validate();
 }
 private class R implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		t++;
		if(t<25)
		l.setText("click"+t+"times");
		else
			l.setText("fuckyou,you think I am foolish?");
	}
	 
 }
	public static void main(String[] args) {
	   new ActionListen();
	}

}


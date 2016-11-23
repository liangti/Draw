package Test;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Guess extends JFrame {
	BufferedImage image = null;

	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
    ObjectInputStream ops;
	TextField tfTxt = new TextField();

	boolean connected = false;
	

	public Guess() {
		this.setTitle("Guess");
		this.setBounds(300, 200, 500, 500);

		this.add(tfTxt, BorderLayout.SOUTH);
		tfTxt.addActionListener(new TFListener());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void connect() {
		try {
			s = new Socket("121.250.218.177", 8888);
			ops = new ObjectInputStream(s.getInputStream());
			System.out.println("connect");
			connected = true;
			new Thread(new R()).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) throws Exception {
		Guess GuessFrame = new Guess();
		GuessFrame.connect();
		
	}

	public void paint(Graphics g) {
		if (image != null)
			g.drawImage(image, 0, 0, 500, 500, this);
	}

	private class TFListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				dos.writeUTF(tfTxt.getText());
				dos.flush();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			tfTxt.setText("");
		}

	}

	class R implements Runnable {
		byte[] b = new byte[1000000];

		public void run() {
			try {
				System.out.println("fuc");
				while (connected) {
					
					ops = new ObjectInputStream (s.getInputStream());
					image = (BufferedImage)ops.readObject();
					
					ImageIO.write(image, "png", new File("F:\\image.png"));
					repaint();
				}
			} catch (Exception e) {
				System.out.println("°h¥X,bye");
			} 
		}
	}
}

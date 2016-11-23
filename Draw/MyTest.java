package Draw;

import java.net.*;
import java.io.*;

public class MyTest {  
	
	public static void main(String[] args)  { 
		DataInputStream br;
	    DataOutputStream wr ;
	     
		try{
		ServerSocket ss = new ServerSocket(8885);
		Socket so = ss.accept();
		br = new DataInputStream(so.getInputStream());
		wr = new DataOutputStream(so.getOutputStream());
		while(true){
		wr.writeUTF("fuck");
		Thread.sleep(1000);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	}
	
		

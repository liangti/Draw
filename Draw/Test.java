package Draw;
import java.net.*;
import java.io.*;
public class Test {


	public static void main(String[] args) {
		DataInputStream br;
		try{
			InetAddress id = InetAddress.getLocalHost();
			Socket so = new Socket(id,8885);
			br = new DataInputStream(so.getInputStream());
			while(true){
			String str = br.readUTF();
			System.out.println(str);
			Thread.sleep(1000);
			}
		}
		catch(Exception e){
			
		}
		
	}

}

package Test;
import java.util.*;
import java.io.*;
public class Btest {

	public static void main(String[] args) {
		String str = "fuckyou";
		byte[] a = str.getBytes();
        ArrayList<Byte> aa = new ArrayList<Byte>();
        for(int i=0;i<a.length;i++){
        	aa.add(a[i]);
        }
        for(int i=0;i<aa.size();i++){
        	System.out.print(aa.get(i));
        }
        byte[] na = {1,0,2,1,1,7,9,9,1,0,7,1,2,1,1,1,1,1,1,7};
	}

}

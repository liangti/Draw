package Char;

import java.sql.*;
public class Sql {
	Connection conn=null;
public Connection getcon(){
	try{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/t";
		String user = "root";
		String pass = "123456";
		conn = DriverManager.getConnection(url,user,pass);
		if(conn!=null)
			System.out.println("finish connecting");
		
	}
	catch(Exception e){
		
	}
	return conn;
}
public String operate(String num){
	String str = null;
	try{
         Statement statement = conn.createStatement();
         ResultSet res = statement.executeQuery("select word from ww "+
         "where id="+num);
         res.next();
         str = res.getString("word");
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return str;
}
	public static void main(String[] args) {
	
	}

}

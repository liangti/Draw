package Test;

public  class Threadtest extends Thread{
private String str;
public	Threadtest(String str){
	this.str=str;
	
}
public void run(){
	while(true){
	System.out.println(str);
	try{
		Thread.sleep(1000);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	}
}
	public static void main(String[] args) {
		Threadtest th =new Threadtest("fuckyou");
		th.start();
	}

}

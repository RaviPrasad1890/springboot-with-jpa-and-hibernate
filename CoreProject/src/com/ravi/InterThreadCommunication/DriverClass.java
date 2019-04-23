package com.ravi.InterThreadCommunication;

public class DriverClass {
	
	public static void main(String[] args) {
		
		    Printer print = new Printer();
		    Thread t1 = new Thread(new TaskEvenOdd(10, print, false),"Odd");
		    Thread t2 = new Thread(new TaskEvenOdd(10, print, true),"Even");
		    t1.start();
		    t2.start();
		
	}

}

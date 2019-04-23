package com.ravi.semaphore.oddevenprinter;

public class OddNumberThread extends Thread{
	
	int maxNumberToBePrinted;
	SharedPrinter sp;
	
	public OddNumberThread(int maxNumberToBePrinted, SharedPrinter sp) {
		super();
		this.maxNumberToBePrinted = maxNumberToBePrinted;
		this.sp = sp;
	}
	
	public void run() {
		for (int i = 1; i <= maxNumberToBePrinted; i=i+2) {
			sp.printOddNum(i);
		}
	}
	

}

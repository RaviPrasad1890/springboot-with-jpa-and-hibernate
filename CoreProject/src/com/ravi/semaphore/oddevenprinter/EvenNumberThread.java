package com.ravi.semaphore.oddevenprinter;

/*
 * Class is responsible to print even number
 */
public class EvenNumberThread extends Thread {
	
	int maxNumberToBePrinted;
	SharedPrinter sp;
	
	
	public EvenNumberThread(int maxNumberToBePrinted, SharedPrinter sp) {
		super();
		this.maxNumberToBePrinted = maxNumberToBePrinted;
		this.sp = sp;
	}
	
	public void run() {
		for (int i = 2; i <= maxNumberToBePrinted; i=i+2) {
			
			sp.printEvenNumber(i);
		}
	}
	
	

}

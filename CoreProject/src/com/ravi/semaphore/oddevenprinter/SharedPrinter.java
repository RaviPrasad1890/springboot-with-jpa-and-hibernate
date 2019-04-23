package com.ravi.semaphore.oddevenprinter;

import java.util.concurrent.Semaphore;

public class SharedPrinter {


	Semaphore evenSemaphore = new Semaphore(0);// Even number should not be printed first
	Semaphore oddSemaphore = new Semaphore(1);

	
	//Method to print even numbers
	public void printEvenNumber(int num) {
		try {
			evenSemaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(num);
		oddSemaphore.release();
	}

	// Method for printing odd numbers
	public void printOddNum(int num) {
		try {
			oddSemaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(num);
		evenSemaphore.release();

	}
}

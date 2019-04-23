package com.ravi.semaphore.oddevenprinter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This demo class is used about inter process of thread communication demo. 
 * e.g one thread print Odd number and another thread print even no by using Semaphore
 */
public class EvenOddDisplayBySemaphore {
 
	public static void main(String[] args) {
		SharedPrinter printer = new SharedPrinter();
 
		System.out.println("two different threads to print odd and even number upto max provided, starting from  1 : ");
		// Starting two threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		executor.execute(new EvenNumberThread(10, printer));
		executor.execute(new OddNumberThread(10, printer));
		
		executor.shutdown();
	}
}

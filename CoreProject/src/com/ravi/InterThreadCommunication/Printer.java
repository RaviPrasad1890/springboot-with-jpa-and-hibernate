package com.ravi.InterThreadCommunication;

public class Printer {

	private volatile boolean isOdd;//value will be false initially
	
	synchronized void printEven(int number) {
		
		while(!isOdd) {//since value is false initially, this while block will be executed and thread executing this bloc will enter into wait status
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName()+" : "+number);
		isOdd=false;
		notify();
	}
	
	
	synchronized void printOdd(int number) {
        while (isOdd) {//since value is false initially, wait method will not be called
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + number);
        isOdd = true;//setting true now so that printOdd thread will be in wait state and printEven will get chance
        notify();
    }
}

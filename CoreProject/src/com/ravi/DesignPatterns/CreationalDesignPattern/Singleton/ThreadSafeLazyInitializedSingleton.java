package com.ravi.DesignPatterns.CreationalDesignPattern.Singleton;

public class ThreadSafeLazyInitializedSingleton {

	private static ThreadSafeLazyInitializedSingleton instance;
	
	private ThreadSafeLazyInitializedSingleton() {}
	
	public synchronized ThreadSafeLazyInitializedSingleton getInsatnce() {
	
		if(instance==null) {
			instance = new ThreadSafeLazyInitializedSingleton();
			
		}
		
		return instance;
	}
	
/*
 * Above implementation works fine and provides thread-safety but it reduces the performance because of the cost associated with the synchronized method, 
 * although we need it only for the first few threads who might create the separate instances (Read: Java Synchronization). 
 * To avoid this extra overhead every time, double checked locking principle is used. In this approach, 
 * the synchronized block is used inside the if condition with an additional check to ensure that only one instance of a singleton class is created.
 */
	
	/*
	public static ThreadSafeLazyInitializedSingleton getInstanceUsingDoubleLocking(){
	    if(instance == null){
	        synchronized (ThreadSafeLazyInitializedSingleton.class) {
	            if(instance == null){
	                instance = new ThreadSafeLazyInitializedSingleton();
	            }
	        }
	    }
	    return instance;
	}
	*/
	
	//in this case only once it will check when instance is null, otherwise will return same instance, so performance improvesd
	//as all threads dosesn't have to go through synchronized block
}

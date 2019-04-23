package com.ravi.DesignPatterns.CreationalDesignPattern.Singleton;
/*
 * Static block initialization implementation is similar to eager initialization, 
 * except that instance of class is created in the static block that provides option for exception handling.
 */
public class StaticBlockSingleton {

	private static  StaticBlockSingleton instance;//we can't declare it final, as we declared in EagerInitialization,bcoz than we need to initialize it
	
	
	private StaticBlockSingleton() {}
	
	static {
		try {
			instance= new StaticBlockSingleton();
		}catch(Exception e) {
			System.out.println("A runtime exception occured while creating instance");
		}
	}
	
	public static StaticBlockSingleton getInstance() {
		return instance;
	}
}

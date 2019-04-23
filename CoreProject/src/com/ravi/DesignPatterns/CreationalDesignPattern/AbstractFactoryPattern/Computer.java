package com.ravi.DesignPatterns.CreationalDesignPattern.AbstractFactoryPattern;

/*
 * In the Abstract Factory pattern, we get rid of if-else block and have a factory class for each sub-class. 
 * Then an Abstract Factory class that will return the sub-class based on the input factory class. At first, 
 * it seems confusing but once you see the implementation, 
 * it’s really easy to grasp and understand the minor difference between Factory and Abstract Factory pattern.
 */
public abstract class Computer {

	public abstract String getRam();
	public abstract String getHdd();
	public abstract String getCpu();
	
	
	@Override
	public String toString() {
		return "Computer [getRam()=" + getRam() + ", getHdd()=" + getHdd() + ", getCpu()=" + getCpu() + "]";
	}
	
	
}

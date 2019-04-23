package com.ravi.DesignPatterns.CreationalDesignPattern.FactoryPatter;

/*
 * Factory design pattern is used when we have a super class with multiple sub-classes and based on input, 
 * we need to return one of the sub-class. This pattern take out the responsibility of instantiation of a class from client program to the factory class.
 */

/*
 * Super class in factory design pattern can be an interface, abstract class or a normal java class. 
 * For our factory design pattern example, we have abstract super class with overridden toString() method for testing purpose.
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

package com.ravi.DesignPatterns.CreationalDesignPattern.AbstractFactoryPattern;

public class ComputerFactory {

	public static Computer getComputer(ComputerAbstractFactory factory){
		return factory.getComputer();
	}
}

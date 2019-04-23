package com.ravi.DesignPatterns.CreationalDesignPattern.AbstractFactoryPattern;

public class PCFactory implements ComputerAbstractFactory {

	private String ram;
	private String hdd;
	private String cpu;
	
	public PCFactory(String ram, String hdd, String cpu){
		this.ram=ram;
		this.hdd=hdd;
		this.cpu=cpu;
	}

	@Override
	public Computer getComputer() {
		return new PC(ram,hdd,cpu);
	}


}

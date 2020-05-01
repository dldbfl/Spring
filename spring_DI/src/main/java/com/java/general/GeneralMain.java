package com.java.general;

public class GeneralMain {

	public static void main(String[] args) {
		
		Calculator cal = new Calculator();
		TripleSumation trisum = new TripleSumation();
		
		cal.setSumation(trisum);
		
		cal.sum(3,5);
		cal.sum(3, 5, 10);
	}

}

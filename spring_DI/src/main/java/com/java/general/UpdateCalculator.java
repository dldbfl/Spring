package com.java.general;

public class UpdateCalculator extends Calculator{
	
	private Sumation sumation = new Sumation();
	public void setSumation(Sumation sumaion) {
		this.sumation = sumaion;
	}
	
	public void sum(int a, int b) {
		sumation.setA(a);
		sumation.setB(b);
		
		System.out.println("두 정수 "+a+", "+b+"의 합은 "+sumation.sum()+"일까요?");
	}
	
	public void sum(int a, int b, int c) {
		if(sumation instanceof TripleSumation) {
			((TripleSumation) sumation).setC(c);
			int result = sumation.sum();
			System.out.println("세 정수  "+a+", "+b+", "+c+"의 합은 "+result+"인줄 알고계십니까?");
		}else {
			System.out.println("함부로 세 정수 합을 하지마세요.");
		}
	}
}

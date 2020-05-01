package com.jsp.test;

public class Main {
	
	public static void main(String[] args) {
		
		Parent parent = Parent.getInstance();
		
		String result="";
		try {
			result=parent.resultSum(4, 5);
		} catch (Exception e) {
			result="오류가 발생했서";
			e.printStackTrace();
		}
				
		System.out.println(result);
		
	}
	
}

package com.shiv.TourUtility;

public class MyDummyChild1 {

 public void myFunc1() throws Exception {
		try{
			myFunc2();
			System.out.println("After calling child func");
		}
		catch(ArithmeticException e){
			System.out.println("Parent Function");
		}
	}

	
	public void myFunc2() throws Exception{
		
		try{
			int x = 5;
			int y = x/0;
			
		}
		catch(ArithmeticException e){
			System.out.println("Child function");
			throw new UserException("Divide by zero");
		}
	}
	
}

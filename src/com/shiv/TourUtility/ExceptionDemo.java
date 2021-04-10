package com.shiv.TourUtility;

public class ExceptionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
			
		MyDummyChild1 mdc = new MyDummyChild1();
		mdc.myFunc1();
		System.out.println("After calling from main");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("Main function");
			
		}
		
		
	}

}

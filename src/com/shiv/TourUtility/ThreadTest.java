package com.shiv.TourUtility;

public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ThreadDemo R1 = new ThreadDemo( "Thread-1");
	      R1.start();
	      
	      ThreadDemo R2 = new ThreadDemo( "Thread-2");
	      R2.start();	
		
	}

}

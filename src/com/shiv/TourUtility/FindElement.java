package com.shiv.TourUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindElement {
	
	
	public static WebElement isElementPresent(WebDriver driver){
		WebElement ele=null;
	
		for(int i=0;i<20;i++){
			try{
				System.out.println("Iteration " + i);
				ele=findElement(driver);
				String text = ele.getText();
				System.out.println(text + ele.toString());
				//Thread.sleep(2000);
				break;
			
			}catch(Exception e1){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				System.out.println("waiting for element to appear in dom");
			}
		
		}
		return ele;
	}
	
	
	
	
	
	public static WebElement findElement(WebDriver driver){
		
		
		
		WebElement ele = driver.findElement(By.xpath("//div[@id='page']//table/tbody//td/h1[contains(text(),'Verizon Enterprise Login')]"));
		
		//ele = null;
		
		return ele;
		
	}
	
	

}

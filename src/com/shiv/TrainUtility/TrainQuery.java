package com.shiv.TrainUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TrainQuery {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		File file = new File("D:/workspace/Selenium_Beginners/TrainDemo.properties");
		FileInputStream fileinput = null;
		
		try{
			fileinput = new FileInputStream(file);
			}
		catch(FileNotFoundException e){
			e.printStackTrace();
		    }

		Properties prop = new Properties();
		try{
			prop.load(fileinput);
			}
		catch(IOException e){
			e.printStackTrace();
			}

		Proxy proxy=new Proxy();
		proxy.setProxyType(Proxy.ProxyType.PAC);
		proxy.setProxyAutoconfigUrl("http://10.203.7.170/proxy.pac");
		DesiredCapabilities capabilities =new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY,proxy);

		WebDriver driver = new FirefoxDriver(capabilities);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));
		System.out.println("I am here");
		WebElement homePg = driver.findElement(By.xpath("//table/tbody//font[contains(text(),'INDIAN RAILWAYS')]"));
		if(homePg.isDisplayed()){
			System.out.println("You are at homepage");
		}
		else {System.out.println("You are not at Home Page");
		driver.close();
		}		
		
		Actions action = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath("//table/tbody//a[contains(text(),'Availability  at Major Stations')]"));
		action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//div[@class='anylinkmenu']//a[contains(text(),'Next 7 days Availability')]"))).click().build().perform();
		
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		   for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          WebElement trainAvl = driver.findElement(By.xpath("//table/tbody//strong[contains(text(),'Top 100 Booking Stations')]"));
		          
		          if(trainAvl.isDisplayed())
		          {	  System.out.println("U r at Train availability page");
		          }
		          else 
		          {  System.out.println("U r NOT at Train availability page");
		          }
		          
		          driver.findElement(By.xpath(".//table/tbody//font[contains(text(),'TATA')]")).click();
		          driver.close(); //closing child window
		          driver.switchTo().window(parentWindow); //cntrl to parent window
		          }
		       }
		
		handles = driver.getWindowHandles();
		for(String windowHandle  : handles)
	       {
	       if(!windowHandle.equals(parentWindow))
	          {
	          driver.switchTo().window(windowHandle);
	          WebElement trainAvl = driver.findElement(By.xpath("//table/tbody//td[contains(text(),'TATANAGAR')]"));
	          
	          if(trainAvl.isDisplayed())
	          {	  System.out.println(" U r at Station availability page");
	          }
	          else 
	          {  System.out.println(" U r NOT at Station availability page");
	          }
	          
	          Thread.sleep(5000);
	          driver.close(); //closing child window
	          driver.switchTo().window(parentWindow); //cntrl to parent window
	          System.out.println("Back to Home Page");
	          }
	       }
		   
		
		driver.close();
	}

}

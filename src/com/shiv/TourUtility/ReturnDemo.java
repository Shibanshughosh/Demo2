package com.shiv.TourUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ReturnDemo {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DesiredCapabilities capability = new DesiredCapabilities();
	    capability.setJavascriptEnabled(true);
	    capability.setBrowserName("IE");
	    capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		System.setProperty("webdriver.ie.driver", "D:\\SeleniumFiles\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(capability);
		driver.manage().window().maximize();
		driver.get("https://pcguiad.ebiz.verizon.com/pcguiAD/ordermanager");
				
		FindElement.isElementPresent(driver);
		
		System.out.println("Element Found");
		
		driver.close();
		
	}

}

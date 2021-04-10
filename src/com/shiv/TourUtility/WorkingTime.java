package com.shiv.TourUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WorkingTime {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);
        //capability.setBrowserName(browserName);
		capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		System.setProperty("webdriver.ie.driver", "D:\\SeleniumFiles\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(capability);
		driver.get("http://ep.intra.aricent.com/irj/portal");
		driver.findElement(By.xpath(".//table[@id='level2']/tbody//a[contains(text(),'Working Time')]")).click();
		WebElement framedesktop1 = driver.findElement(By.name("Desktop Innerpage   "));
		driver.switchTo().frame(framedesktop1);
		WebElement framedesktop = driver.findElement(By.id("isolatedWorkArea"));
		driver.switchTo().frame(framedesktop);
		driver.findElement(By.xpath(".//a[@title='Leave Request']")).click();
		Thread.sleep(5000);
		driver.close();
		
	}

}

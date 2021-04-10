package com.shiv.TourUtility;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class EasrDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);
        capability.setBrowserName("IE");
        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		System.setProperty("webdriver.ie.driver", "D:\\SeleniumFiles\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(capability);
		
		driver.manage().window().maximize();
		driver.get("http://easrqa.verizon.com/asrad/asrMain.jsp");
				
		if(driver.getTitle().contains("Enterprise Single Sign"))
		{
			driver.findElement(By.id("USERID")).sendKeys("GHOSSH5");
			driver.findElement(By.id("PASSWORD")).sendKeys("TheGreen@rr0w");
			driver.findElement(By.xpath("//span[text()='Sign-On']")).click();
			Alert signOnAlert = driver.switchTo().alert();
			signOnAlert.accept();	
			
			Thread.sleep(8000);
		}
		else{ System.out.println("invalid page");
		}
		
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for (WebElement iframe : iframes) 
		{
			System.out.println("Frames: " +iframe);
		}		
		
		WebElement frame1 = driver.findElement(By.name("navigate"));
		driver.switchTo().frame(frame1);
		Thread.sleep(8000);
		driver.findElement(By.xpath(".//a[@id='SEARCH']")).click();	
		System.out.println("Clicked Serach Order");
		Thread.sleep(8000);
		driver.switchTo().defaultContent();
        WebElement mainFrame=driver.findElement(By.xpath("//frame[@name='main']"));
        System.out.println(driver.switchTo().frame(mainFrame));
        WebElement frame2 = driver.findElement(By.xpath("//iframe[contains(@src,'getSearchForm')]"));
		driver.switchTo().frame(frame2);
        System.out.println("Frame switched");
        
        Thread.sleep(5000);
        try
        {
        	System.out.println("Enter Order Number");
        	driver.findElement(By.xpath("//*[@name='order']")).sendKeys("24469044");
        	driver.findElement(By.xpath("//input[@name='submit']")).click();
 	       	Thread.sleep(5000);
 	        //driver.getWindowHandle();
 	       //	wb1.getTablehead("//table[@id='row']/thead");
        }
        catch(Exception e){
        	e.getMessage();
        }
		
        System.out.println("Click on the pon number");
        driver.findElement(By.xpath(".//*[@id='row']/tbody/tr/td[5]/a")).click();
        Thread.sleep(8000);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frame1);
        Thread.sleep(8000);
        System.out.println("Click on Admin Form");
		driver.findElement(By.xpath(".//a[contains(@id,'getAdminForm')]")).click();
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(mainFrame);
		WebElement iframe3 = driver.findElement(By.xpath(".//iframe[contains(@src,'getAdminForm')]"));
		driver.switchTo().frame(iframe3);
		
		WebElement SAVE = driver.findElement(By.xpath("//input[@value='Save Changes']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SAVE);
		SAVE.click();
		
		/*WebElement SAVE_THEN_NEXT = driver.findElement(By.xpath("//input[@title='Shortcut: ALT+N']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SAVE_THEN_NEXT );
		SAVE_THEN_NEXT.click();*/
        
		System.out.println("Clicked on SAVE_THEN_NEXT button");
		
		Thread.sleep(5000);
		driver.close();
		
		}
	}



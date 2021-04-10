package com.shiv.TourUtility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BgwDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		{
			System.out.println("Now launching UAT4");
			DesiredCapabilities capability = new DesiredCapabilities();
	        capability.setJavascriptEnabled(true);
	        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			System.setProperty("webdriver.ie.driver","D:\\SeleniumFiles\\IEDriverServer.exe");
			WebDriver driver=new InternetExplorerDriver(capability);

			/**Launching UAT4 and logging in */

			driver.get("https://bgwqtcple.vzbi.com/IONSWeb/login.do");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.name("USERID")).sendKeys("GHOSSH5");
			driver.findElement(By.id("PASSWORD")).sendKeys("Mohanbagan!1");
			driver.findElement(By.partialLinkText("Sign-On")).click();
			Thread.sleep(8000);

			/**Closing the POPUP */

			System.out.println("Now closing window");
			Alert alt = driver.switchTo().alert();
			alt.accept();
			System.out.println("Waiting for 10000ms");
			Thread.sleep(10000);

			/**Selecting Create Aisle from Main Menu */		

			System.out.println("Launching Create Aisle");
			// now finding the id of the main page which is the Create Aisle page
			String homePage = driver.getWindowHandle();
			System.out.println(homePage);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//driver.findElement(By.xpath(".//*[@id='top']/div/div[2]/div/ul/li[18]/a")).click();
			
			driver.findElement(By.xpath("//table/tbody//div/h3[contains(text(),'Inventory')]/parent::div/div/ul/li/a[contains(text(),'Create Aisle')]")).click();
		   	System.out.println("Waiting implicit");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    //WebElement myDynamicElement1 = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.name("Create Aisle")));
	        		  
	        				  
		    /** Adding Aisle */
		    WebElement aisle = driver.findElement(By.name("Create Aisle"));
			driver.switchTo().frame(aisle);
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 	driver.findElement(By.xpath("//input[@id='n0.tm_vp_Site']")).sendKeys("IRNG.IAE"); 
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    driver.findElement(By.xpath("//div[@class='search-item x-combo-selected']/font[@face='Verdana']")).click();
		  	driver.findElement(By.id("n0.tm_vp_LineUp")).sendKeys("SIT2028");
	        new Select(driver.findElement(By.id("n0.tm_vp_status"))).selectByVisibleText("IN EFFECT");
	        driver.findElement(By.xpath(".//*[@id='id_buttons']/table/tbody/tr/td[2]/input[1]")).click();
	     //closing the popup confirmation
	        System.out.println("Closing the popup");
	        Thread.sleep(16000);
	       // driver.findElement(By.xpath("//table[@id='ext-comp-1010']/tbody/tr/td[2]")).click(); 
	        //using code below to close the pop up instead of above 2 lines//
/*	        //Thread.sleep(10000);
	        
			*/
	        //driver.switchTo().parentFrame();
	        //driver.switchTo().frame(aisle);
	        System.out.println("switched to aisle frame");
	        /*try{
	        WebElement myDynamicElement = (new WebDriverWait(driver, 30))
	        		  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='x-btn-text'][text()='OK']"))); 
	        myDynamicElement.click();
	        }catch(Exception e){
	        	System.out.println(e.getMessage());
	        }*/
	       
	        	WebElement ele = driver.findElement(By.xpath("//button[@class='x-btn-text'][text()='OK']"));
	        		ele.click();
	        		Thread.sleep(10000);
	        //switching to the right frame//
	        driver.switchTo().defaultContent();
	        System.out.println(" parent frame");
	        WebElement fr = driver.findElement(By.name("Equipment"));
	        driver.switchTo().frame(fr);
	        System.out.println(" now we are in fr ");
	        
	       
	        //div[contains(text(),'SIT2020')]/parent::*/parent::*/td[1]/div[2]
	        Thread.sleep(5000);
	        System.out.println("switched to equipment frame");
	        //clicks the search element checkbox//
	        driver.findElement(By.xpath("//table/tbody/tr/td/div/div[@class='x-grid3-row-checker']")).click();


		 	  /** Adding Rack */
	 	    //clicking on the Add Rack Tab//
	        Thread.sleep(8000);
	        System.out.println("Clicking on Add Rack");
	 	   driver.findElement(By.xpath("//button[contains(text(),'Add Rack']")).click();
	 	    Thread.sleep(5000);  
	 	    // finding site field
	 	   System.out.println("Entering Site");
	 	    driver.findElement(By.xpath(".//*[@id='n0.tm_vp_locationId']")).sendKeys("IRNG.IAE");

	 	    //click on populated site name//
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    driver.findElement(By.xpath("//div[@class='search-item x-combo-selected']/font[@face='Verdana']")).click();
		    Thread.sleep(5000);
	 	    //Enter the Aisle//
	 	    driver.findElement(By.xpath(".//*[@id='equipmentaddtab']/table/tbody/tr/td/table/tbody/tr[1]/td[5]")).sendKeys("SIT399");
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        driver.findElement(By.xpath("//div[@class='search-item x-combo-selected']/font[@face='Verdana']")).click();
	 	    //Enter the Frame//
	 	    driver.findElement(By.xpath(".//*[@id='n0.tm_vp_frame']")).sendKeys("100");
	 	     //Enter the category as Generic Rack BGW//
	 	    new Select(driver.findElement(By.xpath(".//*[@id='n0.tm_vp_template']"))).selectByVisibleText("GENERIC_RACK_BGW");

	 	    //Enter Status as In Effect//
	 	   new Select(driver.findElement(By.id("n0.tm_vp_status"))).selectByVisibleText("IN EFFECT");
	 	    	    //Click on Add//
	 	   driver.findElement(By.xpath(".//*[@id='n0.tm_vp_add']")).click();


	 	  /** Selecting Site via Binoculars.*/

		    /*Blocking this code as code above allows the Site selection from the Aisle screen itself
		     is being entered and selected directly from the Add Aisle Screen*/ 
		 	    	//now clicking on magnifying glass.
		    /*
	           driver.findElement(By.xpath(".//*[@id='tm_vps_div']/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr/td[2]/a/img")).click();	 	  
		 	     // switching to popup window
			     System.out.println("Switching to popup");
					     Thread.sleep(12000);
					// Switch to new window opened
				for(String winHandle : driver.getWindowHandles())
				{
		    	driver.switchTo().window(winHandle);
				}
				driver.manage().window().maximize();
				Thread.sleep(5000);
				// entering the site id 
	   	 	  	driver.findElement(By.id("tm_lo_name")).sendKeys("IRNG.IAE");
				Thread.sleep(8000);
				//driver.findElement(By.xpath(".//*[@id='tm_vps_div']/table/tbody/tr/td/table/tbody/tr[2]/td[2]")).click();
				System.out.println("Clicking on Search now");
				Thread.sleep(12000);
				driver.findElement(By.cssSelector("#tm_vo_search")).click();
				Thread.sleep(8000);
				driver.findElement(By.xpath("//div[@id='table_div']//div[@id='ext-gen90']//table/tbody/tr/td[1]/div/div[@class='x-grid3-row-checker'] ")).click();
				driver.findElement(By.xpath(".//*[@id='ext-comp-1003']/tbody/tr/td[2]")).click();
				driver.close();
				driver.switchTo().window(homePage);
		 End of code for Select Site with binoculars*/


		}
		}
		
		
	}

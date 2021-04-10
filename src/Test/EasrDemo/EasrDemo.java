package Test.EasrDemo;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import common.Constant;
import common.Utility;


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
			Thread.sleep(8000);
			Alert signOnAlert = driver.switchTo().alert();
			signOnAlert.accept();	
			
			Thread.sleep(15000);
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
        
        
      /*  driver.switchTo().frame(frame1);
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
		
		WebElement SAVE_THEN_NEXT = driver.findElement(By.xpath("//input[@title='Shortcut: ALT+N']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SAVE_THEN_NEXT );
		SAVE_THEN_NEXT.click();
        
		System.out.println("Clicked on SAVE_THEN_NEXT button");*/
		
        //Utility.switchToDefaultContent();
       
        
                
        Utility.driver = driver;
        driver.switchTo().defaultContent();
        
        Utility.switchTo("xpath", "//frame[@name='navigate']", "frame");
		Utility.clickElement("xpath", Constant.TESTSIMULATOR);	
		Utility.waitTime("sleep", 7000);
		Utility.switchWindow();
		Utility.maximizeWindow();
		Utility.waitTime("sleep", 5000);
		
		if(Utility.pageTitle().contains("Enterprise Single Sign"))
		{
			Utility.enterText("id", Constant.USERID, "GHOSSH5");
			Utility.enterText("id", Constant.PASSWORD, "TheGreen@rr0w");
			Utility.clickElement("xpath", Constant.SIGNON);
			Thread.sleep(2000);
			Utility.acceptAlert();	
			Utility.waitTime("sleep", 3000);
		}
		
		
		String pgTitle = Utility.pageTitle();
		
		System.out.println(pgTitle);
		if(pgTitle.contains("QA Test"))
        
		{	System.out.println("at Simulator");

		Utility.clickElement("xpath", Constant.FOC);
		Thread.sleep(5000);

		WebElement dateFld = driver.findElement(By.xpath(Constant.FOCDD));
		dateFld.click();
		WebElement calenderTable = driver.findElement(By.xpath(".//div[@id='calendarSelector']/table/tbody"));
		String title = "";
		List <WebElement> Calrow = calenderTable.findElements(By.tagName("tr"));
		int index=0;
		boolean selectDate = false;
		for(WebElement row : Calrow)
		{
			if(selectDate)
				break;
			index++;
			List <WebElement> calCol = row.findElements(By.tagName("td"));
			for(WebElement col : calCol)
			{
				if(selectDate)
					break;
				if(col.getAttribute("class").contains("valid month today"))
				{
					title = col.getAttribute("title");
					if(!("Saturday".contains(title) || "Sunday".contains(title)))
					{
						col.click();
						selectDate = true;
					}
					else
					{
						switch (title) 
						{
						case "Saturday": List <WebElement> calCol2 = Calrow.get(index).
								findElements(By.tagName("td"));
						for(WebElement col2 : calCol2)
						{
							if(col2.getAttribute("title").equals("Tuesday"))
							{
								col2.click();
								selectDate = true;
								break;
							}
						}
						break;

						case "Sunday": 
							for(WebElement col1 : calCol)
							{
								if(col1.getAttribute("title").equals("Tuesday"))
								{
									col1.click();
									selectDate = true;
									break;
								}
							}

							break;	
						default:
							break;
						}
					}
				}
			}
		}

			Utility.clickElement("xpath", Constant.GETFOCCIRCUITS);
			focCircuits("//table[@class='standardForm']/child::tbody/tr");
			Utility.clickElement("xpath", Constant.EASRSUBMIT);
			System.out.println( " FOC Processed successfully with your defaulted data");
			
			//Utility.enterText("xpath", Constant.FOCDD, "05312016");
			
			Thread.sleep(10000);
			cnr("CNR");
		}

		driver.close();
	}
	
	
	public static void focCircuits(String target)
	{
		System.out.println("Enter FOC circuit");
		WebElement table = Utility.findElement("xpath", target);
		List<WebElement> allrows = table.findElements(By.xpath("//input[contains(@name,'focckr')]"));
		int len = allrows.size();
		System.out.println(len);
		String cells_content_3 = null;
		int index = -1;
		for(int x=0; x<len; x++)
		{
			WebElement cell = allrows.get(x);
			cells_content_3 = new String(cell.getAttribute("value"));
			System.out.println(cells_content_3);
			if(cells_content_3.contains("N10") || cells_content_3.contains("E")||cells_content_3.contains("D"))
			{
				index = x;		
				Utility.enterText("xpath", "//input[contains(@name,'cctDetailId["+index+"].focecckt')]", cells_content_3+"12345678");
			}
		}			
	}
	
	
	public static void cnr(String value)
	{
		try
		{
			String cnr = Utility.makeXpath(Constant.EASRCHECKFIELD, value);
			Utility.clickElement("xpath", cnr);
			Utility.clickElement("xpath", Constant.CNRA);
			Utility.clickElement("xpath", Constant.EASRSUBMIT);
			System.out.println(value+ " Processed successfully with your defaulted data");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	
	}



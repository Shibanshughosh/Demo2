package com.shiv.TourUtility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FlightBooking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		File file = new File("D:/workspace/Selenium_Beginners/TicketBookingDemo.properties");
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
		WebElement homePg = driver.findElement(By.xpath(".//*[@id='chf_header']/div[1]/div/div[1]/p/a[1][@class='chf_flL chf_logopart']"));
		if(homePg.isDisplayed()){
			System.out.println("You are at homepage");
		}
		else {System.out.println("You are not at Home Page");
		driver.close();
		}		
		
		driver.findElement(By.xpath(".//*[@id='ssologinlink']/strong[text()='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(prop.getProperty("UID"));
		driver.findElement(By.xpath(".//*[@id='password_text']")).sendKeys(prop.getProperty("PWD"));
		driver.findElement(By.xpath(".//*[@id='login_btn']")).click();
		
		Thread.sleep(2000);
		
		WebElement login = driver.findElement(By.xpath(".//*[@id='ssologinlink']/strong[contains(text(),'Shibanshu')]"));
		if(login.isDisplayed()){
			System.out.println("Login Successful");
		}
		else {System.out.println("Unsuccessful Login");
		driver.close();
		}		
		
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='widget_row']/div[1]/div/div[3]/div/div[2]/div[1]/a[contains(text(),'Domestic')]")));
		
		System.out.println("Domestic identified");
		myDynamicElement.click();
		//driver.findElement(By.xpath(".//*[@id='widget_row']/div[1]/div/div[3]/div/div[2]/div[1]/a[contains(text(),'Domestic')]")).click();
		//driver.findElement(By.xpath(".//*[@id='widget_row']/div[1]/div/div[3]/div/div[2]/div[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='round_trip_button1']/span")).click();
		WebElement from = driver.findElement(By.xpath(".//*[@id='from_typeahead1']"));
		from.clear();
		from.sendKeys(prop.getProperty("FROM"));
		driver.findElement(By.xpath(".//*[@id='one_round_default']/div/div[1]/div/div[1]/span/span/div[1]/span/div/p")).click();
		from.sendKeys(Keys.TAB);
		
		WebElement to =driver.findElement(By.xpath(".//*[@id='to_typeahead1']"));
		to.sendKeys(prop.getProperty("TO"));
		driver.findElement(By.xpath(".//*[@id='one_round_default']/div/div[3]/div/div[1]/span/span/div[1]/span/div/p")).click();
		
		driver.findElement(By.xpath(".//*[@id='start_date_sec']/span[2]")).click();
		WebElement startMonth = driver.findElement(By.xpath("//div[contains(@class,'datepicker-group-first')]//span[contains(@class,'month')]"));
		String sm = CalenderSelect.selectCalendarDate(driver,startMonth,prop,"STARTMONTH","DEPARTUREDATE");
		System.out.println("Start "+sm);
		
		driver.findElement(By.xpath(".//div/a[@id='return_date_sec']/span[@class='glyphicon glyphicon-calendar flL']")).click();
		WebElement returnMonth = driver.findElement(By.xpath("//div[contains(@class,'datepicker-group-last')]//span[contains(@class,'month')]"));
		String rm =CalenderSelect.selectCalendarDate(driver, returnMonth, prop, "RETURNMONTH", "RETURNDATE");
		System.out.println("Return "+rm);
		
		driver.findElement(By.xpath(".//div[@class='modify_button_row clearfix']//a[@id='flights_submit']")).click();
		
		
		driver.close();
	}
}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class JavaSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	File file = new File("D:/workspace/Selenium_Beginners/JavaSearch.properties");
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
	
	WebElement pgTitle = driver.findElement(By.xpath("//div[1]/h1/a/img[@alt='tutorialspoint']"));
	if(pgTitle.isDisplayed()){
		System.out.println("You are at homepage");
	}
	else {System.out.println("You are not at Home Page");
	driver.close();
	}
	
	WebElement tuLibrary = driver.findElement(By.xpath(".//*[@id='liTL']/a/span"));
	tuLibrary.click();
	//Java Technologies drop-down link
	driver.findElement(By.xpath(".//*[@id='top-sub-menu']/div[1]/a[5]/div")).click();
	
	WebElement catTitle = driver.findElement(By.xpath(".//*[@id='content']//*[text()='Java Tutorials']"));
	
	if(catTitle.isDisplayed()){
		System.out.println("You are at Java Technologies search");
	}
	else {System.out.println("You are not at Java Search Page");
	driver.close();
	}
	
	WebElement lnjava = driver.findElement(By.xpath(".//div/a/img[@alt='Learn Java']"));
	Actions action = new Actions(driver);
	action.moveToElement(lnjava).click().perform();
		
	driver.close();
	
}
}
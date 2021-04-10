import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TourDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

File file = new File("D:/workspace/Selenium_Beginners/TourDemo.properties");
FileInputStream fileinput = null;

try{
fileinput = new FileInputStream (file);
}
catch(FileNotFoundException e){
	e.printStackTrace();
}


Properties prop = new Properties();

try {
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
WebElement pgTitle = driver.findElement(By.xpath("//img[@alt='Mercury Tours']")); 
if(pgTitle.isDisplayed()){
	System.out.println("You are at Home Page");
}

else {System.out.println("You are not at Home Page");
driver.close();
}

driver.findElement(By.xpath(".//tr/td[2][@class='mouseOut']/a")).click();
//driver.findElement(By.xpath(".//table//input[@name='firstName']")).sendKeys("fname");

driver.findElement(By.name("firstName")).sendKeys(prop.getProperty("FirstName"));
driver.findElement(By.name("lastName")).sendKeys(prop.getProperty("LastName"));
driver.findElement(By.name("phone")).sendKeys(prop.getProperty("Phone"));
driver.findElement(By.xpath(".//*[@id='userName']")).sendKeys(prop.getProperty("UserName"));
driver.findElement(By.name("address1")).sendKeys(prop.getProperty("Address1"));
driver.findElement(By.name("address2")).sendKeys(prop.getProperty("Address2"));
driver.findElement(By.name("city")).sendKeys(prop.getProperty("City"));
driver.findElement(By.name("state")).sendKeys(prop.getProperty("State"));
driver.findElement(By.name("postalCode")).sendKeys(prop.getProperty("PostalCode"));
WebElement wstate = driver.findElement(By.name("country"));
Select state = new Select(wstate);
state.selectByValue("92");
driver.findElement(By.id("email")).sendKeys(prop.getProperty("Email")); 
driver.findElement(By.xpath("//input[@type='password']")).sendKeys(prop.getProperty("Pwd"));
driver.findElement(By.xpath(".//tbody/tr[16]/td[2]/input[@type='password']")).sendKeys(prop.getProperty("Pwd"));

driver.findElement(By.xpath(".//tbody/tr[18]/td/input[@name='register']")).click();

WebElement reg = driver.findElement(By.xpath(".//td/img[contains(@src,'mast_register')]"));

if(reg.isDisplayed()){
	String register = "Thank you for registering";
	WebElement regpage = driver.findElement(By.xpath(".//tbody/tr[3]/td/p[2]/font"));

		if (regpage.getText().trim().contains(register)){
				System.out.println(" User registered successfully");
		}
		else {
				System.out.println(" User is not registered");
			}
}
else {System.out.println(" User is not registered");
}

driver.findElement(By.xpath(".//tbody/tr[3]/td/p[2]/font/a[1]")).click();

WebElement signon = driver.findElement(By.xpath(".//tbody/tr[1]/td/img[contains(@src,'mast_signon')]"));

if(signon.isDisplayed()){
	WebElement signonpage = driver.findElement(By.xpath(".//tbody/tr[3]/td/p/font/b"));
	if(signonpage.getText().contains("Welcome back to")){
		System.out.println("User on sign-on page");
		
		driver.findElement(By.xpath(".//tbody/tr[1]/td[2]/input[@name='userName']")).sendKeys(prop.getProperty("UserName"));
		driver.findElement(By.name("password")).sendKeys(prop.getProperty("Pwd"));
		driver.findElement(By.xpath(".//tbody/tr[4]/td/input[@value='Login']")).click();
	}
	else {System.out.println("Not sign-on page");
	}
}


// Without properties File
/*driver.get("http://newtours.demoaut.com/mercurywelcome.php");
WebElement pgTitle = driver.findElement(By.xpath("//img[@alt='Mercury Tours']")); 
if(pgTitle.isDisplayed()){
	System.out.println("You are at Home Page");
}

else {System.out.println("You are not at Home Page");
driver.close();
}

driver.findElement(By.xpath(".//tr/td[2][@class='mouseOut']/a")).click();
//driver.findElement(By.xpath(".//table//input[@name='firstName']")).sendKeys("fname");

driver.findElement(By.name("firstName")).sendKeys("fname");
driver.findElement(By.name("lastName")).sendKeys("lname");
driver.findElement(By.name("phone")).sendKeys("9999654333");
driver.findElement(By.xpath(".//*[@id='userName']")).sendKeys("testuser@gmail.com");
driver.findElement(By.name("address1")).sendKeys("House No - 7, Sector 8");
driver.findElement(By.name("address2")).sendKeys("Dalhousie Square");
driver.findElement(By.name("city")).sendKeys("Kolkata");
driver.findElement(By.name("state")).sendKeys("West Bengal");
driver.findElement(By.name("postalCode")).sendKeys("752001");
WebElement wstate = driver.findElement(By.name("country"));
Select state = new Select(wstate);
state.selectByValue("92");
driver.findElement(By.id("email")).sendKeys("testuser@gmail.com"); 
driver.findElement(By.xpath("//input[@type='password']")).sendKeys("testpwd");
driver.findElement(By.xpath(".//tbody/tr[16]/td[2]/input[@type='password']")).sendKeys("testpwd");

driver.findElement(By.xpath(".//tbody/tr[18]/td/input[@name='register']")).click();

WebElement reg = driver.findElement(By.xpath(".//td/img[contains(@src,'mast_register')]"));

if(reg.isDisplayed()){
	String register = "Thank you for registering";
	WebElement regpage = driver.findElement(By.xpath(".//tbody/tr[3]/td/p[2]/font"));

		if (regpage.getText().trim().contains(register)){
				System.out.println(" User registered successfully");
		}
		else {
				System.out.println(" User is not registered");
			}
}
else {System.out.println(" User is not registered");
}

driver.findElement(By.xpath(".//tbody/tr[3]/td/p[2]/font/a[1]")).click();

WebElement signon = driver.findElement(By.xpath(".//tbody/tr[1]/td/img[contains(@src,'mast_signon')]"));

if(signon.isDisplayed()){
	WebElement signonpage = driver.findElement(By.xpath(".//tbody/tr[3]/td/p/font/b"));
	if(signonpage.getText().contains("Welcome back to")){
		System.out.println("User on sign-on page");
		
		driver.findElement(By.xpath(".//tbody/tr[1]/td[2]/input[@name='userName']")).sendKeys("testuser@gmail.com");
		driver.findElement(By.name("password")).sendKeys("testpwd");
		driver.findElement(By.xpath(".//tbody/tr[4]/td/input[@value='Login']")).click();
	}
	else {System.out.println("Not sign-on page");
	}
}

	// Without Properties file */

driver.close();
driver.quit();


	}
}



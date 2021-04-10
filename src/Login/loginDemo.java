package Login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginDemo {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.ie.driver", "D:/SeleniumFiles/IEDriverServer.exe");
		
		WebDriver driver = new InternetExplorerDriver();
		//WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://bgwqtcuat.vzbi.com/IONSWeb/login.do");
			
		WebElement vtitle=driver.findElement(By.xpath("//*[text()='Verizon Enterprise Login ']"));
		if(vtitle.isDisplayed())
		{	
		  /*WebElement userid=driver.findElement(By.id("USERID"));
		   userid.sendKeys("z832295");*/
		  driver.findElement(By.id("USERID")).sendKeys("z832295");
		  WebElement pswd=driver.findElement(By.name("PASSWORD"));
		   pswd.sendKeys("Chocol@te31");
		  WebElement subbtn=driver.findElement(By.xpath("//span[text()='Sign-On']"));
		   subbtn.click();
		  }
		  else
		  {
		   System.out.println("Page not displayed");
		    }

		  WebDriverWait wait = new WebDriverWait(driver, 05);
		    wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert=driver.switchTo().alert();
		  System.out.println(alert.getText());
		   alert.accept();
			
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 	
		 // WebElement minz=driver.findElement(By.xpath("//div[contains(@qtip,'Click to')]"));
		 WebElement minz=driver.findElement(By.id("ext-gen41"));
		    minz.click();
		    Thread.sleep(1000);
		    WebElement aord=driver.findElement(By.xpath("//li/a[text()='Add Order']"));
		    aord.click();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		    WebElement pframe=driver.findElement(By.name("Add Order")); 
		    System.out.println(driver.switchTo().frame(pframe).getTitle());
		    Thread.sleep(1000);
		    SiteSearch.siteSh();
		    driver.close();
	}

}

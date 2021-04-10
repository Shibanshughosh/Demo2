import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
/*import org.openqa.selenium.htmlunit.HtmlUnitDriver;*/
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		Proxy proxy=new Proxy();
		proxy.setProxyType(Proxy.ProxyType.PAC);
		proxy.setProxyAutoconfigUrl("http://10.203.7.170/proxy.pac");
		DesiredCapabilities capabilities =new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY,proxy);
		WebDriver driver = new FirefoxDriver(capabilities);
		driver.manage().window().maximize();
		String baseURL = "http://www.teachmeselenium.blogspot.in/p/automation-practice.html";
		driver.get(baseURL);
		WebElement firstNm = driver.findElement(By.id("firstname"));
		firstNm.sendKeys("Testnm");
		/*driver.findElement(By.id("lastname")).sendKeys("lastnm");*/
		driver.findElement(By.xpath("//input[@class='lastname']")).sendKeys("lastnm");
		System.out.println("Firstname and Lastname entered");
		driver.findElement(By.xpath("//input[@value='Male']")).click();
		System.out.println("Gender is selected");
		driver.findElement(By.xpath("//input[@value='vbscript']")).click();
		System.out.println("Language is selected");
		Select age = new Select(driver.findElement(By.xpath("//select[@name='age']")));
		age.selectByValue("30 to 39");
		System.out.println("Age is selected");
		driver.findElement(By.id("submit_htmlform")).click();
		System.out.println("Form is submitted");
		
		driver.findElement(By.partialLinkText("Click Me to")).click();
		WebDriverWait wait = new WebDriverWait(driver, 05);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert=driver.switchTo().alert();
		System.out.println("Alert Message '/n'"+ alert.getText()+ "'/n'");
		alert.accept();
				
		WebElement pgTitle = driver.findElement(By.xpath("//*[text()='Teach me Selenium']"));
		
		if (pgTitle.isDisplayed())
			{ System.out.println("Home Page Displayed");
			}
		
		else {
			   System.out.println("Page not displayed");
	    } 
				
		driver.close();
				
		/*driver.close();
	
		/*HtmlUnitDriver unitDriver = new HtmlUnitDriver();
		unitDriver.get("http://google.co.in");
		System.out.println("Title of the page is -> " + unitDriver.getTitle());
		WebElement searchBox = unitDriver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium");
		WebElement button = unitDriver.findElement(By.name("gbqfba"));
		button.click();
		System.out.println("Title of the page is -> " + unitDriver.getTitle());
		*/
		
		
	}

}

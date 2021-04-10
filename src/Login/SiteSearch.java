package Login;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteSearch {
	
	private static WebDriver driver=null;
	
	public static void siteSh() throws Exception{
	
	String defaultWindowId=driver.getWindowHandle();
    System.out.println("defaultWindowId is : " + defaultWindowId);
    Set<String> oldWndHandles=driver.getWindowHandles();
    System.out.println("oldWndHandles : " + oldWndHandles);
   
    WebElement siteA=driver.findElement(By.xpath("//a[contains(@onclick,'searchsiteForTpta')]"));  
        siteA.click();
   	    Thread.sleep(3000);    
    System.out.println("Site A");
        
    Set<String> newWndHandles;
    try 
    {
     for(int i=1;i<=5;i++) 
    {
      newWndHandles = driver.getWindowHandles(); 
      System.out.println("new handles " + newWndHandles);
      if (newWndHandles.size() > oldWndHandles.size()) 
     {
	   break;
      } 
      else 
     {
       Thread.sleep(2000);
       }
      }
     }
	    catch (InterruptedException e) 
    {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }

    Boolean wndFound = false;

    newWndHandles = driver.getWindowHandles();
    System.out.println("Size of handle set: " + newWndHandles.size());

    for (String wndHandle : newWndHandles) 
    {
	     System.out.println(wndHandle);
     if (!wndHandle.contentEquals(defaultWindowId)) 
     {
      driver.switchTo().window(wndHandle);
      String wndTitle = driver.getTitle();
      System.out.println(wndTitle);
      wndFound = true;
      break;
      }
     }
    if (!wndFound) 
    {
      System.out.println("Site Search window could not be found");
     }
    WebDriverWait wait = new WebDriverWait(driver, 05);
    wait.until(ExpectedConditions.elementToBeClickable(By.id("tm_lo_siteCodeFandE")));

    WebElement boxFNEcode = driver.findElement(By.id("tm_lo_siteCodeFandE"));
	       boxFNEcode.sendKeys("HSTN.POP");
       boxFNEcode.submit();
   	    
    WebElement siteCheck=driver.findElement(By.className("x-grid3-row-checker"));
       siteCheck.click();
      
    WebElement siteSel=driver.findElement(By.id("ext-comp-1003"));
       siteSel.click();
    	        
    driver.switchTo().window(defaultWindowId);
    System.out.println("current window handle : " + driver.getWindowHandle());
    
    Thread.sleep(3000);
    System.out.println(driver.switchTo().frame(0).getTitle());
    
    WebElement telcoA=driver.findElement(By.id("n0.tm_ao_sitea_telco"));
    Select selTelA=new Select(telcoA);
      selTelA.selectByIndex(1);

}
}

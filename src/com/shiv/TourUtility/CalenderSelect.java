package com.shiv.TourUtility;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalenderSelect {

	public static String selectCalendarDate(WebDriver driver, WebElement month, Properties prop, String mth, String dt)
	{	
		//String month = "STARTMONTH";
		if (month.getText().equals(prop.getProperty(mth)))
		{ System.out.println("months are same");
			
			switch (mth) {
			case "STARTMONTH": WebElement smonth_cal = driver.findElement(By.xpath(".//div[@class='ui-datepicker-group ui-datepicker-group-first']//table/tbody"));
				List<WebElement> smonth_cal_row = smonth_cal.findElements(By.tagName("tr"));
				int srow_count = smonth_cal_row.size();
				for (int row=0; row<=srow_count; row++)
				{	List<WebElement> smonth_cal_col = smonth_cal_row.get(row).findElements(By.tagName("td"));
					int col_count = smonth_cal_col.size();
					for (int col=0;col<col_count;col++)
					{	String colText = smonth_cal_col.get(col).getText();
						if(colText.equals(prop.getProperty(dt)))
						{	smonth_cal_col.get(col).click();
							//break Searchdate1;
							return "Startmonth date selected in if";
						}
					}
				}
				break;
				
			case "RETURNMONTH":  
			WebElement rmonth_cal = driver.findElement(By.xpath(".//div[@class='ui-datepicker-group ui-datepicker-group-last']//table/tbody"));
			List<WebElement> rmonth_cal_row = rmonth_cal.findElements(By.tagName("tr"));
			int rrow_count = rmonth_cal_row.size();
			for (int row=0; row<=rrow_count; row++)
			{	List<WebElement> smonth_cal_col = rmonth_cal_row.get(row).findElements(By.tagName("td"));
				int col_count = smonth_cal_col.size();
				for (int col=0;col<col_count;col++)
				{	String colText = smonth_cal_col.get(col).getText();
					if(colText.equals(prop.getProperty(dt)))
					{	smonth_cal_col.get(col).click();
						//break Searchdate1;
						return "Returnmonth date selected in if";
					}
				}
			}
			break;

			default:
				break;
			}
		
		
			
		 }
		else
		{	System.out.println("different month");
			String sm = null;
			while (!month.getText().equals(prop.getProperty(mth))) {
				sm = month.getText();
				System.out.println(sm);
				switch (mth) 
				{					
					case "STARTMONTH": driver.findElement(By.xpath(".//div[@id='ui-datepicker-div']//span[contains(@class,'ui-icon ui-icon-circle-triangle-e')]")).click();
					System.out.println("Inside Switch");
					month = driver.findElement(By.xpath("//div[contains(@class,'datepicker-group-first')]//span[contains(@class,'month')]"));
					break;
					case "RETURNMONTH": driver.findElement(By.xpath(".//div[@id='ui-datepicker-div']//span[contains(@class,'ui-icon ui-icon-circle-triangle-e')]")).click();
					month = driver.findElement(By.xpath("//div[contains(@class,'datepicker-group-last')]//span[contains(@class,'month')]"));
					break;
					default:
					break;
				}
				
			}
			
			System.out.println("months are same now");
			WebElement smonth_cal = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/table/tbody"));
			List<WebElement> smonth_cal_row = smonth_cal.findElements(By.tagName("tr"));
			int row_count = smonth_cal_row.size();
			
			for (int row=0; row<=row_count; row++)
			{	List<WebElement> smonth_cal_col = smonth_cal_row.get(row).findElements(By.tagName("td"));
				int col_count = smonth_cal_col.size();
				for (int col=0;col<col_count;col++)
				{	String colText = smonth_cal_col.get(col).getText();
					if(colText.equals(prop.getProperty(dt)))
					{	smonth_cal_col.get(col).click();
						return "date selected in else";
					}
				}
			}
		}
			
		return "Date is not selected";
	}
	
}

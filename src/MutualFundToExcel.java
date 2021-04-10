import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class MutualFundToExcel {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		
		File file = new File("D:/workspace/Selenium_Beginners/MutualFundSearch.properties");
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
		System.out.println("I am here");
		WebElement homePg = driver.findElement(By.xpath(".//*[@class='first active']"));
		if(homePg.isDisplayed()){
			System.out.println("You are at homepage");
		}
		else {System.out.println("You are not at Home Page");
		driver.close();
		}		
		
		//		driver.findElement(By.xpath(".//*[@title='MUTUAL FUNDS']")).click();
		String xpath_mf = "//a[contains(text(),'Mutual Funds')]";
		//		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		driver.findElement(By.xpath(xpath_mf)).click();
		driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		WebElement mfHome = driver.findElement(By.xpath(".//*[@id='mc_mainWrapper']/div[2]/div[1]/div/div/div/div/ul/li[1]/a[@title='Home']"));
		if (mfHome.isDisplayed()){

			System.out.println("You are at Mutual Fund homepage");
		}
		else {System.out.println("You are not at MF Home Page");
		driver.close();
		}		
		
		//Click on Large Cap and sort the Rating
	//	driver.findElement(By.xpath(".//*[@id='mfBox1']/div[1]/div[1]/div[1]/div[1]/a/b[contains(text(),'LARGE CAP')]")).click();
	//	driver.findElement(By.xpath(".//*[@id='tCont1']/div[2]/table/tbody/tr[1]/th[2]/a/span/strong[text()='Crisil Rank']")).click();
		
		String cap = "DIVERSIFIED";
		
		switch (cap) {
		case "LARGE CAP": driver.findElement(By.xpath(".//div[@id='mfBox1']//a/b[contains(text(),'LARGE CAP')]")).click();
		break;
		case "SMALL & MID CAP": driver.findElement(By.xpath(".//div[@id='mfBox1']//a/b[contains(text(),'SMALL & MID CAP')]")).click();
		break;
		case "DIVERSIFIED": driver.findElement(By.xpath(".//div[@id='mfBox1']//a/b[contains(text(),'DIVERSIFIED EQUITY')]")).click();
		break;
		case "ELSS": driver.findElement(By.xpath(".//div[@id='mfBox1']//a/b[contains(text(),'DIVERSIFIED EQUITY')]")).click();
		break;		
		default:
			break;
		}
		
		//Locate the table
		WebElement mytable = driver.findElement(By.xpath(".//*[@id='tCont1']/div[2]/table/tbody"));
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		
		//Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook(); 
	      //Create a blank sheet
	      XSSFSheet spreadsheet = workbook.createSheet(cap);		
	  		
		//Calculate number of rows
		int rows_count = rows_table.size();
		int i=0;			
		
		//Capture the heading
		List<WebElement> Columns_heading = rows_table.get(0).findElements(By.tagName("th"));
		int columnshd_count = Columns_heading.size();
		XSSFRow excelRowHeader = spreadsheet.createRow(i++);
		//Loop will execute till the last cell of that specific row. 
		int j=0;
		for (int columnhd=0; columnhd<columnshd_count; columnhd++){
			//To retrieve text from that specific cell.
			String celhdtext = Columns_heading.get(columnhd).getText(); 
				
			if(celhdtext.length()>2){
				Cell excelCellHeader = excelRowHeader.createCell(j++);
				excelCellHeader.setCellValue(celhdtext);
			}   
					
		} // End of heading 
		
				
		
		//Loop will execute till the last row of table. 
		for (int row=1; row<rows_count; row++){ 
				
		//To locate columns(cells) of that specific row. 
		List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
		
		//To calculate no of columns(cells) In that specific row. 
		int columns_count = Columns_row.size(); 
		
		if(Columns_row.size()>2){ 
		String celrank = Columns_row.get(1).getText();
	
		//System.out.println(celrank);
			if(celrank.equals("Rank 1") || celrank.equals("Rank 2") || celrank.equals("Rank 3")){
				System.out.println("Rank of "+Columns_row.get(0).getText() + " is " + Columns_row.get(1).getText());
		
				XSSFRow excelRow = spreadsheet.createRow(i++);
				//Loop will execute till the last cell of that specific row. 
				int k=0;
				for (int column=0; column<columns_count; column++){
					//To retrieve text from that specific cell.
					String celtext = Columns_row.get(column).getText(); 
						
					if(celtext.length()>2){
						Cell excelCell = excelRow.createCell(k++);
						excelCell.setCellValue(celtext);
					}   
					
					//	System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
	 	
				} 
				
				System.out.println("--------------------------------------------------"); 	
			}
		}
		
		}// Rowcount for loop ends
		
		
	      XSSFCellStyle style1 = workbook.createCellStyle();
	      style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	      style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	      style1.setBorderRight(XSSFCellStyle.BORDER_HAIR);
	      style1.setBorderLeft(XSSFCellStyle.BORDER_HAIR);
	      style1.setBorderTop(XSSFCellStyle.BORDER_HAIR);
	      style1.setBorderBottom(XSSFCellStyle.BORDER_HAIR);
		
		 //Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("MutualFund.xlsx"));
		workbook.write(out);
		out.close();
		workbook.close();
		System.out.println("MutualFund.xlsx written successfully" );
		
		driver.close();
		
	}

}

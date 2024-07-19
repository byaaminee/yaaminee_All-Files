package com.Login.seleniumExample;


import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Finalassigntwo {

	WebDriver driver;
	@BeforeMethod
	public void beforeMethod() {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

	}

	@Test
	public void readfromapp() throws IOException, InterruptedException{

		driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		Thread.sleep(2000);	
		String excelFilePath = System.getProperty("C:\\Users\\byaam\\eclipse\\Login\\Excel\\Quebec.xlsx");



		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[198]")).click();  
		
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);
		WebElement table = driver.findElement(By.xpath("(//table)[2]"));
		
		try {
			
			List<WebElement> rows = table.findElements(By.xpath("(//tbody)[2]//tr"));

			// Iterate through each row (starting from 1 to skip header if present)
			for (int i = 0; i < rows.size(); i++) {
				WebElement row = rows.get(i);
				List<WebElement> cells = row.findElements(By.tagName("td"));

				for (int c = 0; c< cells.size(); c++) {
				// Extract data from each cell
				String stringData = cells.get(c).getText();
				System.out.print(stringData + "\t");
				}
				
				System.out.println();
			}

		
		}
				catch (NullPointerException e) {
				System.out.println("Null pointer exception: " + e.getMessage());
			} 
				catch (Exception e) {
					e.printStackTrace();
				}													
	
	}		
		
}

//FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//
//	workbook.write(outputStream);
//	String r=driver.findElement(By.xpath("(//tbody)[2]//tr")).getText();
//	XSSFSheet  sheet =  workbook.createSheet("Saint Basil");
//	XSSFRow ro = sheet.createRow(0);
//	XSSFCell cell = ro.createCell(0);
//	cell.setCellValue(r);

//catch (NullPointerException e) {
//	System.out.println("Null pointer exception: " + e.getMessage());
//} 


	
	

		
	
//


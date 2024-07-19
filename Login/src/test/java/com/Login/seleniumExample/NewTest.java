package com.Login.seleniumExample;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class NewTest {
	private WebDriver driver;
	 XSSFWorkbook workbook;
	 XSSFSheet sheet;
     String excelFileName = "C:\\Users\\byaam\\eclipse\\Login\\Excel\\Quebec.xlsx";
	//String newSheetName = "SaintBasil";
	int sheetIndex;

	@BeforeClass
	
	public void setUp() {
		

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver = new ChromeDriver(ops);
	
		driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT");
		Duration duration = Duration.ofSeconds(30);
		driver.manage().timeouts().implicitlyWait(duration);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}	

	}

	@Test
	public void testTableToExcel() {
	
		//driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[190]")).click();  

		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);
		WebElement table = driver.findElement(By.xpath("(//table)[2]"));

		List<WebElement> rows = table.findElements(By.xpath("(//tbody)[2]//tr"));
		workbook = new XSSFWorkbook(); 
		sheetIndex=1;
	     String sheet1 = "SaintBasil" + sheetIndex;
	        while (sheetIndex<10) {
	            sheet1 = "SaintBasil" + sheetIndex;
	            sheet = workbook.createSheet(sheet1);
	            sheetIndex++;
	        }

	        // Create new sheet
	        
	        
		  
		//Sheet SaintBasil = workbook.createSheet(newSheetName);

		// Iterate through each row (starting from 1 to skip header if present)
		for (int i = 3; i < rows.size()-3; i++) {
			WebElement row = rows.get(i);
			List<WebElement> cells = row.findElements(By.tagName("td"));

			// Extract data from each cell
			String stringData = cells.get(0).getText();
			String integerData = cells.get(1).getText();
			double doubleData = Double.parseDouble(cells.get(2).getText());
			System.out.print(stringData + "\t" );
			System.out.print(integerData + "\t" );
			System.out.print(doubleData + "\t" );
			
			// Write data to Excel
			Row excelRow = sheet.createRow(i);
			Cell cell1 = excelRow.createCell(0);
			cell1.setCellValue(stringData);
			Cell cell2 = excelRow.createCell(1);
			cell2.setCellValue(integerData);
			Cell cell3 = excelRow.createCell(2);
			cell3.setCellValue(doubleData);
		
		}
	

	try {
		FileOutputStream fos = new FileOutputStream(excelFileName);
		workbook.write(fos);
		workbook.close();
		System.out.println("Excel file has been written successfully.");
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}
		
		@AfterClass
		public void tearDown() {
			// Close the WebDriver
			if (driver != null) {
				driver.quit();
			}
		}
		// Write Excel file and close WebDriver
		
		//driver.quit();
	}

	

	





package com.Login.seleniumExample;

import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
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

import com.aventstack.extentreports.Status;

//import FinalAssignment.ExtentReportFinal;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Testtwo {
	private WebDriver driver;
	private Workbook workbook;
	private XSSFSheet sheet;
	private String excelFileName = "C:\\Users\\byaam\\eclipse\\Login\\Excel\\Quebec.xlsx";
	//    		
	//    @BeforeMethod
	//	public void beforeMethod() {
	//
	//		ChromeOptions ops = new ChromeOptions();
	//		ops.addArguments("--disable-notifications");
	//
	//		driver = new ChromeDriver(ops);
	//		driver.manage().window().maximize();
	//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	//
	//	}
	@BeforeClass
	public void setUp() {
		// Set path to your ChromeDriver executable
//		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver--win64\\chromedriver-win64\\chromedriver.exe");
//
//		ChromeOptions ops = new ChromeOptions();
//		ops.addArguments("--disable-notifications");
//		driver = new ChromeDriver(ops);
		driver = new ChromeDriver();
		
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
	public void testTableToExcel() throws InterruptedException, IOException {
		// Assuming the table has 11 rows and 3 columns
		workbook = new XSSFWorkbook();
		sheet = (XSSFSheet) workbook.createSheet("Redemption");
		FileOutputStream fos = new FileOutputStream(excelFileName);
		
		// Replace driver.get(...) with:
		driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT");
		Duration duration = Duration.ofSeconds(30);
		driver.manage().timeouts().implicitlyWait(duration);

		driver.findElement(By.xpath("//a[normalize-space()='English']")).click(); 
		driver.findElement(By.xpath("(//a)[197]")).click();  
		
		
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);
		WebElement table = driver.findElement(By.xpath("(//table)[2]"));
		List<WebElement> rows = table.findElements(By.xpath("(//tbody)[2]//tr"));

		// Iterate through each row (starting from 1 to skip header if present)
		for (int i = 0; i < rows.size(); i++) {
			WebElement row = rows.get(i);
			List<WebElement> cells = row.findElements(By.tagName("(td"));
			int size = cells.size();
			XSSFRow excelRow = sheet.createRow(i);
			for (int c = 0; c<size ; c++) {
				String dataVal = cells.get(c).getText();
				excelRow.createCell(c).setCellValue(dataVal);
			}
			
			//Thread.sleep(2000);
			
			// Extract data from each cell
//			String stringData = cells.get(0).getText();
//			int integerData = Integer.parseInt(cells.get(1).getText());
//			double doubleData = Double.parseDouble(cells.get(2).getText());
//
//			// Write data to Excel
//			Row excelRow = sheet.createRow(i);
//			Cell cell1 = excelRow.createCell(0);
//			cell1.setCellValue(stringData);
//			Cell cell2 = excelRow.createCell(1);
//			cell2.setCellValue(integerData);
//			Cell cell3 = excelRow.createCell(2);
//			cell3.setCellValue(doubleData);
		}
		try {
			fos = new FileOutputStream(excelFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		workbook.write(fos);
		workbook.close();
		fos.close();
	}

	@AfterClass
	public void tearDown() {
		// Write Excel file and close WebDriver
//		try (FileOutputStream fos = new FileOutputStream(excelFileName)) {
//			workbook.write(fos);
//			workbook.close();
//			System.out.println("Excel file has been written successfully.");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//driver.quit();
	}
}

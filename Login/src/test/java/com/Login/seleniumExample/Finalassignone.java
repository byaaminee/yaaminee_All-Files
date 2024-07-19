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

public class Finalassignone {

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
		driver.findElement(By.xpath("(//a)[197]")).click();   
		//	
		WebElement iframetable = driver.findElement(By.xpath("//div[@id='apex_dialog_1']//iframe")); // Locate the iframe element
		driver.switchTo().frame(iframetable);
		WebElement  table=driver.findElement(By.xpath("(//table)[2]"));
		List<WebElement> rows= driver.findElements(By.xpath("(//tbody)[2]//tr"));
		System.out.println("Total no. of Rows: "+rows.size());
		int rowcount=rows.size();


		
		

		try {
			for (int i = 2; i <=rowcount; i++) {

				WebElement row = rows.get(i);
				List<WebElement> columns = row.findElements(By.xpath("(//tbody)[2]//tr//td"));
				System.out.println("Total no. of Elements in the Table: "+columns.size() + "\t");

				for (WebElement column : columns) {

					if (column != null) {
						System.out.print(column.getText().toString() + "\t");
					} else {
						System.out.println("Cell element not found.");
					}
                  
				}				
		
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
	

		




	
//   try {
//    String excelFilePath1 = System.getProperty("C:\\Users\\Uma\\eclipse-workspace\\Learning\\src\\test\\java\\Excel\\Quebec.xlsx");
//    String rowss=driver.findElement(By.xpath("(//tbody)[2]//tr")).getText();
//	FileOutputStream outputStream = new FileOutputStream(excelFilePath1);
//	XSSFWorkbook workbook=new XSSFWorkbook();
//	workbook.write(outputStream);
//	XSSFSheet  sheet =  workbook.createSheet("Redemption");
//	XSSFRow ro = sheet.createRow(0);
//	XSSFCell cell = ro.createCell(0);
//   }
//	catch (IOException e) {
//		System.out.println("Null pointer exception: " + e.getMessage());
//	} 	
	

	






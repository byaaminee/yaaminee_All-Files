package Selenium_Assignment_1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Sel_Assignment3 {
	
	WebDriver driver;
	JavascriptExecutor js;
	String excelFilePath = "C:\\Users\\byaam\\eclipse\\Login\\src\\test\\java\\SeleniumExcelFiles\\MaturityValCalc_2024.xlsx";
	FileInputStream file;
	FileOutputStream outputStream;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
  @Test
  public void calculate_Maturiy_Interest() throws InterruptedException {
	  
	  js.executeScript("window.scrollBy(0, 100)");
	  
	 try {
		file = new FileInputStream(excelFilePath);
		workbook = new XSSFWorkbook(file);
		outputStream = new FileOutputStream(excelFilePath);
		
		sheet = workbook.getSheet("maturityValCalculator");
		
		int rownum = sheet.getLastRowNum();
		int colnum = sheet.getRow(0).getLastCellNum();
		
		System.out.println("Number of rows in the excel sheet: " + rownum);
		System.out.println("Number of columns in the excel sheet: " + colnum);
		
		for(int row = 1; row <= rownum ; row ++ ) {
			XSSFRow rowVal = sheet.getRow(row);
			
				String principal = rowVal.getCell(0).toString().replace(".0", "");
				WebElement principalTxtBx = driver.findElement(By.xpath("//input[@id='principal']"));
				principalTxtBx.sendKeys(principal);
				System.out.print(principal + "\t");
				
				String roi = rowVal.getCell(1).toString();
				WebElement roiTxtBx = driver.findElement(By.xpath("//input[@id='interest']"));
				roiTxtBx.sendKeys(roi);
				System.out.print(roi + "\t");
				
				String periodVal = rowVal.getCell(2).toString().replace(".0", "");
				WebElement periodValTxtBx = driver.findElement(By.xpath("//input[@id='tenure']"));
				periodValTxtBx.sendKeys(periodVal);
				System.out.print(periodVal + "\t");
				
				
				String periodType = rowVal.getCell(3).toString();
				WebElement periodTypeCmboBox = driver.findElement(By.xpath("//select[@id='tenurePeriod']"));
				Select selectPeriodType = new Select(periodTypeCmboBox);
				selectPeriodType.selectByVisibleText(periodType);
				System.out.print(periodType + "\t");
				
				String frequency = rowVal.getCell(4).toString();
				WebElement frequencyCmboBox = driver.findElement(By.xpath("//select[@id='frequency']"));
				Select selectFrequency = new Select(frequencyCmboBox);
				selectFrequency.selectByVisibleText(frequency);
				System.out.print(frequency + "\t");
				
				System.out.println();
				
				//WebElement calButton = driver.findElement(By.xpath("//img[contains(@src,'btn_calcutate')]"));
//				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//				wait.until(ExpectedConditions.elementToBeClickable(calButton));
//				calButton.click();
				
				//Click the CALCULATE button
				WebElement calButton = driver.findElement(By.xpath("//a[contains(@onclick,'return getfdMatVal(this);')]"));
				js.executeScript("arguments[0].click();", calButton);
				Thread.sleep(5000);
				
				String maturityVal = driver.findElement(By.xpath("//span/strong")).getText();
				String interestEarnedFullTxt = driver.findElement(By.xpath("//span/em")).getText();
				String interestEarned = interestEarnedFullTxt.replace("Interest earned Rs.", "");
				System.out.println("Maturity Value = " + maturityVal + "\t" + "\t" + "Interest Earned = " + interestEarned);
			
			          
			    //Write maturity value and interest earned in the File			        	  
				rowVal.createCell(5).setCellValue(maturityVal);
				rowVal.createCell(6).setCellValue(interestEarned);
				
				
				//Clear the data
				WebElement clearButton = driver.findElement(By.xpath("//a[contains(@onclick,'javascript:reset_fdcalcfrm();')]"));
				js.executeScript("arguments[0].click();", clearButton);
				Thread.sleep(2000);	
				
				System.out.println();
				
		}
		
		//Write changes to the Excel File
		workbook.write(outputStream);
		System.out.println("\n" + "Maturity value and Interest Earned added to the Excel File!!!!");
		outputStream.close();
		file.close();
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  

  @BeforeClass
  public void beforeClass() {
	  
	  driver = new ChromeDriver();
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
	  js = (JavascriptExecutor) driver;
  }

  @AfterClass
  public void afterClass() {
	 // driver.quit();
	  
  }

}

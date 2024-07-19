package quebecBonds;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class TestCasesNew {
	WebDriver driver;
	String excelFilePath = "C:\\Users\\byaam\\eclipse\\yaaminee_Final_Project\\src\\test\\java\\SeleniumExcelFiles\\FinalExamExcel.xlsx";
	FileInputStream inputStream;
	FileOutputStream outputStream;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	JavascriptExecutor js;
	ExtentReportFinal erf = new ExtentReportFinal();
	WebDriverWait wait;
	ExcelCellStyles cellStyles;
	QuebecHomePage qhp;
	String sheetName;
	int tenderTblRowSize = 0;
	XSSFRow excelRow;
	XSSFCell cell;
	CellStyle cs;
	static Logger logger = Logger.getLogger(TestCasesNew.class);
	
  @Test (priority = 1)
  public void changeLanguage() throws InterruptedException, IOException {
	  qhp = new QuebecHomePage(driver);
	  Thread.sleep(2000);
	  
	  String methodName = new Exception().getStackTrace()[0].getMethodName();
	  String className = new Exception().getStackTrace()[0].getClassName();
	  
	  ExtentReportFinal.test = ExtentReportFinal.extent.createTest(methodName,"Change the language");
	  ExtentReportFinal.test.addScreenCaptureFromPath(ExtentReportFinal.captureScreenshot(driver, "beforeChangeLang"), "beforeChangeLang");
	  ExtentReportFinal.test.log(Status.INFO, "Change the language to English");
	  logger.info("Change the language to English");
	  qhp.selectLanguage("English");
	  ExtentReportFinal.test.addScreenCaptureFromPath(ExtentReportFinal.captureScreenshot(driver, "afterChangeLang"),"afterChangeLang");
	  boolean testLang = qhp.frenchLang.isEnabled();
	  
	  if (testLang) {
		  ExtentReportFinal.test.log(Status.PASS, "Successfully changed the lanaguage to English");
		  logger.info("Successfully changed the lanaguage to English");
	 }
	  else {
		  ExtentReportFinal.test.log(Status.FAIL, "System does not change the language to English"); 
		  logger.fatal("FAIL, System does not change the language to English");
	  }
	  
	 AssertJUnit.assertEquals(true, testLang);
  }
  
  @Test (priority = 2)
  public void findBondTableRows() throws InterruptedException, IOException {	  
	  String methodName = new Exception().getStackTrace()[0].getMethodName();
	  String className = new Exception().getStackTrace()[0].getClassName();
	  ExtentReportFinal.test = ExtentReportFinal.extent.createTest(methodName,"Validate winning bid for each Municipality/City under the 5 sub-tables-Bond category");
	  
	  wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	  
	  QuebecHomePage qhp = new QuebecHomePage(driver);
	  List <WebElement> tb = qhp.bondsTable.findElements(By.tagName("tbody"));
	  int noOfsmallTable = tb.size();
	  System.out.println("Total tables under 'Bond' category : " + noOfsmallTable);
	  
	  try {
		  	outputStream = new FileOutputStream(excelFilePath);
			workbook = new XSSFWorkbook();
	  
			//Getting only data from 5 small tables under the bond category. Starting from 1 since the 0th index table does not have bonds
			for (int i=1; i<=5 ; i++) {
				List<WebElement> rows = tb.get(i).findElements(By.tagName("tr"));
				int rowSize = rows.size();
				System.out.println("\n" + "Number of Municipalities/Cities under Bond Table" + i + " " + (rowSize-2) );
				System.out.println("------------------------------------------------");
				ExtentReportFinal.test.log(Status.INFO,"Number of Municipalities/Cities under Bond Table" + i + " = " + (rowSize-2) );
				logger.info("Number of Municipalities/Cities under Bond Table" + i + " = " + (rowSize-2));
				for (int x = 0; x< (rowSize-2); x++) {
					
					//since only 1 hyperlink given for each municipality name I am using findElement with 'a' tag 
					WebElement bond = rows.get(x).findElement(By.tagName("a"));
					sheetName = bond.getText();
					System.out.println (sheetName);
					wait.until(ExpectedConditions.elementToBeClickable(bond));
					//For me the element was not visible when I pass scrollIntoView method as empty. Therefore passed false since the element is present above the viewable point and worked as expected
					js.executeScript("arguments[0].scrollIntoView(false);", bond);
					//take screenprint
					ExtentReportFinal.test.log(Status.INFO, "Click on the " + bond.getText());
					ExtentReportFinal.test.addScreenCaptureFromPath(ExtentReportFinal.captureScreenshot(driver, sheetName),sheetName);
					logger.info("Click on the " + bond.getText() + " link in the bond category table" );
					Thread.sleep(2000);
					//bond.click(); 
					js.executeScript("arguments[0].click();", bond);
					
					//Create an excel tab with same name as shown in the link
					sheet = workbook.createSheet(sheetName);
					
					// Find all frames on the page
//				      List<WebElement> frames = driver.findElements(By.tagName("iframe"));
//				      System.out.println("Number of iFrames: " + frames.size());
					
					//Switch to frame by Index. Only one frame found
					driver.switchTo().frame(0);
					copyTenderPageDataToExcel();
					ExtentReportFinal.test.log(Status.PASS, "Added data to Excel for : " + sheetName);	
				}
				System.out.println("________________________________________________________________");
			}
			//Thread.sleep(2000);
	  }
	  
	  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  }
		
	  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  }
	  
	  finally {
		  workbook.write(outputStream);
		  workbook.close();
		  outputStream.close();	
		  ExtentReportFinal.test.log(Status.PASS, "Successfully added data to Excel and saved");
		  logger.info("Successfully added data to Excel and saved");
	  }
  }
  
  
  @BeforeClass
  public void beforeClass() {
	  
	  driver = new ChromeDriver();
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  driver.manage().window().maximize();
	  driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT::::");
	  ChromeOptions cop = new ChromeOptions();
	  cop.addArguments("--disable-notification");
	  cop.addArguments("--disable-infobars");
	  cop.addArguments("--enable-automation");
	  js = (JavascriptExecutor) driver;
	  erf.intializer();
	  PropertyConfigurator.configure("src\\test\\resources\\log4j.properties");
	  logger.info("User navigates to the Website successfully");
	  System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.Jdk14Logger");
  }

  @AfterClass
  public void afterClass() {
	  ExtentReportFinal.extent.flush();
	  driver.close();
  }
  
  
  public void copyTenderPageDataToExcel() throws IOException, InterruptedException{
	  	WebElement winTenderDsply = driver.findElement(By.xpath("//v[text() = 'WINNING TENDER']"));
		wait.until(ExpectedConditions.visibilityOf(winTenderDsply));
		Boolean result = winTenderDsply.isDisplayed();
		
		if (result) {
			ExtentReportFinal.test.log(Status.PASS, "System Successfully navigates to the WINNING TENDER page for : " + sheetName);
			logger.info("System Successfully navigates to the WINNING TENDER page for : " + sheetName);
		}
		
		else {
			ExtentReportFinal.test.log(Status.FAIL, "System does not navigated to the WINNING TENDER page upon clicking the " + sheetName + " link");
			logger.fatal("FAIL, System does not navigated to the WINNING TENDER page upon clicking the " + sheetName + " link");
		}
		
		//Take screenprint
		ExtentReportFinal.test.addScreenCaptureFromPath(ExtentReportFinal.captureScreenshot(driver,sheetName+"Tender"),sheetName+"Tender");
		Thread.sleep(1000);
		//Find the table.. Since there is no name for table, chosen the align attribute since there are 2 tables inside the iFrame
		WebElement table = driver.findElement(By.xpath("//table[@align='center']"));
		//WebElement table = driver.findElement(By.tagName("tbody"));
		List<WebElement> tblRows = table.findElements(By.tagName("tr"));
		tenderTblRowSize = tblRows.size();
		
		//Find columns in each row in the table and insert to excel
		for (int r = 0; r < tenderTblRowSize ; r++ ) {		
			excelRow = sheet.createRow(r);
			List<WebElement> tblCols = tblRows.get(r).findElements(By.tagName("td"));
			int eachRowColSize = tblCols.size();
				for (int c = 0; c < eachRowColSize ; c++) {
					String dataVal = tblCols.get(c).getText();
					cell = excelRow.createCell(c);
					
					//Set the cell value
					if (Pattern.matches("^[0-9]*\\.?[0-9]*",dataVal)){
						double dd = Double.parseDouble(dataVal);
						cell.setCellValue(dd);
					}
					else {
						cell.setCellValue(dataVal);
					}
				}	
		}
		
		cellStyles = new ExcelCellStyles(workbook);
		setupCellStyle();
		
		driver.switchTo().parentFrame();
		//Close the Tender screen
		driver.findElement(By.xpath("//span[contains(@class,'closethick')]")).click();
}
  
  public void setupCellStyle()throws IOException, InterruptedException {
		//Merging cells in the excel similar to the format shown in the website table
		sheet.addMergedRegion(new CellRangeAddress (0,0,0,2));
		sheet.addMergedRegion(new CellRangeAddress (1,1,1,2));
		sheet.addMergedRegion(new CellRangeAddress (tenderTblRowSize-1,tenderTblRowSize-1,1,2));
		sheet.addMergedRegion(new CellRangeAddress (tenderTblRowSize-2,tenderTblRowSize-2,1,2));
		
		//Creating below unused - cells in order to add borders in the Excel (just for display purpose)
		cs = cellStyles.getCellStyle("Double line border");
		sheet.getRow(0).createCell(1).setCellStyle(cs);
		sheet.getRow(0).createCell(2).setCellStyle(cs);
		sheet.getRow(1).createCell(2).setCellStyle(cs);
		sheet.getRow(tenderTblRowSize-2).createCell(2).setCellStyle(cs);
		sheet.getRow(tenderTblRowSize-1).createCell(2).setCellStyle(cs);
		
		//Set up border/font:bold/decimals
		for (int r = 0; r< tenderTblRowSize ; r++) {
			  for (int c = 0; c< 3 ; c++){
				  if (r==0 || (r==1 && c == 0) || (r==2 && c == 0) || (r==(tenderTblRowSize-1) && c == 0) || ((r==tenderTblRowSize-2) && c == 0) || (r==tenderTblRowSize-3) ) {
						//Created separate Class for different CellStyles using HashMap
						cs = cellStyles.getCellStyle("Double line border & Bold font");
						sheet.getRow(r).getCell(c).setCellStyle(cs);	
				  }
					  
				  else if(r==1 & c==1) {
						cs = cellStyles.getCellStyle("Double line border & Decimal format");
						sheet.getRow(r).getCell(c).setCellStyle(cs);	
				  }
				  else {
						cs = cellStyles.getCellStyle("Double line border");
						sheet.getRow(r).getCell(c).setCellStyle(cs);	
				  }	  
			  }
		  }
		
		//Border and setting "Annual coupon rate" data to 2 decimals values
		for (int r = 3 ; r < tenderTblRowSize-3 ; r++ ) {
			cs = cellStyles.getCellStyle("Double line border & Decimal 2 digits");
			sheet.getRow(r).getCell(2).setCellStyle(cs);
		}
		
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);  
}
}

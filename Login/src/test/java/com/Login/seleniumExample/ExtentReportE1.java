package com.Login.seleniumExample;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportE1 {
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	WebDriver driver;
	
	public void initializer() {
		sparkReporter =  new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/extentSparkReport.html");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Test Execution Report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);		
	}
	
	public static String captureScreenshot(WebDriver driver) throws IOException {
		String FileSeparator = System.getProperty("file.separator"); // "/" or "\"
		String Extent_report_path = "."+FileSeparator+"Reports"; // . means parent directory
		File Src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String Screenshotname = "screenshot"+Math.random()+".png";
		File Dst = new File(Extent_report_path+FileSeparator+"Screenshots"+FileSeparator+Screenshotname);
		FileUtils.copyFile(Src, Dst);
		String absPath = Dst.getAbsolutePath();
		//System.out.println("Absolute path is:"+absPath);
		return absPath;
	}
	
	@Test (priority = 1)
	public void loginPage() throws IOException {
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		String className = new Exception().getStackTrace()[0].getClassName();
		test = extent.createTest(methodName,"Validate login to the application");
		test.log(Status.INFO, "Starting Open Login and Verify title");
		test.assignCategory("Regression Testing");
		
		  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("Mercury");
		  test.log(Status.INFO, "Entered UserName Successfully");
		  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Mercury");
		  test.log(Status.PASS, "Entered password Successfully");
		  //test.log(Status.WARNING, "Entered password Successfully");
		  test.addScreenCaptureFromPath(captureScreenshot(driver));
		  driver.findElement(By.xpath("//input[@name='submit']")).click();
		  
		  String actualTitle = driver.getTitle();
		  test.log(Status.PASS, "Login is Successful");
		  test.addScreenCaptureFromPath(captureScreenshot(driver));
		  System.out.println("Actual Title is :"+actualTitle);
		  String expectedTitle = "Login: Mercury Tours";
		  
		  AssertJUnit.assertEquals(expectedTitle,actualTitle);
	}
	@Test (priority = 2)
	public void loginPage2() throws IOException {
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		String className = new Exception().getStackTrace()[0].getClassName();
		test = extent.createTest(methodName,"Validate login to the application");
		test.log(Status.INFO, "Starting Open Login and Verify title");
		test.assignCategory("Regression Testing");
		
		driver.get("https://demo.guru99.com/test/newtours/login.php");	
		  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("mercury");
		  test.log(Status.INFO, "Entered UserName Successfully");
		  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mercury");
		  test.log(Status.INFO, "Entered password Successfully");
		  test.addScreenCaptureFromPath(captureScreenshot(driver));
		  driver.findElement(By.xpath("//input[@name='submit']")).click();
		  
		  String actualTitle = driver.getTitle();
		  test.log(Status.PASS, "Login failed");
		  test.addScreenCaptureFromPath(captureScreenshot(driver));
		  System.out.println("Actual Title is :"+actualTitle);
		  String expectedTitle = "Login: Mercury Tours";
		  
		  AssertJUnit.assertEquals(expectedTitle,actualTitle);
	}
	@AfterTest
	public void closeMethod() {
		extent.flush();
		driver.close();
	}
	
	@BeforeTest
	public void driverSetup() {
		initializer();
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/newtours/login.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	}


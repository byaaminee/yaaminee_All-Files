package com.Login.seleniumExample;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class LocatorsExample {
  
	//Create a WebDriver object. Initialize the object globally
	WebDriver driver;
	
	@Test
  public void openURL() {
		//Define the driver which needed
		//driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
  }
  
	@BeforeMethod
  public void beforeMethod() {
	  
		
	  Scanner scanner = new Scanner(System.in);	
	  System.out.println("Enter the name of Browser");
	  String browserName = scanner.nextLine();
	  
	  if(browserName.equalsIgnoreCase("firefox")) {
		  	driver = new FirefoxDriver();
	  }  else if(browserName.equalsIgnoreCase("edge")) {
		  	driver = new EdgeDriver();
	  }  else if(browserName.equalsIgnoreCase("Internet Explorer")) {
		  	driver = new InternetExplorerDriver();
	  }  else {
		  	driver = new ChromeDriver();
	  }
	  
	  driver = new ChromeDriver();
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  
	  
	  scanner.close();
  }
  

  @AfterMethod
  public void afterMethod() {
	 //driver.quit();
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("This is my Before Class Method");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("This is my After Class Method");
	  }

}
